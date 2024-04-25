package com.javarush;

import com.javarush.redis.CityCountry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PrepareAndTestingTest {
    private static SessionFactory sessionFactory = null;
    private Session session = null;
    PrepareAndTesting testing = Mockito.mock(PrepareAndTesting.class);

    @BeforeAll
    static void setup() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure().build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(PrepareAndTesting.class)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void testShutdown() {
        testing.shutdown();
        Mockito.verify(testing).shutdown();
    }

    @Test
    void testFetchData() {
        PrepareAndTesting prepareAndTesting = new PrepareAndTesting();
        testing.fetchData(prepareAndTesting);
        Mockito.verify(testing).fetchData(prepareAndTesting);
    }

    @Test
    void testPrepareRedisClient(){
        testing.prepareRedisClient();
        Mockito.verify(testing).prepareRedisClient();
    }

    @Test
    void pushToRedis() {
        CityCountry cityCountry1 = new CityCountry();
        CityCountry cityCountry2 = new CityCountry();
        CityCountry cityCountry3 = new CityCountry();

        List<CityCountry> cityCountries = List.of(cityCountry1, cityCountry2, cityCountry3);
        testing.pushToRedis(cityCountries);
        Mockito.verify(testing).pushToRedis(cityCountries);
    }

    @Test
    void testRedisDataTest() {
        List<Integer> integerList = List.of(1,2,3,4);
        testing.retrieveAndProcessRedisData(integerList);
        Mockito.verify(testing).retrieveAndProcessRedisData(integerList);
    }

    @Test
    void testMysqlData() {
        List<Integer> integerList = List.of(1,2,3,4,5);
        testing.retrieveAndProcessCityData(integerList);
        Mockito.verify(testing).retrieveAndProcessCityData(integerList);
    }
}