package ru.he.repositoriesJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.he.models.entities.FileInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    List<FileInfo> findAll();

    List<FileInfo> findAllByEmail(String email);

    Optional<FileInfo> findByStorageFileName(String storageFileName);

}
