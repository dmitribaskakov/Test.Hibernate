package org.home.hibernate;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

public class Main {
    static Logger log = LogManager.getLogger(Main.class);

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
        log.log(Level.INFO, "user.getId()=" + user.getId());

        session.close();
        HibernateUtil.close();

    }
}
