package com.example.dailyspent.phone;

import com.example.dailyspent.phone.dto.SavePhoneDTO;
import com.example.dailyspent.user.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;

@Table(name = "phone")
@Entity(name = "phone")
@EqualsAndHashCode(of = "phoneId")
public class PhoneModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Size(min = 1, max = 4)
    @NotBlank(message = "Country Code is mandatory")
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @NotBlank(message = "Phone Number is mandatory")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public PhoneModel() {
    }

    public PhoneModel(SavePhoneDTO savePhoneDTO) {
        this.countryCode = savePhoneDTO.countryCode();
        this.phoneNumber = savePhoneDTO.phoneNumber();
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
