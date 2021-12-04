package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

import java.util.Arrays;

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

        //Статистика кешированя
        log.info("L2C HitCount: " + session.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
        log.info("L2C MissCount: " + session.getSessionFactory().getStatistics().getSecondLevelCacheMissCount());
        log.info("L2C PutCount: " + session.getSessionFactory().getStatistics().getSecondLevelCachePutCount());
        log.info("L2C RegionNames:");
        Arrays.stream(session.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames()).forEach(log::info);

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
    }
}
