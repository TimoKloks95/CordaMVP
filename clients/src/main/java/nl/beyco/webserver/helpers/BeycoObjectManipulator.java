package nl.beyco.webserver.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BeycoObjectManipulator {
    private static ObjectMapper objectMapper;
    private static ObjectWriter objectWriter;
    public BeycoObjectManipulator() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    public static <T> T toObject(String jsonString, Class<T> valueType) throws JsonProcessingException {
        return (T) objectMapper.readValue(jsonString, valueType);
    }

    public static <T> String toJson(T obj) throws JsonProcessingException {
        return objectWriter.writeValueAsString(obj);
    }
}
