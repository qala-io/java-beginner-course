package io.qala.javabeginner.domain;

public class User {
    private String email, firstName, lastName;

    public User(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return email;
    }
}
