package io.augmentedrealms.client.model;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.common.database.model.DBModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Util {

    public static<DB extends DBModel,OUT> Map<Long,OUT> getInstanceMap(Collection<DB> collection, FactoryMethod<DB,OUT> f) {
        if(collection==null){
            return new HashMap<>();
        }
        Map<Long,OUT> map = new HashMap<>();
        for (DB element : collection) {
            OUT out = f.make(element);
            if (out != null) {
                map.put(element.getId(),out);
            }
        }
        return map;

    }

    public interface FactoryMethod<DB,OUT> {
        OUT make(DB model);
    }
}
