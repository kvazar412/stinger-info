package org.dtmhapcs.model.services.db.dao.interfaces;

import java.util.List;

import org.dtmhapcs.model.User;

public interface UserDao {
    void createOrUpdateUser (User user);
    User readUserById (String userId);
    List<User> readAllUsers ();
    void deleteUser(String userId);
}