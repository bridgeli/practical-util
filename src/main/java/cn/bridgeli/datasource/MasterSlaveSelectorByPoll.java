package cn.bridgeli.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库选择器
 */
public class MasterSlaveSelectorByPoll implements MasterSlaveSelector {

    private static final Logger logger = LoggerFactory.getLogger(MasterSlaveSelectorByPoll.class);

    public static final ThreadLocal<DataSource> holder = new ThreadLocal<DataSource>();

    private List<DataSource> masters;

    private List<DataSource> slaves;

    private AtomicInteger selectedMasterIndex = new AtomicInteger(0);
    private AtomicInteger selectedSlavesIndex = new AtomicInteger(0);

    private List<DataSource> badMasters = Collections.synchronizedList(new ArrayList<DataSource>());
    private List<DataSource> badSlaves = Collections.synchronizedList(new ArrayList<DataSource>());

    private DataSource defaultDataSource;
    
    @Override
    public DataSource get() {
        DataSource ds = holder.get();
        return ds == null ? defaultDataSource : ds;
    }


    @Override
    public void master() {
        logger.debug("change master!");
        if(!masters.contains(holder.get())){
            holder.set(getNext(selectedMasterIndex, masters));
        }
    }

    /**
     * 获取下个目标
     */
    private DataSource getNext(AtomicInteger selectedIndex, List<DataSource> sources) {
        if (sources.isEmpty()) {
            throw new RuntimeException("No datasource available.");
        }
        selectedIndex.weakCompareAndSet(sources.size(), 0);
        return sources.get(selectedIndex.getAndIncrement() % sources.size());
    }

    @Override
    public void slave() {
        logger.debug("change slave!");
        if(holder.get() == null){
            holder.set(getNext(selectedSlavesIndex, slaves));
        }
    }

    @Override
    public void monitor() {
        checkRestore(badMasters, masters);
        checkRestore(badSlaves, slaves);
        checkBadDataSource(masters, badMasters);
        checkBadDataSource(slaves, badSlaves);
    }

    /**
     * 检查异常的数据源是否恢复了
     */
    private void checkBadDataSource(List<DataSource> normalDataSources,
                                    List<DataSource> badDataSources) {
        for (DataSource ds : normalDataSources) {
            try {
                ds.getConnection();
                ds.getConnection().close();
            } catch (Exception e) {
                badDataSources.add(ds);
                logger.error("Check new database error! database:" + ds, e.toString());
            }
        }
    }

    /**
     * 检查恢复的数据库
     */
    private void checkRestore(List<DataSource> badDataSources, List<DataSource> normalDataSources) {
        for (DataSource ds : badDataSources) {
            try {
                ds.getConnection();
                ds.getConnection().close();
                normalDataSources.add(ds);
            } catch (SQLException e) {
                logger.error("Continue database error! database:" + ds, e.toString());
            }
        }
    }

    /**
     * Setter method for property <tt>masters</tt>.
     * 
     * @param masters value to be assigned to property masters
     */
    public void setMasters(List<DataSource> masters) {
        this.masters = Collections.synchronizedList(masters);
    }

    /**
     * Setter method for property <tt>slaves</tt>.
     * 
     * @param slaves value to be assigned to property slaves
     */
    public void setSlaves(List<DataSource> slaves) {
        this.slaves = Collections.synchronizedList(slaves);
    }
    
    /**
     * Setter method for property <tt>defaultDataSource</tt>.
     * 
     * @param defaultDataSource value to be assigned to property defaultDataSource
     */
    public void setDefaultDataSource(DataSource defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

}
