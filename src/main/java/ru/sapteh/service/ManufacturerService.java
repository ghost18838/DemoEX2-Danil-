package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Manufacturer;

import java.util.List;

public class ManufacturerService implements DAO<Manufacturer,Integer> {
    private SessionFactory sessionFactory;
    public ManufacturerService(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Manufacturer> readByAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Manufacturer",Manufacturer.class).list();
        }

    }

    @Override
    public Manufacturer read(Integer integer) {
        try(Session session = sessionFactory.openSession()){
            return session.get(Manufacturer.class,integer);
        }
    }

    @Override
    public void create(Manufacturer object) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }

    }

    @Override
    public void update(Manufacturer object) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        }

    }

    @Override
    public void delete(Manufacturer object) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        }

    }
}
