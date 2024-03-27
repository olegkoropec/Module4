package com.javarush.mapper;

import com.javarush.domain.City;
import com.javarush.mapper.MapperFromCityToCitycountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MapperFromCityToCitycountryTest {

    @Test
    void testTransformData() {
        MapperFromCityToCitycountry mapper = Mockito.mock(MapperFromCityToCitycountry.class);
        City city1 = new City();
        City city2 = new City();
        City city3 = new City();

        mapper.transformData(List.of(city1, city2, city3));
        Mockito.verify(mapper).transformData(List.of(city1, city2, city3));
    }
}