package org.dtmhapcs.model.services.db.dao.interfaces;

import java.util.List;

import org.dtmhapcs.model.User;

public interface UserDao {
    void createOrUpdate (User user);
    User readUserById (String userId);
    List<User> readAllUsers ();
    void deleteUser(String userId);
}