package com.example.demo.Services;

import com.example.demo.Services.Components.StringParser.StringParser;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    /**
     *  Parse file to JSON
     * @param file String with structure
     * @return JSON String
     */
    public String stringParser(String file) {
        return StringParser.fileToArray(file);
    }
}
