package org.trotsky.springboot.dao;

import org.trotsky.springboot.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void saveUser(User user) throws SQLException;

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    User show(long id);

    void update(long id, User user);
}
