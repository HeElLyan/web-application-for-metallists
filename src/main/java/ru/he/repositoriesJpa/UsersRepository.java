package ru.he.repositoriesJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.he.dto.InformationDto;
import ru.he.models.entities.User;

import java.util.List;
import java.util.Optional;

//видит как компонент
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmId(String confirmId);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findAllByUsername(String username);

//    @Query("select new ru.he.dto.InformationDto(user.name, (sum(document.size) / 1024 / 1024) ) from User user left join user.documents " +
//            "as document where user.id = :userId group by user.id")
//    InformationDto getInformationByUser(@Param("userId") Long userId);
}
