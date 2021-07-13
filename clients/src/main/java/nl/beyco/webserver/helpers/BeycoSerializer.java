package nl.beyco.webserver.helpers;

import org.springframework.stereotype.Component;

@Component
public interface BeycoSerializer {
    public abstract <T> Object toObject(String jsonString, Class<T> valueType);
    public abstract String toJson(Object obj);
}
