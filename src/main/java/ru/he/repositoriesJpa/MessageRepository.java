package ru.he.repositoriesJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.entities.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserId(Long userId);

    List<Message> findAllByBandId(Long bandId);
}
