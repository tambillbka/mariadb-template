package com.tampn.mariadbtemplate.repositories;

import com.tampn.mariadbtemplate.entities.Account;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    final EntityManager entityManager;

    @Autowired
    public CustomAccountRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Account> listByCodeOrName(String code, String name) {
        // Create criteria builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

        // Root is model map in database
        Root<Account> root = criteriaQuery.from(Account.class);

        // Add predicate
        if (Strings.isNotBlank(code)) {
            Predicate codePredicate = criteriaBuilder.equal(root.get("code"), code);
            criteriaQuery.where(codePredicate);
        }

        if (Strings.isNotBlank(name)) {
            Predicate namePredicate = criteriaBuilder.equal(root.get("name"), name);
            criteriaQuery.where(namePredicate);
        }

        // Create query
        TypedQuery<Account> query = entityManager.createQuery(criteriaQuery);

        // Query result
        return query.getResultList();
    }
}
