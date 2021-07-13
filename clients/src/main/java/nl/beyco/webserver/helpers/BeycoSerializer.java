package nl.beyco.webserver.helpers;

import org.springframework.stereotype.Service;

@Service
public abstract class BeycoSerializer {
    public abstract <T> Object toObject(String jsonString, Class<T> valueType);
    public abstract String toJson(Object obj);
}
