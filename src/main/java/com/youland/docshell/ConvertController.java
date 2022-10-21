package com.youland.docshell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: rico
 * @date: 2022/10/14
 **/
@RequestMapping("/convert")
@RestController
public class ConvertController {

    Logger logger = LoggerFactory.getLogger(ConvertController.class);

    @PostMapping("/wordToPdf/{fileName}")
    public boolean wordToPdf(@PathVariable String fileName) {
        // TODO 支持多文件转换
        Runtime runtime = Runtime.getRuntime();
        String command = "soffice --convert-to pdf --outdir /gendocs /gendocs/".concat(fileName);
        try {
            logger.info("convert {} to pdf, exec command: {}.", fileName, command);
            Process pro = runtime.exec(command);
            pro.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            logger.info("exec command: {}, success: {}.", command, result);
        } catch (IOException | InterruptedException e) {
            logger.info("exec command: {}, error: {}.", command , e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @GetMapping("/wordToHtml/{fileName}")
    public boolean wordToHtml(@PathVariable String fileName) {
        // TODO 支持多文件转换
        Runtime runtime = Runtime.getRuntime();
        String command = "soffice --convert-to \"html:XHTML Writer File:UTF8\" --outdir /gendocs /gendocs/".concat(fileName);
        try {
            logger.info("convert {} to html, exec command: {}.", fileName, command);
            Process pro = runtime.exec(command);
            pro.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            logger.info("exec command: {}, success: {}.", command, result);
        } catch (IOException | InterruptedException e) {
            logger.info("exec command: {}, error: {}.", command , e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
