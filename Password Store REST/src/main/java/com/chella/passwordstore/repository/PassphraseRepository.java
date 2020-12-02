package com.chella.passwordstore.repository;

import com.chella.passwordstore.model.Passphrase;
import com.chella.passwordstore.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PassphraseRepository {

    @Autowired
    private EntityManager entityManager;

    public boolean login(Passphrase password) {
        Passphrase passwordFromDB = entityManager.find(Passphrase.class, password.getUsername());
        if (null != passwordFromDB) {
            return password.getUsername().equals(passwordFromDB.getUsername()) &&
                    password.getPassword().equals(passwordFromDB.getPassword());
        }

        return false;
    }

    /**
     * Method to register user. Here we generate SHA-256 hashvalue for uniqueness of user id.
     *
     * @param password
     * @return return true if user register successfully.
     */
    public boolean register(Passphrase password) {
        Passphrase passwordFromDB = entityManager.find(Passphrase.class, password.getUsername());
        if (null == passwordFromDB) {
            password.setId(Utils.getHashString(password.getUsername() + "-" + password.getSiteName()));
            entityManager.persist(password);
            return true;
        }
        return false;
    }

    public Passphrase getPassphrase(String id) {
        Passphrase passwordFromDB = entityManager.find(Passphrase.class, id);
        return passwordFromDB;
    }

    /**
     * This method uses JPQL to retrieve all the passphrase from the user.
     *
     * @return List of Passphrase
     */
    public List<Passphrase> getAll() {
        return entityManager.createQuery("select p from Passphrase p", Passphrase.class).getResultList();
    }

    public void delete(String id) {
        Passphrase passwordFromDB = entityManager.find(Passphrase.class, id);
        entityManager.remove(passwordFromDB);
    }
}
