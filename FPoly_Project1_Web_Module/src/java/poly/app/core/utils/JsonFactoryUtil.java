package poly.app.core.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonFactoryUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new Hibernate4Module());
    }

    public static String toJson(Object object, boolean withPrettyPrinter) throws JsonProcessingException {
        String json = "";
        if (withPrettyPrinter) {
            json = json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } else {
            json = json = mapper.writeValueAsString(object);
        }

        return json;
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return toJson(object, false);
    }

    public static <T extends Object> T toObject(String json, TypeReference<T> typeReference) throws IOException, JsonParseException, JsonMappingException{
        T result = null;
        result = mapper.readValue(json, typeReference);
        return result;
    }
}
