package nl.beyco.webserver.helpers;

public interface BeycoSerializer {
    public <T> Object toObject(String jsonString, Class<T> valueType);
    public String toJson(Object obj);
}
