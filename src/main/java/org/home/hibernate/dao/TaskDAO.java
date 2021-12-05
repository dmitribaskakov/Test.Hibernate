package org.home.hibernate.dao;

import org.home.hibernate.entity.Task;

import java.util.List;

public interface TaskDAO extends CommonDAO<Task>{

    List<Task> find(boolean completed, String email);
}
