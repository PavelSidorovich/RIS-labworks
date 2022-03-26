package com.sidorovich.pavel.jsf.filter;

import java.util.Objects;

public class UserFilter {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer zip;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.isEmpty()? null : firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.isEmpty()? null : lastName;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.isEmpty()? null : email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserFilter that = (UserFilter) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) && Objects.equals(zip, that.zip) &&
               Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, zip, email);
    }

    @Override
    public String toString() {
        return "UserFilter{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", zip=" + zip +
               ", email='" + email + '\'' +
               '}';
    }

}
