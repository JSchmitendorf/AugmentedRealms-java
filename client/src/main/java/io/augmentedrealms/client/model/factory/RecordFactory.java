package io.augmentedrealms.client.model.factory;


import java.util.HashSet;
import java.util.Set;

/**
 * This is the factory for converting database model to record
 * which is able be represented as desired JSON format
 * @param <T>
 * @param <E>
 */
public interface RecordFactory<T,E> {

    E convertFrom(T from);

    default Set<E> ConvertSet(Set<T> set) {
        if(set==null){
            return null;
        }
        Set<E> recordConvertFactorySet = new HashSet<>();
        for (T element : set) {
            recordConvertFactorySet.add(convertFrom(element));
        }
        return recordConvertFactorySet;
    }
}
