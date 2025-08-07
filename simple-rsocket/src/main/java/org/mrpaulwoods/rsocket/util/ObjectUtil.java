package org.mrpaulwoods.rsocket.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;

import java.io.IOException;

public class ObjectUtil {

    public static Payload toPayload(Object o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = objectMapper.writeValueAsBytes(o);
            return DefaultPayload.create(bytes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(Payload payload, Class<T> type) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] array = payload.getData().array();
            return objectMapper.readValue(array, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
