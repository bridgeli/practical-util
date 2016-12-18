package cn.bridgeli.excel.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bridgeli.excel.util.PoiExcelReaderUtil;
import cn.bridgeli.exception.BussinessException;


public abstract class AbstractDataImportService implements DataImportService  {
    protected static Logger logger = LoggerFactory.getLogger(AbstractDataImportService.class);

    @Override
    public boolean dataImport(String filePath) throws BussinessException {
        InputStream excelFile = null;
        try {
            excelFile = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new BussinessException("[数据导入][失败]文件路径不对，读取文件失败！");
        }
        boolean flag = dataImport(excelFile);
        return flag;
    }

    @Override
    public boolean dataImport(InputStream inputStream) throws BussinessException {
        PoiExcelReaderUtil poiExcelReaderUtil = new PoiExcelReaderUtil();
        Map<Integer, List<String>> rows = null;
        try {
            rows = poiExcelReaderUtil.readExcelRowContent(inputStream, 0, -1, -1);
        } catch (BussinessException e) {
            throw new BussinessException("[数据导入][失败]poi解析excel数据文件失败！");
        }
        boolean flag = dataImport(rows);
        return flag;
    }

    @Override
    public boolean dataImport(Map<Integer, List<String>> rowsMap) throws BussinessException {
        if(rowsMap == null){
            throw new BussinessException("[数据导入][失败]待导入数据不能为空");
        }
        boolean flag = doDataImport(rowsMap);
        return flag;
    }

    protected abstract boolean doDataImport(Map<Integer, List<String>> rowsMap);
}
