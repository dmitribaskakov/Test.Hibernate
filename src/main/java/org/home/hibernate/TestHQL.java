package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.home.hibernate.entity.User;

import java.util.List;
@Log4j2

public class TestHQL {

    /**
     * HQL/JPQL – более упрощенный и сокращенный вариант стандартного синтаксиса SQL.
     * JPQL – Java Persistence Query Language – из стандарта JPA
     * HQL – Hibernate Query Language – более расширенная версия JPQL
     */
    public static void Test(){

        // Получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();

//        //Получение списка всех пользователей
//        List<User> users = TestHQL.GetAllUsers(session);
//        log.info("HQL.GetAllUsers users.size()=" + users.size());

//        //Получение списка пользователей по условию
//        List<User> users2 = GetUsersWhere(session, 0, 10);
//        log.info("HQL.GetUsersWhere users2.size()=" + users2.size());

//        //Получение пользователя по ID
//        User user = GetUserById(session, 10039L);
//        log.info("HQL.GetUserById=" + user);

        //Получение пользователя по ID
        User user2 = GetUserById(session, 10039L);
        log.info("HQL.GetUserById 2=" + user2);
        log.info("HQL.GetUserById.getCategories 2:");
        user2.getCategories().forEach(log::info);
        log.info("HQL.GetUserById.getPriorities 2:");
        user2.getPriorities().forEach(log::info);
        log.info("HQL.GetUserById.getActivity 2:");
        log.info(user2.getActivity().toString());
        log.info("HQL.GetUserById.getStat 2:");
        log.info(user2.getStat().toString());
        log.info("HQL.GetUserById.getRoles 2:");
        user2.getRoles().forEach(log::info);


//        Long res = GetUserCountByEmail(session, "%@mail.ru%");
//        log.info("HQL.GetUserCountByEmail=" + res);
//
//        //Получение списка пользователей с указанным email через Native SQL
//        List<User> users3 = GetUsersByEmail(session, "%@mail.ru%", 0, 10);
//        log.info("Native SQL.GetUsersByEmail users3.size()=" + users3.size());
//
//        //Получение статистики по доменам пользователей через Native SQL
//        //Object stat =
//
//        Object[][] objs = GetStatisticByUsersDomain(session);
//        for(Object[] obj: objs) {
//            log.info(obj[0]);
//            log.info(obj[1]);
//            log.info("----");
//        }

        session.close();
        HibernateUtil.close();

    }

    /**
     * Выборка данных с помощью HQL.
     * Получение списка всех пользователей
     * @param session - текущая сессия hibernate
     * @return List<User> список всех пользователей
     */
    public static List<User> GetAllUsers(Session session) {
        // Выполнение запроса - Получение списка всех пользователей
        Query query = session.createQuery("From User");
        return (List<User>) query.getResultList();
    }

    /**
     * Выборка данных с помощью HQL.
     * Получение списка пользователей по условию
     *
     * @param session - текущая сессия hibernate
     * @param offset - с какой записы начать вывод (нумерация с 0), если <0 то не используется.
     * @param pageSize - вернуть указангное количествр записей, если <=0 то не используется.
     * @return List<User> список всех пользователей
     */
    public static List<User> GetUsersWhere(Session session, int offset, int pageSize) {
        // Выполнение запроса - Получение списка пользователей
        Query query = session.createQuery("From User as u Where u.id > :gt and u.id < :ls");
        query.setParameter("gt", 10000L);
        query.setParameter("ls", 20000L);
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
            if (offset >= 0) query.setFirstResult(offset);
        }
        return (List<User>) query.getResultList();
    }

    /**
     * Выборка данных с помощью HQL.
     * Получение пользователя по ID.
     * @param session - текущая сессия hibernate
     * @param id - ID пользователя. Если Пользователь с таким ID не найден то вернется Null.
     * @return User пользователь
     */
    public static User GetUserById(Session session, Long id) {
        // Выполнение запроса - Получение пользователя
        Query query = session.createQuery("From User as u Where u.id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    /**
     * Выборка данных с помощью HQL.
     * Получение пользователя по ID.
     * @param session - текущая сессия hibernate
     * @param id - ID пользователя. Если Пользователь с таким ID не найден то вернется Null.
     * @return User пользователь
     */
    public static User GetUserById2(Session session, Long id) {
        // Выполнение запроса - Получение пользователя
        User user = session.get(User.class, id);
        return user;
    }
    /**
     * Выборка данных с помощью HQL.
     * Получение количества пользователей с указанным email
     * @param session - текущая сессия hibernate
     * @param email - regexp для столбца email.
     * @return Long количество пользователей
     */
    public static Long GetUserCountByEmail(Session session, String email) {
        // Выполнение запроса - Получение количества пользователей с указанным email
        Query query = session.createQuery("select count(u.id) from User as u where u.email like :email");
        query.setParameter("email", email);
        return (Long) query.getSingleResult();
    }



    /**
     * Выборка данных с помощью HQL.
     * Получение списка пользователей с указанным email через Native SQL
     * @param session - текущая сессия hibernate
     * @param offset - с какой записы начать вывод (нумерация с 0), если <0 то не используется.
     * @param pageSize - вернуть указангное количествр записей, если <=0 то не используется.
     * @return List<User> список всех пользователей
     */
    public static List<User> GetUsersByEmail(Session session, String email, int offset, int pageSize) {
        // Выполнение запроса - Получение списка пользователей с указанным email
        final String sqlQueryStr = "select * from todolist.user_data where email like :email";
        NativeQuery<User> nativeQuery = session.createSQLQuery(sqlQueryStr);
        nativeQuery.addEntity(User.class);
        nativeQuery.setParameter("email", email);
        if (pageSize > 0) {
            nativeQuery.setMaxResults(pageSize);
            if (offset >= 0) nativeQuery.setFirstResult(offset);
        }
        return (List<User>) nativeQuery.getResultList();
    }


    /**
     * Выборка данных с помощью HQL.
     * Получение статистики по доменам пользователей через Native SQL
     * @param session - текущая сессия hibernate
     * @return Object[][] список всех пользователей
     */
    public static Object[][] GetStatisticByUsersDomain(Session session) {
        // Выполнение запроса - статистики по доменам пользователей через Native SQL
        final String sqlQueryStr =
                "select substring(u.email, position('@' in u.email)+1 ) as DomainName, " +
                "       count(u.id) as Count " +
                "from todolist.user_data u " +
                "where u.email like '%@%' " +
                "group by DomainName " +
                "order by DomainName";
        NativeQuery<Object[][]> nativeQuery = session.createSQLQuery(sqlQueryStr);
        return nativeQuery.getResultList().toArray(new Object[0][]);
    }

}
