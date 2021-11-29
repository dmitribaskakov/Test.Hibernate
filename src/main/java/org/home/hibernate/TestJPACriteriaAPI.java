package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.home.hibernate.entity.User;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TestJPACriteriaAPI {


    /**
     * Выборка данных с помощью JPA Criteria API.
     * Получение списка всех пользователей
     * @param session - текущая сессия hibernate
     * @return List<User> список всех пользователей
     */
    public static List<User> GetAllUsers(Session session){
        // Подготовка запроса - Получение списка всех пользователей
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        // Выполнение запроса - Получение списка всех пользователей
        Query query = session.createQuery(criteriaQuery);
        return (List<User>) query.getResultList();
    }

    /**
     * Выборка данных с помощью JPA Criteria API.
     * Получение списка пользователей по условию
     * @param session - текущая сессия hibernate
     * @return List<User> список всех пользователей
     */
    public static List<User> GetUsersWhere(Session session){
        // Подготовка запроса - Получение списка всех пользователей
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate p1 = criteriaBuilder.gt(userRoot.get("id"), 10000);
        Predicate p2 = criteriaBuilder.le(userRoot.get("id"), 20000);
        Predicate p3 = criteriaBuilder.notEqual(userRoot.get("id"), 15000);
        criteriaQuery.select(userRoot).where(criteriaBuilder.and(p1, criteriaBuilder.and(p2, p3)));
        // Выполнение запроса - Получение списка всех пользователей
        Query query = session.createQuery(criteriaQuery);
        return (List<User>) query.getResultList();
    }

    /**
     * Удаление данных с помощью JPA Criteria API.
     * Удаление списка пользователей
     * @param session - текущая сессия hibernate
     * @param ids - список id для удаления
     * @return int количество удаленных пользователей
     */
    public static int DeleteUserByID(Session session, ArrayList<Long> ids){
        int query = 0;
        // Подготовка запроса - Удаление списка пользователей
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> userRoot = criteriaDelete.from(User.class);
        // Выполнение запроса - Удаление списка пользователей
        Transaction transaction = session.beginTransaction();
        for (Long id: ids) {
            log.info("Удаление пользователя id="+id);
            criteriaDelete.where(criteriaBuilder.equal(userRoot.get("id"), id));
            query += session.createQuery(criteriaDelete).executeUpdate();
        }
        transaction.commit();
        return query;
    }

}
