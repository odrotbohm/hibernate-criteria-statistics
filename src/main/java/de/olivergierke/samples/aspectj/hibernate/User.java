package de.olivergierke.samples.aspectj.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Oliver Gierke
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastname;


    protected User() {

    }


    /**
     * @param lastname
     */
    public User(String lastname) {

        this.lastname = lastname;
    }
}
