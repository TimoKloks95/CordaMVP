package nl.beyco.webserver.helpers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.beyco.webserver.business.exceptions.BeycoParseException;
import nl.beyco.webserver.helpers.BeycoObjectManipulator;
import nl.beyco.webserver.helpers.BeycoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeycoSerializerImpl implements BeycoSerializer {

    @Autowired
    private BeycoObjectManipulator objectManipulator;

    @Override
    public <T> Object toObject(String jsonString, Class<T> valueType) {
        try {
            return objectManipulator.toObject(jsonString, valueType);
        } catch(JsonProcessingException e) {
            throw new BeycoParseException("Something went wrong trying to parse a json string to an object.", e);
        }
    }

    @Override
    public String toJson(Object obj) {
        try {
            return objectManipulator.toJson(obj);
        } catch(JsonProcessingException e) {
            throw new BeycoParseException("Something went wrong trying to parse an object to a json string.", e);
        }
    }
}
