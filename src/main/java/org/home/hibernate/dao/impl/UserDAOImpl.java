package org.home.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.home.hibernate.HibernateUtil;
import org.home.hibernate.dao.interfaces.objects.UserDAO;
import org.home.hibernate.entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    /**
     * Получение списка всех пользователей
     * @return List<User> список всех пользователей
     */
    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("From User").getResultList();
        session.close();
        return users;
    }

    /**
     * Получение списка пользователей по email
     * @param email regexp для столбца email.
     * @return List<User> список пользователей
     */
    @Override
    public List<User> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From User Where email like :email");
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    /**
     * Получение пользователя по email
     * @param email значение для столбца email.
     * @return User пользователь
     */
    @Override
    public User getByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From User Where email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    /**
     * Получение пользователя по ID.
     * @param id ID пользователя. Если Пользователь с таким ID не найден то вернется Null.
     * @return User пользователь
     */
    @Override
    public User get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * Обновление информации о пользователе.
     * @param user пользователь
     */
    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    /**
     * Удаление пользователя по ID.
     * @param id ID пользователя.
     */
    @Override
    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = new User();
        user.setId(id);
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    /**
     * Добавление нового пользователя.
     * @param user - Пользователь.
     */
    @Override
    public void add(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }


}
