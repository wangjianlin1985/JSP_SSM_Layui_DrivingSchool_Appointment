// 
// 
// 

package com.car.utils;

import com.fasterxml.jackson.databind.JavaType;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;

public class Result implements Serializable
{
    private static final ObjectMapper MAPPER;
    private Integer status;
    private String msg;
    private Object data;
    
    static {
        MAPPER = new ObjectMapper();
    }
    
    public static Result build(final Integer status, final String msg, final Object data) {
        return new Result(status, msg, data);
    }
    
    public static Result ok(final Object data) {
        return new Result(data);
    }
    
    public static Result ok() {
        return new Result(null);
    }
    
    public Result() {
    }
    
    public static Result build(final Integer status, final String msg) {
        return new Result(status, msg, null);
    }
    
    public Result(final Integer status, final String msg, final Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public Result(final Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(final String msg) {
        this.msg = msg;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    public static Result formatToPojo(final String jsonData, final Class<?> clazz) {
        try {
            if (clazz == null) {
                return (Result)Result.MAPPER.readValue(jsonData, (Class)Result.class);
            }
            final JsonNode jsonNode = Result.MAPPER.readTree(jsonData);
            final JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = Result.MAPPER.readValue(data.traverse(), (Class)clazz);
                }
                else if (data.isTextual()) {
                    obj = Result.MAPPER.readValue(data.asText(), (Class)clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static Result format(final String json) {
        try {
            return (Result)Result.MAPPER.readValue(json, (Class)Result.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Result formatToList(final String jsonData, final Class<?> clazz) {
        try {
            final JsonNode jsonNode = Result.MAPPER.readTree(jsonData);
            final JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = Result.MAPPER.readValue(data.traverse(), (JavaType)Result.MAPPER.getTypeFactory().constructCollectionType((Class)List.class, (Class)clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e) {
            return null;
        }
    }
}
