package com.parking.parkinglot2.ejb;

import com.parking.parkinglot2.common.UserDto;
import com.parking.parkinglot2.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<UserDto> copyUsersToDto(List<User> usersList) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : usersList) {
            UserDto userDto = new UserDto(
                    user.getUsername(), // Username from Users entity
                    user.getEmail()     // Email from Users entity
            );
            userDtos.add(userDto);
        }
        return userDtos;
    }
}