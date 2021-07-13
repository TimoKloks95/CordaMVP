package nl.beyco.webserver.helpers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.beyco.webserver.helpers.BeycoObjectManipulator;
import org.springframework.stereotype.Component;

@Component
public class BeycoObjectManipulatorImpl implements BeycoObjectManipulator {
    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;
    public BeycoObjectManipulatorImpl() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    public <T> T toObject(String jsonString, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, valueType);
    }

    public <T> String toJson(T obj) throws JsonProcessingException {
        return objectWriter.writeValueAsString(obj);
    }
}
