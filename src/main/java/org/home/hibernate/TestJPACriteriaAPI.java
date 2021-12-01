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

    public static void Test(Session session){
        //Получение списка всех пользователей
        List<User> users = TestJPACriteriaAPI.GetAllUsers(session);
        log.info("TestJPACriteriaAPI.GetAllUsers users.size()=" + users.size());

        //Получение списка пользователей по условию
        List<User> users2 = TestJPACriteriaAPI.GetUsersWhere(session);
        log.info("JPACriteriaAPI.GetUsersWhere users2.size()=" + users2.size());

        ArrayList<Long> ids = new ArrayList<>(List.of(
                users2.get(0).getId(),
                users2.get(1).getId()));
        int res = TestJPACriteriaAPI.UpdateUserByID(session, ids);
        log.info("JPACriteriaAPI.UpdateUserByID res=" + res);

        ArrayList<Long> ids2 = new ArrayList<>(List.of(
                users2.get(2).getId(),
                users2.get(3).getId()));
        int res2 = TestJPACriteriaAPI.DeleteUserByID(session, ids2);
        log.info("JPACriteriaAPI.DeleteUserByID res=" + res2);

        List<User> users3 = TestJPACriteriaAPI.GetUsersWhere(session);
        log.info("JPACriteriaAPI.GetUsersWhere users3.size()=" + users3.size());
    }

    /**
     * Выборка данных с помощью JPA Criteria API.
     * Получение списка всех пользователей
     *
     * @param session - текущая сессия hibernate
     * @return List<User> список всех пользователей
     */
    public static List<User> GetAllUsers(Session session) {
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
     *
     * @param session - текущая сессия hibernate
     * @return List<User> список всех пользователей
     */
    public static List<User> GetUsersWhere(Session session) {
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
     *
     * @param session - текущая сессия hibernate
     * @param ids     - список id для удаления
     * @return int количество удаленных пользователей
     */
    public static int DeleteUserByID(Session session, ArrayList<Long> ids) {
        int query = 0;
        // Подготовка запроса - Удаление списка пользователей
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> userRoot = criteriaDelete.from(User.class);
        // Выполнение запроса - Удаление списка пользователей
        Transaction transaction = session.beginTransaction();
        for (Long id : ids) {
            log.info("Удаление пользователя id=" + id);
            criteriaDelete.where(criteriaBuilder.equal(userRoot.get("id"), id));
            query += session.createQuery(criteriaDelete).executeUpdate();
        }
        transaction.commit();
        return query;
    }


    /**
     * Обновление данных с помощью JPA Criteria API.
     * Обновление списка пользователей
     * @param session - текущая сессия hibernate
     * @param ids - список id для обновления
     * @return int количество удаленных пользователей
     */
    public static int UpdateUserByID(Session session, ArrayList<Long> ids) {
        int query = 0;
        // Подготовка запроса - Обновление списка пользователей
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate;
        Root<User> userRoot;
        // Выполнение запроса - Обновление списка пользователей
        Transaction transaction = session.beginTransaction();
        for (Long id : ids) {
            log.info("Обновление пользователя id=" + id.toString());
            criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
            userRoot = criteriaUpdate.from(User.class);
            criteriaUpdate.set("email", id +"@mail.ru");
            criteriaUpdate.set("username", id.toString());
            criteriaUpdate.set("password", id.toString());
            criteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), id));
            query += session.createQuery(criteriaUpdate).executeUpdate();
        }
        transaction.commit();
        return query;
    }
}
