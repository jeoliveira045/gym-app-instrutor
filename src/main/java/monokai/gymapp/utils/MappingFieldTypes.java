package monokai.gymapp.utils;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
public class MappingFieldTypes {

    private Map<Class, Function> functionMap = new HashMap<>();

    public MappingFieldTypes(){
        functionMap.put(Long.class, (cons) -> Long.parseLong(cons.toString()));
        functionMap.put(String.class, (cons) -> String.valueOf(cons));
    }
}
