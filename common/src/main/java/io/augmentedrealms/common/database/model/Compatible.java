package io.augmentedrealms.common.database.model;

public interface Compatible<T extends DBModel> {

    T convertTo();

}
