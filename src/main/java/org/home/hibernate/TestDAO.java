package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.home.hibernate.dao.TaskDAOImpl;
import org.home.hibernate.entity.Task;

import java.util.List;

@Log4j2
public class TestDAO {
    public static void Test(){
//        UserDAOImpl userDAO = new UserDAOImpl();
//        User user = userDAO.get(20028L);
//        user.setEmail("user122200@#mail.ru");
//        user.setUsername("user122200");
//        user.setPassword("password");
//        userDAO.update(user);
//        log.info("user.getId()=" + user.getId());

        TaskDAOImpl taskDAO = new TaskDAOImpl();
        List<Task> tasks = taskDAO.find(true, "10033@mail.ru");
        log.info("task.getId()=" + tasks);
        
        HibernateUtil.close();
    }
}
