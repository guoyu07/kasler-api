package net.kasler.webserver.client;

import net.kasler.webserver.dao.UserDao;
import net.kasler.webserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthenticationClientImpl implements AuthenticationClient {


    @Autowired
    AuthenticationClientImpl(UserDao userDao) {
        authMap.put("admin", userDao.findOneById(1));
    }

    Map<String, User> authMap = new ConcurrentHashMap<>();

    @Override
    public User findUserByAuthKey(String authKey) {
        return authMap.get(authKey);
    }
}
