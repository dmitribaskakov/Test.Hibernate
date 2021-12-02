package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;
@Log4j2

public class TestCacheable {

    public static void Test() {
        // Получаем готовый SessionFactory и сразу создаем сессию
        Session session1 = HibernateUtil.getSessionFactory().openSession();

        //Получение пользователя по ID
        User user1 = TestHQL.GetUserById(session1, 10039L);
        log.info("HQL.GetUserById =" + user1);
        log.info("HQL.GetUserById.getCategories:");
        user1.getCategories().forEach(log::info);
        log.info("HQL.GetUserById.getPriorities:");
        user1.getPriorities().forEach(log::info);
        log.info("HQL.GetUserById.getActivity:");
        log.info(user1.getActivity().toString());
        log.info("HQL.GetUserById.getStat:");
        log.info(user1.getStat().toString());
        log.info("HQL.GetUserById.getRoles:");
        user1.getRoles().forEach(log::info);

        session1.close();

        log.info("Новая сессия. все данные берутся из кеша");

        Session session2 = HibernateUtil.getSessionFactory().openSession();
        //Получение пользователя по ID
        User user2 = TestHQL.GetUserById(session2, 10039L);
        log.info("HQL.GetUserById =" + user2);
        log.info("HQL.GetUserById.getCategories:");
        user2.getCategories().forEach(log::info);
        log.info("HQL.GetUserById.getPriorities:");
        user2.getPriorities().forEach(log::info);
        log.info("HQL.GetUserById.getActivity:");
        log.info(user2.getActivity().toString());
        log.info("HQL.GetUserById.getStat:");
        log.info(user2.getStat().toString());
        log.info("HQL.GetUserById.getRoles:");
        user2.getRoles().forEach(log::info);
        session2.close();


        HibernateUtil.close();
    }
}
