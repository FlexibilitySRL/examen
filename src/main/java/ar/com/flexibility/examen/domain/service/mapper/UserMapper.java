package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.app.api.UserApi;
import ar.com.flexibility.examen.domain.model.Authority;
import ar.com.flexibility.examen.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity User and its Api called UserApi.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    public UserApi userToUserApi(User user) {
        return new UserApi(user);
    }

    public List<UserApi> usersToUserApis(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserApi)
            .collect(Collectors.toList());
    }

    public User userApiToUser(UserApi userApi) {
        if (userApi == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userApi.getId());
            user.setLogin(userApi.getLogin());
            user.setFirstName(userApi.getFirstName());
            user.setLastName(userApi.getLastName());
            user.setEmail(userApi.getEmail());
            user.setImageUrl(userApi.getImageUrl());
            user.setActivated(userApi.isActivated());
            user.setLangKey(userApi.getLangKey());
            Set<Authority> authorities = this.authoritiesFromStrings(userApi.getAuthorities());
            if (authorities != null) {
                user.setAuthorities(authorities);
            }
            return user;
        }
    }

    public List<User> userApisToUsers(List<UserApi> userApis) {
        return userApis.stream()
            .filter(Objects::nonNull)
            .map(this::userApiToUser)
            .collect(Collectors.toList());
    }

    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    public Set<Authority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }
}
