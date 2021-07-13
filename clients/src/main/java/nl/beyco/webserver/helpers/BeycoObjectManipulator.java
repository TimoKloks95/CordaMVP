package nl.beyco.webserver.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface BeycoObjectManipulator {
    <T> T toObject(String jsonString, Class<T> valueType) throws JsonProcessingException;
    <T> String toJson(T obj) throws JsonProcessingException;
}
