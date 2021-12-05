package org.home.hibernate.dao.interfaces.objects;

import org.home.hibernate.entity.Stat;

public interface StatDAO {

    // получение статистики конкретного пользователя (несколькими способами)
    Stat getByUser(String email);
}
