package com.example.dailyspent.user;

import com.example.dailyspent.expense.ExpenseModel;
import com.example.dailyspent.phone.PhoneModel;
import com.example.dailyspent.user.dto.SaveUserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "user")
@Entity(name = "user")
@EqualsAndHashCode(of = "userId")
public class UserModel implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "First Name is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Valid
    private List<PhoneModel> phoneNumbers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Valid
    private List<ExpenseModel> expenses = new ArrayList<>();

    public UserModel() {}

    public UserModel(SaveUserDTO saveUserDTO) {
        this.firstName = saveUserDTO.firstName();
        this.lastName = saveUserDTO.lastName();
        this.email = saveUserDTO.email();
        this.password = new BCryptPasswordEncoder().encode(saveUserDTO.password());
        this.phoneNumbers = saveUserDTO.phoneNumbers().stream().map(PhoneModel::new).toList();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<PhoneModel> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneModel> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<ExpenseModel> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseModel> expenses) {
        this.expenses = expenses;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
