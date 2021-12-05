package org.home.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.home.hibernate.HibernateUtil;
import org.home.hibernate.dao.interfaces.objects.StatDAO;
import org.home.hibernate.entity.Stat;

public class StatDAOImpl implements StatDAO {

    // получение статистики конкретного пользователя (по email)
    @Override
    public Stat getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Stat> query = session.createQuery("FROM Stat where user.email like :email");
        query.setParameter("email", "%"+email+"%");
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }

}
