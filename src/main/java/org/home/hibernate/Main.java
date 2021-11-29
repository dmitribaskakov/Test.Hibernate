package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.home.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Main {

    public static void main(String[] args) {
        // Получаем готовый SessionFactory и сразу создаем сессию
        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        //Создание нового пользователя
//        User user = testCreateNewUser(session);
//        log.info("user.getId()=" + user.getId());

        //Получение списка всех пользователей
        //Выборка данных с помощью JPA Criteria API. Получение списка всех пользователей
        List<User> users = TestJPACriteriaAPI.GetAllUsers(session);
        log.info("TestJPACriteriaAPI.GetAllUsers users.size()=" + users.size());


        //Выборка данных с помощью JPA Criteria API. Получение списка пользователей по условию
        List<User> users2 = TestJPACriteriaAPI.GetUsersWhere(session);
        log.info("TestJPACriteriaAPI.GetUsersWhere users2.size()=" + users2.size());

        ArrayList<Long> ids = new ArrayList<>(List.of(
                users2.get(0).getId(),
                users2.get(1).getId()));
        int res = TestJPACriteriaAPI.UpdateUserByID(session, ids);
        log.info("TestJPACriteriaAPI.UpdateUserByID res=" + res);

        ArrayList<Long> ids2 = new ArrayList<>(List.of(
                users2.get(2).getId(),
                users2.get(3).getId()));
        int res2 = TestJPACriteriaAPI.DeleteUserByID(session, ids2);
        log.info("TestJPACriteriaAPI.DeleteUserByID res=" + res2);

        List<User> users3 = TestJPACriteriaAPI.GetUsersWhere(session);
        log.info("TestJPACriteriaAPI.GetUsersWhere users3.size()=" + users3.size());

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
