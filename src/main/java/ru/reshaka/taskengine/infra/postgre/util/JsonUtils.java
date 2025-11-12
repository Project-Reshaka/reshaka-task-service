package ru.reshaka.taskengine.infra.postgre.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON serialization error", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return json == null ? null : MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException("JSON deserialization error", e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return json == null ? null : MAPPER.readValue(json, type);
        }
        catch (Exception e) {
            throw new RuntimeException("JSON deserialization error", e);
        }
    }
}

