package com.assecor.data.dao;

import com.assecor.model.Person;
import com.assecor.model.jpa.PersonJpa;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


/**
@Component("daoDB") und sollte sich noch extends DaoEntity<Person> haben
 **/
@NoArgsConstructor
public class DaoPersonDatabaseImpl  {

    /**
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("assecorrestapp");
    }

    @Override
    public void addPerson(Person person) {

        PersonJpa personJpa = new PersonJpa();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            personJpa.setFirstName(person.getFirstName());
            personJpa.setLastName(person.getLastName());
            personJpa.setZipCode(person.getZipCode());
            personJpa.setStreet(person.getStreet());
            personJpa.setColor(person.getColor());

            entityManager.persist(personJpa);
            transaction.commit();

        } catch (PersistenceException per){

            per.printStackTrace();

            throw new PersistenceException("Error: " + per.getMessage(), per);


        } catch (Exception ex){

            if(transaction != null){
                transaction.rollback();
            }

            ex.printStackTrace();

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List getPersonsByColor(String color) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT u FROM PersonJpa u WHERE u.color = :color";
        TypedQuery<PersonJpa> typedQuery = entityManager.createQuery(query, PersonJpa.class);

        List<PersonJpa> results = null;
        try{
            results = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return results;
    }

    @Override
    public Optional<Person> getPersonById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

            String query = "SELECT p FROM PersonJpa WHERE p.id= :person_id";
            TypedQuery<PersonJpa> typedQuery = entityManager.createQuery(query, PersonJpa.class);
            typedQuery.setParameter("person_id", id);

            PersonJpa result = null;

            try{
                result = typedQuery.getSingleResult();

            } catch (NoResultException ex) {
                ex.printStackTrace();
                throw new NoResultException("Person with id : " + id + " is not in database");
            }
            finally {
                entityManager.close();
            }
            return Optional.ofNullable(result);
    }

    @Override
    public List getPersons() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT p FROM PersonJpa p";
        TypedQuery<PersonJpa> typedQuery = entityManager.createQuery(query, PersonJpa.class);

        List<PersonJpa> results = null;
        try{
            results = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return results;
    }
    **/
}