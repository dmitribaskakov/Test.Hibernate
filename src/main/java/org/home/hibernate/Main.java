package org.home.hibernate;

import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        // сразу получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        HibernateUtil.close();
    }
}
