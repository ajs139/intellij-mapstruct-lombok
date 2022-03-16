package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MappingUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectToJson {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface JsonToObject {
    }

    @ObjectToJson
    public String objectToJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object: %s", obj, e);
            return "\"<UNPARSABLE>\"";
        }
    }

    @JsonToObject
    @SneakyThrows
    public Object jsonToObject(String json) {
        return objectMapper.readValue(json, Object.class);
    }
}
