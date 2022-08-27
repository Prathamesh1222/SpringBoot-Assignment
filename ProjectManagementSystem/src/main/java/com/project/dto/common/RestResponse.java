package com.project.dto.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class RestResponse {

    private boolean success;
    private String message;

    private Map<String, Object> data;



    public void setData(String key,Object value) {
       if(null==data){
           data=new HashMap<>();
       }
       data.put(key, value);
    }
}
