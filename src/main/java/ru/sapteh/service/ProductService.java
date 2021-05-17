package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Product;


import java.util.List;

public class ProductService implements DAO<Product,Integer> {
    private SessionFactory sessionFactory;
    public ProductService(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Product> readByAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Product",Product.class).list();
        }

    }

    @Override
    public Product read(Integer integer) {
        try(Session session = sessionFactory.openSession()){
            return session.get(Product.class,integer);
        }
    }

    @Override
    public void create(Product object) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }

    }

    @Override
    public void update(Product object) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        }

    }

    @Override
    public void delete(Product object) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        }

    }
}