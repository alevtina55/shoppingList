package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateShoppingCartRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateShoppingCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(ShoppingCart shoppingCart) {
        sessionFactory.getCurrentSession().save(shoppingCart);
        return shoppingCart.getId();
    }

    public Optional<ShoppingCart> findShoppingCartById(Long id) {
        ShoppingCart shoppingCart = (ShoppingCart) sessionFactory.getCurrentSession()
                .createCriteria(ShoppingCart.class).add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(shoppingCart);
    }

    public void delete(ShoppingCart shoppingCart) {
        sessionFactory.getCurrentSession().delete(shoppingCart);
    }
}
