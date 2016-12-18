package cn.bridgeli.excel.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.bridgeli.exception.BussinessException;

public interface DataImportService {
    /**
     * 数据导入
     * @param filePath
     * @return
     * @throws BussinessException
     */
    public boolean dataImport(String filePath) throws BussinessException;
    /**
     * 数据导入
     * @param inputStream
     * @return
     * @throws BussinessException
     */
    public boolean dataImport(InputStream inputStream) throws BussinessException;

    /**
     * 数据导入
     * @param rowsMap
     * @return
     * @throws BussinessException
     */
    public boolean dataImport(Map<Integer, List<String>> rowsMap) throws BussinessException;
}
