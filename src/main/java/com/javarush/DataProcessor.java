package com.javarush;

import com.javarush.domain.City;
import com.javarush.mapper.MapperFromCityToCitycountry;
import com.javarush.redis.CityCountry;

import java.util.List;

public class DataProcessor {
    public void processAndTestData(List<Integer> ids){
        PrepareAndTesting prepareAndTesting = new PrepareAndTesting();
        MapperFromCityToCitycountry fromCityToCitycountry = new MapperFromCityToCitycountry();
        List<City> allCities = prepareAndTesting.fetchData(prepareAndTesting);
        List<CityCountry> preparedData = fromCityToCitycountry.transformData(allCities);
        prepareAndTesting.pushToRedis(preparedData);
        prepareAndTesting.sessionFactory.getCurrentSession().close();
        long startRedis = System.currentTimeMillis();
        prepareAndTesting.retrieveAndProcessRedisData(ids);
        long stopRedis = System.currentTimeMillis();
        long startMysql = System.currentTimeMillis();
        prepareAndTesting.retrieveAndProcessCityData(ids);
        long stopMysql = System.currentTimeMillis();
        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
        prepareAndTesting.shutdown();
    }
}
