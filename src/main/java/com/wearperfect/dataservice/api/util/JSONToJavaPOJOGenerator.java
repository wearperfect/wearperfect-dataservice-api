package com.wearperfect.dataservice.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JSONToJavaPOJOGenerator {
    public static void main(String[] args){
        convertJSONToJavaPOJO();
    }

    public static void convertJSONToJavaPOJO(){
        InputStream inJson = JSONToJavaPOJOGenerator.class.getResourceAsStream("/dto/UserDTO.json");
        ObjectMapper mapper = new ObjectMapper();
    }
}
