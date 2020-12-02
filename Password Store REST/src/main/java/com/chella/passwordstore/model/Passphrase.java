package com.chella.passwordstore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class Passphrase {

    @Id
    private String id;
    private String username;
    private String password;
    private String siteName;
}
