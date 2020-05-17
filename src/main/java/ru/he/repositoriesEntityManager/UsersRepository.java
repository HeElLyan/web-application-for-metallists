package ru.he.repositoriesEntityManager;

import org.springframework.stereotype.Repository;
import ru.he.models.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Long, User> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmId(String confirmedId);

    Optional<User> findByUsername(String username);

}
