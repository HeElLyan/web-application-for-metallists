package ru.he.repositoriesJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.entities.Document;

public interface DocumentsRepository extends JpaRepository<Document, Long> {
}
