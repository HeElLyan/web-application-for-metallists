package ru.he.repositoriesJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.he.models.entities.Token;

import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String value);
}
