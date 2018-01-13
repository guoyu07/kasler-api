package net.kasler.webserver.dao;

import net.kasler.webserver.domain.User;

import java.util.List;

/**
 * CRUD
 */
public interface UserDao {
    // Create
    User create(User newUser);
    // Read
    User findOneById(int userId);
    List<User> findAll();

    // Update
    User update(User user);
    // Delete
    void delete(User user);

    User updateUserDisplayName(User user);
}
