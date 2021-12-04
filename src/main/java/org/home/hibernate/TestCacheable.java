package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;
@Log4j2

public class TestCacheable {

    public static void Test() {
        log.info("Hibernate 1 started");

        //сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        User u1 = session.get(User.class, 10040L);
        log.info(u1);
        //u1.getCategories().forEach(log::info);
        session.close();// закрыть первуб сессию

        // откроем новую сессию
        log.info("Hibernate 2 started");
        session = HibernateUtil.getSessionFactory().openSession();
        User u2 = session.get(User.class, 10040L); // должен получить его из L2C
        log.info(u2);
        //u2.getCategories().forEach(log::info);

        session.close();// закрыть сессию

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
    }
}
