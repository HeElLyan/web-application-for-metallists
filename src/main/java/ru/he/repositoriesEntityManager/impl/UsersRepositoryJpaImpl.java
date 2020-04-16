//package ru.he.repositoriesEntityManager.impl;
//
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import ru.he.models.entities.User;
//import ru.he.repositoriesEntityManager.UsersRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class UsersRepositoryJpaImpl implements UsersRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional
//    public void save(User user) {
////        entityManager.getTransaction().begin();
//        entityManager.persist(user);
////        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void update(User entity) {
//        entityManager.getTransaction().begin();
//        entityManager.merge(entity);
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void delete(Long id) {
//        User user = entityManager.find(User.class, id);
//        entityManager.getTransaction().begin();
//        entityManager.remove(user);
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public List findAll() {
//        Query query = entityManager.createQuery("from User", User.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public Optional<User> find(Long id) {
//        User user = entityManager.find(User.class, id);
//        return Optional.ofNullable(user);
//    }
//
//    @Override
//    public Optional<User> findByEmail(String email) {
//        User user = entityManager.find(User.class, email);
//        return Optional.ofNullable(user);
//    }
//
//    @Override
//    public Optional<User> findByConfirmId(String confirmedId) {
//        User user = entityManager.find(User.class, confirmedId);
//        return Optional.ofNullable(user);
//    }
//}
