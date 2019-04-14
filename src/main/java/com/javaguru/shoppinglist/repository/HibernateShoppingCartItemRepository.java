package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.domain.ShoppingCartItem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateShoppingCartItemRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateShoppingCartItemRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(ShoppingCartItem shoppingCartItem) {
        sessionFactory.getCurrentSession().save(shoppingCartItem);
        return shoppingCartItem.getId();
    }

    public List<ShoppingCartItem> getItemsInShoppingCart(ShoppingCart shoppingCart) {
        return sessionFactory.getCurrentSession().createCriteria(ShoppingCartItem.class)
                .add(Restrictions.eq("shoppingCart.id", shoppingCart.getId())).list();
    }

    public Optional<ShoppingCartItem> findById(Long id) {
        ShoppingCartItem shoppingCartItem = (ShoppingCartItem) sessionFactory.getCurrentSession()
                .createCriteria(ShoppingCartItem.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(shoppingCartItem);
    }

    public void delete(ShoppingCartItem shoppingCartItem) {
        sessionFactory.getCurrentSession().delete(shoppingCartItem);
    }

    public void deleteItems(List<ShoppingCartItem> items) {
        Session session = sessionFactory.getCurrentSession();
        items.forEach(session::delete);
    }
}
