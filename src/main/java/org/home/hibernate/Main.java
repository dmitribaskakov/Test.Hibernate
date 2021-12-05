package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

@Log4j2
public class Main {

    public static void main(String[] args) {
        //testCreateNewUser(); //Создание нового пользователя
        //TestJPACriteriaAPI.Test(); //Выборка данных с помощью JPA Criteria API
        //TestHQL.Test(); //Выборка данных с помощью HQL
        //TestCacheable.Test(); //Кеширование L2C
        //TestTask.Test();
        //TestNewUser.Test();

        TestDAO.Test();

    }

    /**
     * Создание нового пользователя
     */
    public static void testCreateNewUser() {
        // Получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        User user = new User();
        user.setEmail("newfromapp27@#mail.ru");
        user.setUsername("newfromapp27");
        user.setPassword("password");
        session.save(user);
        session.getTransaction().commit();
        log.info("user.getId()=" + user.getId());
        session.close();
        HibernateUtil.close();
    }

    /**
     * Удаление пользователя
     */
    public static void testDeleteUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        User user;

        // Способ 1
        user = session.get(User.class, 10025L);
        session.remove(user);

        // Способ 2
        user = new User();
        user.setId(10025L);
        session.remove(user);

        session.getTransaction().commit();
        log.info("user.getId()=" + user.getId());
        session.close();
        HibernateUtil.close();

    }

}
