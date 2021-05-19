package com.example.redis.paging.demo.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public final class MyJsonUtil {
    private MyJsonUtil() {
    }

    /**
     * 定义Jackson的ObjectMapper实例对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略类没有的字段
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String pojoToJson(Object pojo) throws Exception {
        try {
            return MAPPER.writeValueAsString(pojo);
        } catch (Exception e) {
            log.error("MyJsonUtil_pojoToJson", e);
            throw e;
        }
    }

    public static String pojoToJson(Object pojo, SimpleFilterProvider filterProvider) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setFilterProvider(filterProvider);
            return objectMapper.writeValueAsString(pojo);
        } catch (Exception e) {
            log.error("MyJsonUtil_pojoToJson", e);
            throw e;
        }
    }

    public static <T> T jsonToPojo(String json, Class<T> beanType) throws Exception {
        try {
            if (StringUtils.isBlank(json) || null == beanType) {
                return null;
            }
            T t = MAPPER.readValue(json, beanType);
            return t;
        } catch (Exception e) {
            log.error("MyJsonUtil_jsonToPojo", e);
            throw e;
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> beanType) throws Exception {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, beanType);
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.error("MyJsonUtil_jsonToList", e);
            throw e;
        }
    }

    public static Map pojoToMap(Object pojo) throws Exception {
        try {
            return MAPPER.readValue(MAPPER.writeValueAsString(pojo), Map.class);
        } catch (Exception e) {
            log.error("MyJsonUtil_pojoToMap", e);
            throw e;
        }
    }

    public static JsonNode jsonToJsonNode(String json) throws Exception {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return MAPPER.readTree(json);
        } catch (Exception e) {
            log.error("MyJsonUtil_jsonToJsonNode", e);
            throw e;
        }
    }
}
