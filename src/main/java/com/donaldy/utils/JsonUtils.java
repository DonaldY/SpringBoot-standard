package com.donaldy.utils;

import com.donaldy.dto.MultipartParamDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author donald
 * @date 2021/09/02
 */
@Slf4j
public class JsonUtils {

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> Map<String, Object> objectToMap(T object) {

        String json = transToJsonStr(object);

        return stringToMap(json, String.class, Object.class);
    }

    public static <T, V> Map<T, V> stringToMap(String json, Class<T> classT, Class<V> classV) {
        try {
            return JSON_MAPPER.readValue(json, TypeFactory.defaultInstance()
                            .constructMapType(HashMap.class, classT, classV));
        } catch (Exception e) {

            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    public static <T> List<T> stringToList(String json, Class<T> clazz) {
        try {
            return JSON_MAPPER.readValue(json, TypeFactory.defaultInstance()
                    .constructCollectionLikeType(ArrayList.class, clazz));
        } catch (Exception e) {

            throw new RuntimeException("Json反序列化异常", e);
        }
    }

    public static String transToJsonStr(Object obj) {

        if (obj == null) {
            return null;
        }

        try {
            return JSON_MAPPER.writeValueAsString(obj);

        } catch (JsonProcessingException e) {

            log.error("Json序列化异常, object : {}", obj.toString());

            throw new RuntimeException("Json序列化异常", e);
        }
    }

    public static <T> T mapToObject(Map<Object, Object> map, Class<T> clazz) {

        String json = transToJsonStr(map);

        if (StringUtils.isEmpty(json)) {

            return null;
        }

        return strToObject(json, clazz);
    }

    public static <T> T strToObject(String json, Class<T> clazz) {

        try {

            return JSON_MAPPER.readValue(json, clazz);

        } catch (IOException e) {

            log.error("Json反序列化异常, json: {}, class: {}", json, clazz);
        }

        return null;
    }

    public static <T> T strToObject(String json, TypeReference typeReference) {

        try {

            return JSON_MAPPER.readValue(json, typeReference);

        } catch (IOException e) {

            log.error("Json反序列化异常, json: {}, class: {}", json, typeReference);
        }

        return null;
    }

    public static <T> List<T> str2List(String json, Class<T> clazz) {
        List<T> objList = Collections.emptyList();
        try {
            JavaType t = JSON_MAPPER.getTypeFactory().constructParametricType(
                    List.class, clazz);
            objList = JSON_MAPPER.readValue(json, t);
        } catch (Exception ignored) {

            log.error("Json反序列化异常, json: {}, class: {}", json, clazz);
        }
        return objList;
    }

    public static String objectToStr(Object obj) {

        try {

            return JSON_MAPPER.writeValueAsString(obj);

        } catch (JsonProcessingException e) {

            log.error("Json序列化异常, class: {}", obj);
        }

        return "";
    }
}
