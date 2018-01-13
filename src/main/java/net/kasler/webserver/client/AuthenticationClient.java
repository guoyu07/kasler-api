package net.kasler.webserver.client;

import net.kasler.webserver.domain.User;

public interface AuthenticationClient {
    User findUserByAuthKey(String authKey);
}
