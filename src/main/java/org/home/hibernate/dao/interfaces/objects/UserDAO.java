package org.home.hibernate.dao.interfaces.objects;

import org.home.hibernate.dao.interfaces.CommonDAO;
import org.home.hibernate.dao.interfaces.FindDAO;
import org.home.hibernate.entity.User;

public interface UserDAO extends CommonDAO<User>, FindDAO<User> {

    // получение только одного пользователя по email
    User getByEmail(String email);
}
