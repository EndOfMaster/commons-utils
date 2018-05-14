package com.endofmaster.commons.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author YQ.Huang
 */
public abstract class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> tClass) throws JsonException {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    public static String toJson(Object object) throws JsonException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }
}
