package id.soc.repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import id.soc.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<Integer> calculateAgeByUserId(Long id) {
        return findByIdOptional(id)
                .map(User::getDateOfBirth)
                .map(dob -> Period.between(dob, LocalDate.now()).getYears());
    }

}
