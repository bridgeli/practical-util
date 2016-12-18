package cn.bridgeli.excel.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bridgeli.excel.service.DataImportService;
import cn.bridgeli.exception.BussinessException;
import cn.bridgeli.web.Result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/excelImportController")
public class ExcelImportController {

    private Logger logger = LoggerFactory.getLogger(ExcelImportController.class);

    //创建gson
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Resource(name="excelDataImportServiceImpl")
    private DataImportService excelDataImportServiceImpl;

    @RequestMapping("excelImport")
    @ResponseBody
    public Object excelImport(@RequestParam("file") MultipartFile file){
        Result result = new Result(false);
        try {
            boolean flag = excelDataImportServiceImpl.dataImport(file.getInputStream());
            result.setSuccess(flag);
        } catch (IOException e) {
            result.setMsg("上传文件格式不符");
            e.printStackTrace();
        } catch (BussinessException e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
            return gson.toJson(result);
        }
        return gson.toJson(result);
    }

}
