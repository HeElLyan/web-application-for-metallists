package ru.he.repositoriesEntityManager.impl;

import org.springframework.stereotype.Component;
import ru.he.models.entities.User;
import ru.he.repositoriesEntityManager.UsersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJpaImpl implements UsersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
//        Query query = entityManager.createQuery("from User", User.class);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> rootAuditorium = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(rootAuditorium);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    @Transactional
    public Optional<User> find(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

//    @Override
//    @Transactional
//    public Optional<User> findByEmail(String email) {
//        User user = entityManager.find(User.class, email);
//        return Optional.ofNullable(user);
//    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), params));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, email);

        List<User> queryResult = query.getResultList();

        User returnUser = null;

        if (!queryResult.isEmpty()) {
            returnUser = queryResult.get(0);
        }

        return Optional.of(returnUser);
    }

    @Override
    @Transactional
    public Optional<User> findByConfirmId(String confirmedId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), params));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, confirmedId);

        List<User> queryResult = query.getResultList();

        User returnUser = null;

        if (!queryResult.isEmpty()) {
            returnUser = queryResult.get(0);
        }

        return Optional.of(returnUser);
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), params));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, username);

        List<User> queryResult = query.getResultList();

        User returnUser = null;

        if (!queryResult.isEmpty()) {
            returnUser = queryResult.get(0);
        }

        return Optional.of(returnUser);
    }

//    @Override
//    @Transactional
//    public Optional<User> findByConfirmId(String confirmedId) {
//        User user = entityManager.find(User.class, confirmedId);
//        return Optional.ofNullable(user);
//    }



//    @Override
//    @Transactional
//    public Optional<Auditorium> findByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Auditorium> criteriaQuery = criteriaBuilder.createQuery(Auditorium.class);
//        Root<Auditorium> root = criteriaQuery.from(Auditorium.class);
//        criteriaQuery.select(root);
//
//        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
//        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), params));
//
//        TypedQuery<Auditorium> query = entityManager.createQuery(criteriaQuery);
//        query.setParameter(params, name);
//
//        List<Auditorium> queryResult = query.getResultList();
//
//        Auditorium returnUser = null;
//
//        if (!queryResult.isEmpty()) {
//            returnUser = queryResult.get(0);
//        }
//        return Optional.of(returnUser);
//    }
}
