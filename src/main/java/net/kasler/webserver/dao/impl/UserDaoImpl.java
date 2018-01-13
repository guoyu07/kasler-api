package net.kasler.webserver.dao.impl;

import net.kasler.webserver.dao.UserDao;
import net.kasler.webserver.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class UserDaoImpl implements UserDao {

    static final Map<Integer, User> storage = new ConcurrentHashMap<>();
    static final AtomicInteger idCounter = new AtomicInteger(0);

    static {
        int id = genId();
        persistUser(new User(id, "admin"));
    }

    static int genId() {
        return idCounter.incrementAndGet();
    }

    static User persistUser(User user) {
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public User create(User newUser) {
        if (newUser == null) {
            throw new NullPointerException("new user must be non-null");
        }
        int newId = genId();
        newUser.setId(newId);
        persistUser(newUser);
        return newUser;
    }

    @Override
    public User findOneById(int userId) {
        return storage.get(userId);
    }

    @Override
    public List<User> findAll() {
        return storage.values()
                .stream()
                .sorted((u1, u2) -> u1.getId() - u2.getId())
                .collect(Collectors.toList());
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new NullPointerException("user must be non-null");
        }
        int id = user.getId();
        User _user = findOneById(id);
        if (_user == null) {
            throw new IllegalArgumentException("User is not exists. " + id);
        }
        return persistUser(user);
    }

    @Override
    public void delete(User user) {
        storage.remove(user.getId());
    }

    @Override
    public User updateUserDisplayName(User user) {
        if (user == null) {
            throw new NullPointerException("user must be non-null");
        }
        int id = user.getId();
        User _user = findOneById(id);
        if (_user == null) {
            throw new IllegalArgumentException("User is not exists. " + id);
        }
        _user.setDisplayName(user.getDisplayName());
        return persistUser(_user);
    }
}
