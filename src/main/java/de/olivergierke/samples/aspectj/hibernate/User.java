package de.olivergierke.samples.aspectj.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Sample entity to be persisted.
 * 
 * @author Oliver Gierke
 */
@Entity
@SuppressWarnings("unused")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastname;


    protected User() {

    }


    public User(String lastname) {

        this.lastname = lastname;
    }
}
