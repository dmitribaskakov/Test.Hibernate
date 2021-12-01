package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

@Log4j2
public class Main {

    public static void main(String[] args) {
        // Получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        //Создание нового пользователя
//        User user = testCreateNewUser(session);
//        log.info("user.getId()=" + user.getId());
//

        //Выборка данных с помощью JPA Criteria API
        //TestJPACriteriaAPI.Test(session);

        //Выборка данных с помощью HQL
        TestHQL.Test(session);

        session.close();
        HibernateUtil.close();
    }

    /**
     * Создание нового пользователя
     * @param session- текущая сессия hibernate
     * @return User - созданный новый пользователь
     */
    public static User testCreateNewUser(Session session){
        session.getTransaction().begin();
        User user = new User();
        user.setEmail("newfromapp27@#mail.ru");
        user.setUsername("newfromapp27");
        user.setPassword("password");
        session.save(user);
        session.getTransaction().commit();
        return user;
    }


}
