package org.home.hibernate.dao.interfaces.objects;

import org.home.hibernate.dao.interfaces.CommonDAO;
import org.home.hibernate.entity.Activity;

public interface ActivityDAO extends CommonDAO<Activity> {

        // получение активности конкретного пользователя (несколькими способами)
        Activity getByUser(String email);
}
