package com.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.entity.AppUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public AppUser findActiveByUsername(String username) {
        List<AppUser> list = em.createQuery(
                "SELECT u FROM AppUser u WHERE u.username = :username AND u.isActive = 1",
                AppUser.class)
                .setParameter("username", username)
                .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }
}
