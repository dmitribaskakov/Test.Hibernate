package org.home.hibernate.dao.interfaces.objects;

import org.home.hibernate.dao.interfaces.CommonDAO;
import org.home.hibernate.dao.interfaces.FindDAO;
import org.home.hibernate.entity.Task;

import java.util.List;

public interface TaskDAO extends CommonDAO<Task>, FindDAO<Task> {

    // найти все завершенные или незавершенные задачи по любому пользователю
    List<Task> find(boolean completed, String email);
}
