// 
// 
// 

package com.car.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import java.util.UUID;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/api/file/" })
public class FileController
{
    @Value("${UPLOAD_URL}")
    private String UPLOAD_URL;
    
    @RequestMapping({ "upload.action" })
    @ResponseBody
    public Map<String, String> upload(@RequestParam("file") final MultipartFile file, final HttpServletRequest request) throws Exception {
        final String name = UUID.randomUUID().toString().replaceAll("-", "");
        final String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        final String filename = String.valueOf(name) + "." + ext;
        final String path = String.valueOf(this.UPLOAD_URL) + filename;
        final File filetemp = new File(String.valueOf(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0, Thread.currentThread().getContextClassLoader().getResource("").getPath().length() - 16)) + path);
        file.transferTo(filetemp);
        final String imagePath = "/photo/" + filename;
        final Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("url", imagePath);
        return dataMap;
    }
}
