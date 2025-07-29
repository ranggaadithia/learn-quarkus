package id.soc.dto;

import java.time.LocalDate;

import id.soc.constraints.Adult;
import id.soc.constraints.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class UserData {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotNull
    @Past
    @Adult
    private LocalDate dateOfBirth;

    public UserData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
