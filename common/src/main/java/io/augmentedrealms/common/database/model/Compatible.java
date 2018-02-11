package io.augmentedrealms.common.database.model;

public interface Compatible<T extends Model> {

    T convertTo();

}
