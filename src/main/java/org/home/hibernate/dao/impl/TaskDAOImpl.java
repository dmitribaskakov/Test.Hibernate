package org.home.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.home.hibernate.HibernateUtil;
import org.home.hibernate.dao.interfaces.objects.TaskDAO;
import org.home.hibernate.entity.Task;

import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    /**
     * Получение списка задач
     * @return List<Task> список всех пользователей
     */
    @Override
    public List<Task> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("From Task").getResultList();
        session.close();
        return tasks;
    }

    /**
     * Получение списка задач по email
     * @param email regexp для столбца email.
     * @return List<Task> список задач
     */
    @Override
    public List<Task> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Task  Where user.email like :email");
        query.setParameter("email", email);
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    /**
     * Получение задачи по ID.
     * @param id ID задачи. Если задача с таким ID не найдена, то вернется Null.
     * @return Task задача
     */
    @Override
    public Task get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task task = session.get(Task.class, id);
        session.close();
        return task;
    }

    /**
     * Обновление информации о задаче.
     * @param task пользователь
     */
    @Override
    public void update(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(task);
        transaction.commit();
        session.close();
    }

    /**
     * Удаление задачи по ID.
     * @param id ID задачи.
     */
    @Override
    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task task = new Task();
        task.setId(id);
        Transaction transaction = session.beginTransaction();
        session.delete(task);
        transaction.commit();
        session.close();
    }

    /**
     * Добавление новой задачи.
     * @param task - задча.
     */
    @Override
    public void add(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(task);
        transaction.commit();
        session.close();
    }

    /**
     * Получение списка задач по email
     * @param completed признак завершенности задачи.
     * @param email regexp для столбца email.
     * @return List<Task> список задач
     */
    @Override
    public List<Task> find(boolean completed, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Task  Where user.email like :email and completed = :completed");
        query.setParameter("completed", completed);
        query.setParameter("email", email);
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }
}
