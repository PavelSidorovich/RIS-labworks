package com.sidorovich.pavel.jsf.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "user_account")
public class User {

    public User() {
    }

    public User(Long id, String firstName,
                String lastName, Integer zip,
                String email, DiscountCode discountCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
        this.email = email;
        this.discountCode = discountCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 256)
    @Size(min = 2, max = 256, message = "Size should be in range (2 to 256)")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 256)
    @Size(min = 2, max = 256, message = "Size should be in range (2 to 256)")
    private String lastName;

    @Column(nullable = false)
    private Integer zip;

    @Column(nullable = false, length = 256)
    @Size(min = 2, max = 256, message = "Size should be in range (2 to 256)")
    private String email;

    @ManyToOne
    @JoinColumn(name = "discount_code", referencedColumnName = "code", nullable = false)
    private DiscountCode discountCode;

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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        this.email = email;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) && Objects.equals(zip, that.zip) &&
               Objects.equals(email, that.email) && Objects.equals(discountCode, that.discountCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, zip, email, discountCode);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", zip=" + zip +
               ", email='" + email + '\'' +
               ", discountCode=" + discountCode +
               '}';
    }

}