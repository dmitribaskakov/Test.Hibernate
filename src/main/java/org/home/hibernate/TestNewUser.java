package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.home.hibernate.entity.Role;
import org.home.hibernate.entity.User;

@Log4j2

public class TestNewUser {
    public static void Test() {
        final long userId = 101L;
        log.info("Test Create new User started");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Role r1 = session.get(Role.class, 1L);  // Admin
        //Role r2 = session.get(Role.class, 2L);    // User - добавляется скриптом базы
        User user = new User();
        user.setUsername("user."+userId);
        user.setPassword("user."+userId);
        user.setEmail("user."+userId+"@gmail.com");
        user.getRoles().add(r1);    // Admin
        //user.getRoles().add(r2);    // User - добавляется скриптом базы
        session.save(user);
        transaction.commit();
        log.info("user.getId()="+user.getId());
        session.close();// закрыть первую сессию
        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня

    }
}
