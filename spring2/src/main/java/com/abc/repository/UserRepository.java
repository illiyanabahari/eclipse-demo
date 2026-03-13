package com.abc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.abc.entity.AppUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public AppUser findActiveByUsername(String username) {
        List<AppUser> list = em.createQuery(
                "SELECT u FROM AppUser u WHERE u.username = :username",
                AppUser.class)
                .setParameter("username", username)
                .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }
}

