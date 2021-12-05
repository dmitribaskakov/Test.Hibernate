package org.home.hibernate.dao.interfaces;

/*

реализация основных операций
CRUD (Create, Read, Update, Delete)

 */

public interface CommonDAO<T> {

    // получить одно значение по id
    T get(long id);

    // обновить значение
    void update(T obj);

    // удалить значение по id
    void delete(long id);

    // добавить значение
    void add(T obj);
}
