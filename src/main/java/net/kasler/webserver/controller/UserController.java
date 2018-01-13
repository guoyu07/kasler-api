package net.kasler.webserver.controller;

import net.kasler.webserver.client.AuthenticationClient;
import net.kasler.webserver.dao.UserDao;
import net.kasler.webserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserDao userDao;
    @Autowired
    AuthenticationClient authenticationClient;

    void authenUser(String apiKey) throws IllegalAccessException {
        User admin = userDao.findOneById(1);
        User userByAuthKey = authenticationClient.findUserByAuthKey(apiKey);
        if (!admin.equals(userByAuthKey)) {
            throw new IllegalAccessException("Just allow admin");
        }
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable(name = "id") Integer id, @RequestParam("api_key") String apiKey) throws IllegalAccessException {
        authenUser(apiKey);
        return userDao.findOneById(id);
    }

    @GetMapping("/**")
    List<User> getAllUsers(@RequestParam("api_key") String apiKey) throws IllegalAccessException {
        authenUser(apiKey);
        return userDao.findAll();
    }

    @PutMapping
    User updateUser(
            @RequestBody User user,
            @RequestParam("api_key") String apiKey
    ) throws IllegalAccessException {
        authenUser(apiKey);
        return userDao.update(user);
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable(name = "id") Integer id,
                  @RequestParam("api_key") String apiKey) throws IllegalAccessException {
        authenUser(apiKey);
        User oneById = userDao.findOneById(id);
        userDao.delete(oneById);
        return oneById.getDisplayName();
    }

    @PostMapping
    User create(@RequestBody User user, @RequestParam("api_key") String apiKey) throws IllegalAccessException {
        authenUser(apiKey);
        return userDao.create(user);
    }


}
