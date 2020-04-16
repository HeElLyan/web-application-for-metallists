//package ru.he.repositoriesEntityManager.impl;
//
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import ru.he.models.entities.Token;
//import ru.he.repositoriesEntityManager.TokensRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class TokensRepositoryJpaImpl implements TokensRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional
//    public void save(Token token) {
////        entityManager.getTransaction().begin();
//        entityManager.persist(token);
////        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void update(Token entity) {
//        entityManager.getTransaction().begin();
//        entityManager.merge(entity);
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void delete(Long id) {
//        Token token = entityManager.find(Token.class, id);
//        entityManager.getTransaction().begin();
//        entityManager.remove(token);
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public List findAll() {
//        Query query = entityManager.createQuery("from Token", Token.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public Optional<Token> find(Long id) {
//        Token token = entityManager.find(Token.class, id);
//        return Optional.ofNullable(token);
//    }
//
//    @Override
//    public Optional<Token> findByValue(String value) {
//        Token token = entityManager.find(Token.class, value);
//        return Optional.empty();
//    }
//}
//
