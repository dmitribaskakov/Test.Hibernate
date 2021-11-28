package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j2
public class Main {

    public static void main(String[] args) {
        // Получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        //Создание нового пользователя
//        User user = testCreateNewUser(session);
//        log.info("user.getId()=" + user.getId());

        //Получение списка всех пользователей
        //Выборка данных с помощью JPA Criteria API. Получение списка всех пользователей
        List<User> users = testJPACriteriaAPIGetAllUsers(session);
        log.info("users.size()=" + users.size());

        session.close();
        HibernateUtil.close();
    }

    /**
     * Создание нового пользователя
     * @param session- текущая сессия hibernate
     * @return User - созданный новый пользователь
     */
    public static User testCreateNewUser(Session session){
        session.getTransaction().begin();
        User user = new User();
        user.setEmail("newfromapp27@#mail.ru");
        user.setUsername("newfromapp27");
        user.setPassword("password");
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    /**
     * Выборка данных с помощью JPA Criteria API.
     * Получение списка всех пользователей
     * @param session - текущая сессия hibernate
     * @return List<User> список всех пользователей
     */
    public static List<User> testJPACriteriaAPIGetAllUsers(Session session){
        // Подготовка запроса - Получение списка всех пользователей
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        // Выполнение запроса - Получение списка всех пользователей
        Query query = session.createQuery(criteriaQuery);
        return (List<User>) query.getResultList();
    }



}
