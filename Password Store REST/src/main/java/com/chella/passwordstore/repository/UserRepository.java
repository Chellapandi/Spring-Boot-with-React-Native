package com.chella.passwordstore.repository;

import com.chella.passwordstore.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private EntityManager em;

    public boolean login(User user) {
        User userFromDB = em.find(User.class, user.getUsername());
        if (null != userFromDB) {
            return user.getUsername().equals(userFromDB.getUsername()) &&
                    user.getPassword().equals(userFromDB.getPassword());
        }
        return false;
    }


    public boolean register(User user) {
        User userFromDB = em.find(User.class, user.getUsername());
        if (null == userFromDB) {
            em.persist(user);
            return true;
        }
        return false;
    }

}
