package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

@Log4j2
public class Main {

    public static void main(String[] args) {
        //Создание нового пользователя
        //testCreateNewUser();

        //Выборка данных с помощью JPA Criteria API
        //TestJPACriteriaAPI.Test();

        //Выборка данных с помощью HQL
        //TestHQL.Test();

        //Кеширование L2C
        TestCacheable.Test();

    }

    /**
     * Создание нового пользователя
     */
    public static void testCreateNewUser(){
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


}
