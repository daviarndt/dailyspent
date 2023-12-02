package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "phone")
@Entity(name = "phone")
public class PhoneModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;
    private String countryCode;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public PhoneModel(Long phoneId, String countryCode, String phoneNumber, UserModel user) {
        this.phoneId = phoneId;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneModel that = (PhoneModel) o;
        return Objects.equals(phoneId, that.phoneId) && Objects.equals(countryCode, that.countryCode) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, countryCode, phoneNumber, user);
    }
}
