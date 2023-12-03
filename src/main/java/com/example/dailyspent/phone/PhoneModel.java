package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Table(name = "phone")
@Entity(name = "phone")
@EqualsAndHashCode(of = "phoneId")
public class PhoneModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public PhoneModel() {
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
