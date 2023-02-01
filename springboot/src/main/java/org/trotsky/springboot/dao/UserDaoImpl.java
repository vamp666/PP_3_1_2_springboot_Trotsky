package org.trotsky.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trotsky.springboot.entity.User;

import java.util.List;
@Component
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {

    }

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public User show(long id) {
        jakarta.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<User> cq = cb.createQuery(User.class);
        jakarta.persistence.criteria.Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        User userToUpdate = em.find(User.class, id);
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRoles(user.getRoles());
        em.merge(userToUpdate);
    }

}
