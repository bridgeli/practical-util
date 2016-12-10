package cn.bridgeli.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * 主从切面
 */
public class MasterAnnotationAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(MasterAnnotationAspect.class);
    
    @Autowired
    private MasterSlaveSelector masterSlaveSelector;
    
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();

        Class<?>[] clazz = target.getClass().getInterfaces();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(Master.class)) {
                logger.warn("select master by method :" + m.getName());
                masterSlaveSelector.master();
            }else{
                logger.warn("select slave by method :" + m.getName());
                masterSlaveSelector.slave();
            }
            
        } catch (Exception e) {
            masterSlaveSelector.slave();
        }
    }


}
