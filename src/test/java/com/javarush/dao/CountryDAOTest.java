package com.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryDAOTest {

    private static SessionFactory sessionFactory = null;
    private Session session = null;
    CountryDAO countryDAO = Mockito.mock(CountryDAO.class);

    @BeforeAll
    static void setup() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure().build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(CountryDAO.class)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
    }

    @BeforeEach
    void setupSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void doTransaction() {
        session.getTransaction().commit();
    }

    @AfterAll
    static void closeBD() {
        sessionFactory.close();
    }

    @Test
    public void testGetAll(){
        countryDAO.getAll();
        Mockito.verify(countryDAO).getAll();
    }

}