package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDaoImpl implements CarDao {

    public List<Car> getCarsByNumber(int count) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("BMV", 5, 2012));
        cars.add(new Car("WV", 7, 2014));
        cars.add(new Car("Lada", 2110, 2006));
        cars.add(new Car("mercedes-benz", 123, 2007));
        cars.add(new Car("Kia", 5, 2017));
        if (count > 5) {
            return cars;
        } else {
            return cars.stream().limit(count).collect(Collectors.toList());
        }
    }
}

