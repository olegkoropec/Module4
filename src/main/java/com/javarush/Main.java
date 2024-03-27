package com.javarush;

import com.javarush.domain.City;
import com.javarush.redis.CityCountry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrepareAndTesting prepareAndTesting = new PrepareAndTesting();
        MapperFromCityToCitycountry fromCityToCitycountry = new MapperFromCityToCitycountry();
        List<City> allCities = prepareAndTesting.fetchData(prepareAndTesting);
        List<CityCountry> preparedData = fromCityToCitycountry.transformData(allCities);
        prepareAndTesting.pushToRedis(preparedData);
        prepareAndTesting.sessionFactory.getCurrentSession().close();
        List<Integer> ids = List.of(3, 254, 123, 4, 189, 89, 345, 118, 10, 102);
        long startRedis = System.currentTimeMillis();
        prepareAndTesting.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();
        long startMysql = System.currentTimeMillis();
        prepareAndTesting.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();
        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
        prepareAndTesting.shutdown();
    }
}
