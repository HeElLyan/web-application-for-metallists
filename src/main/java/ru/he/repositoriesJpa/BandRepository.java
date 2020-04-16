package ru.he.repositoriesJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.he.models.entities.Band;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    Optional<Band> findByName(String name);
    Optional<Band> findById(Long id);
//    Optional<Band> findByUserId(Long id);
}
