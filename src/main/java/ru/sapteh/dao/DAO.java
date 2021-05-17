package ru.sapteh.dao;

import java.util.List;

public interface DAO<Object, Key> {
    List<Object> readByAll();
    Object read (Key key);
    void create(Object object);
    void update(Object object);
    void delete(Object object);

}
