package cn.bridgeli.excel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.bridgeli.excel.service.AbstractDataImportService;


@Service
public class ExcelDataImportServiceImpl extends AbstractDataImportService {

    @Override
    protected boolean doDataImport(Map<Integer, List<String>> rowsMap) {
        List<Object> objects = new ArrayList<>();

        logger.info("[数据导入]数据格式化-->>begin");
        for (int i = 0; i < rowsMap.size(); i++) {
            List<String> row = rowsMap.get(i);
            logger.info("[数据导入]原数据：{}", row.toString());
            if (null != row && row.size() > 0){
                Object object = new Object();

//                TODO 每一行转成一个对象，放到list中
//                DataImportParseUtils.list2Object(row, object);

                logger.info("[数据导入]格式后数据：{}", object.toString());
                objects.add(object);
            }else{
                logger.info("[数据导入][异常]无效数据，忽略！");
            }
        }
        logger.info("[数据导入]数据格式化-->>end");

        logger.info("[数据导入]crm联系人数据导入-->>begin");
        if (objects.size() > 0){
            //数据导入逻辑
            for (Object object : objects){
//            	TODO 每个对象随便处理，或保存或其他
//                auctionCompanyService.save(object);
                logger.info("[数据导入]添加" + object.toString());
            }

            logger.info("[数据导入]导入完成");
        }else{
            logger.info("[数据导入]无数据导入");
        }
        logger.warn("[数据导入]crm联系人数据导入-->>end");

        return true;
    }
}
