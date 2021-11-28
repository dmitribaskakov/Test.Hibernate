package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

@Log4j2
public class Main {

    public static void main(String[] args) {
        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        //открываем тренкзакцию
        session.getTransaction().begin();
        User user = new User();
        user.setEmail("newfromapp24@#mail.ru");
        user.setUsername("newfromapp24");
        user.setPassword("password");
        session.save(user);
        //закрываем тренкзакцию
        session.getTransaction().commit();
        //System.out.println("user.getId()=" + user.getId());
        log.info("user.getId()=" + user.getId());

        session.close();
        HibernateUtil.close();

    }
}
