package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.Task;

import java.util.Arrays;
@Log4j2

public class TestTask {
    public static void Test() {
        log.info("TestTask 1 started");
        Session session = HibernateUtil.getSessionFactory().openSession();

        Task t1 = session.get(Task.class, 50199L);
        log.info(t1);
        log.info(t1.getUser());
        session.close();// закрыть первую сессию

        // откроем новую сессию
        log.info("TestTask 2 started");
        session = HibernateUtil.getSessionFactory().openSession();
        Task t2 = session.get(Task.class, 50199L);
        log.info(t2);
        log.info(t2.getUser());

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
