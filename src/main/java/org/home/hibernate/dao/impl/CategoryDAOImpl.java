package org.home.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.home.hibernate.HibernateUtil;
import org.home.hibernate.dao.interfaces.objects.CategoryDAO;
import org.home.hibernate.entity.Category;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category");
        List<Category> list = query.getResultList(); // получение коллекции должно быть до закрытия сессии
        session.close();
        return list;
    }

    @Override
    public List<Category> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category p where p.user.email like :email");
        query.setParameter("email", "%"+email+"%");
        List<Category> list = query.getResultList(); // получение коллекции должно быть до закрытия сессии
        session.close();
        return list;
    }

    @Override
    public Category get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category user = session.get(Category.class, id); // получаем единичный объект
        session.close();
        return user;
    }

    @Override
    public void update(Category obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        session.update(obj); // если id объекта НЕ будет заполнено - выйдет ошибка (Hibernate не поймет какую именно строку хотите обновить)
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        Category u = new Category();// создаем временный объект и заполняем его id
        u.setId(id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Category obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        session.save(obj); // если id объекта будет заполнено - БД перезапишет это поле
        session.getTransaction().commit();
        session.close();
    }
}
