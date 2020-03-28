package com.example.demo.Controllers;

import com.example.demo.Services.ApiService;
import com.example.demo.Services.Components.StringParser.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class RestControllers {
    /**
     * Pars file
     * @param file file
     * @return  JSON
     */
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public String uploadFile(@NotNull @RequestParam("file") MultipartFile file) {
        try {
            return new ApiService().stringParser(new String(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
