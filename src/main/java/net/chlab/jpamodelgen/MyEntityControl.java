package net.chlab.jpamodelgen;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class MyEntityControl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<MyEntity> findByText(String text) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyEntity> criteriaQuery = criteriaBuilder
                .createQuery(MyEntity.class);
        Root<MyEntity> from = criteriaQuery.from(MyEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(from.get(MyEntity_.text),
                text));
        CriteriaQuery<MyEntity> select = criteriaQuery.select(from);
        TypedQuery<MyEntity> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();
    }
}
