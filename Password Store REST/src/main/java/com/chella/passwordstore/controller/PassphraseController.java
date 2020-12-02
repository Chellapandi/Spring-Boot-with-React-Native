package com.chella.passwordstore.controller;

import com.chella.passwordstore.Constants;
import com.chella.passwordstore.model.Passphrase;
import com.chella.passwordstore.repository.PassphraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PassphraseController {

    @Autowired
    private PassphraseRepository repository;

    @RequestMapping(value = "getpass", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Passphrase password) {
        if (repository.login(password))
            return new ResponseEntity<>(Constants.LOGIN_SUCCESSFUL, HttpStatus.OK);
        return new ResponseEntity<>(Constants.LOGIN_FAILURE, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "storepass", method = RequestMethod.POST)
    public ResponseEntity<String> register(Passphrase password) {
        if (repository.register(password)) {
            return new ResponseEntity<>(Constants.REGISTRATION_SUCCESSFUL, HttpStatus.OK);
        }
        return new ResponseEntity<>(Constants.ALREADY_REGISTERED, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<List<Passphrase>> getAllPassphrase() {
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePassphrase(@PathVariable String id) {
        repository.delete(id);
    }
}
