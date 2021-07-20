package nl.beyco.webserver.helpers;

public interface BeycoSerializer {
    <T> Object toObject(String jsonString, Class<T> valueType);
    String toJson(Object obj);
}
