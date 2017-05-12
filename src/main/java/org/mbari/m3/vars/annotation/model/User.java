package org.mbari.m3.vars.annotation.model;

/**
 * @author Brian Schlining
 * @since 2017-05-11T13:37:00
 */
public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String affiliation;
    private String email;

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }
}
