package com.example.demo.Services.Components.StringParser;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {

    @Test
    void fileToArraySimpleTest() {
        String file = "dsafdsafd #asdfas dfasdf #dsgfsgfd #1# #fdgfdsg### fdgsfdsgds##dsafd ###dsaf ###dsfadsa # fdsgfdsgf\n" +
                "##fsgfdsgfs #dsfadsafd";
        try {
            JSONObject json = new JSONObject(StringParser.fileToArray(file));
            assertEquals(json.get("data"),"Start");
        }catch (Exception error){
            error.getMessage();
        }
    }
}