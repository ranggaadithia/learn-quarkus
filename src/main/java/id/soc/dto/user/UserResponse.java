package id.soc.dto.user;

import java.time.LocalDate;

public class UserResponse {

    public Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public UserResponse(Long id, String name, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

}
