package org.home.hibernate;

import lombok.extern.log4j.Log4j2;
import org.home.hibernate.dao.impl.*;
import org.home.hibernate.entity.*;

import java.util.Date;

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

//        TaskDAOImpl taskDAO = new TaskDAOImpl();
//        List<Task> tasks = taskDAO.find(true, "10033@mail.ru");
//        log.info("task.getId()=" + tasks);

//        создаем пользователя (триггеры создадут сразу же тестовые данные)
//        активируем пользователя (поле activated)
//        создаем новую категорию
//        создаем новый приоритет
//        создаем несколько новых задач (помимо тестовых) с новыми категорией и приоритетом
//        завершаем задачу
//        удаляем задачу
//        считываем статистику по любой категории пользователя
//        считываем общую статистику пользователя


//        // создаем пользователя (триггеры создадут сразу же тестовые данные)
//        User user = new User();
//        user.setUsername("testuser");
//        user.setPassword("testuser");
//        user.setEmail("testuser@gmail.com");
//
//        UserDAOImpl userDAO = new UserDAOImpl();
//        userDAO.add(user);
//
//        // активируем пользователя (поле activated)
//        ActivityDAOImpl activityDAO = new ActivityDAOImpl();
//        Activity activity = activityDAO.getByUser(user);
//        activity.setActivated(true);
//        activityDAO.update(activity);

        UserDAOImpl userDAO = new UserDAOImpl();

        User user = userDAO.get(20028L);


        // создаем справочные значения
        PriorityDAOImpl priorityDAO = new PriorityDAOImpl();

        Priority priority = new Priority();
        priority.setColor("#fff");
        priority.setTitle("Новый приоритет");
        priority.setUser(user);
        priorityDAO.add(priority);

        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

        Category category = new Category();
        category.setTitle("Новая категория");
        category.setUser(user);
        categoryDAO.add(category);

        TaskDAOImpl taskDAO = new TaskDAOImpl();

        Task task = new Task();
        task.setUser(user);
        task.setTitle("Супер новая задача222");
        task.setCategory(category);
        task.setPriority(priority);
        task.setTaskDate(new Date());
        task.setCompleted(false);
        taskDAO.add(task);

        task.setCompleted(true);
        taskDAO.update(task);

        taskDAO.delete(task.getId());

        StatDAOImpl statDAO = new StatDAOImpl();
        Stat stat = statDAO.getByUser(user.getEmail());

        log.info(stat.getCompletedTotal());

        log.info(category.getCompletedCount());

        HibernateUtil.close(); // закрыть Session Factory - очищается кеш 2го уровня
    }
}
