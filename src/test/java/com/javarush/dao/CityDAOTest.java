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
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class CityDAOTest {
    private static SessionFactory sessionFactory = null;
    private Session session = null;
    CityDAO cityDAO = Mockito.mock(CityDAO.class);

    @BeforeAll
    static void setup() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure().build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(CityDAO.class)
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
    public void testGetItems(){
        cityDAO.getItems(0, 500);
        Mockito.verify(cityDAO).getItems(0, 500);
    }

    @Test
    public void testAllCities(){
        cityDAO.allCities();
        Mockito.verify(cityDAO).allCities();
    }

    @Test
    public void testGetTotalCount(){
        Mockito.doReturn(5000).when(cityDAO).getTotalCount();
        assertEquals(5000, cityDAO.getTotalCount());
    }

    @Test
    public void testGetById(){
        cityDAO.getById(5);
        Mockito.verify(cityDAO).getById(5);
    }

}