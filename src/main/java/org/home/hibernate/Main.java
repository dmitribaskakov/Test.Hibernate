package org.home.hibernate;

import org.hibernate.Session;
import org.home.hibernate.entity.User;

public class Main {

    public static void main(String[] args) {
        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

        //открываем тренкзакцию
        session.getTransaction().begin();
        User user = new User();
        user.setEmail("newfromapp7#mail.ru");
        user.setUsername("newfromapp7");
        user.setPassword("password");
        session.save(user);
        //закрываем тренкзакцию
        session.getTransaction().commit();
        System.out.println("user.getId()=" + user.getId());

        session.close();
        HibernateUtil.close();

    }
}
