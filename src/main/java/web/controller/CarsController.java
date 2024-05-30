package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.DAO.CarDao;
import web.DAO.CarDaoImpl;
import web.Service.CarService;
import web.Service.CarServiceImpl;
import web.model.Car;

import java.util.Arrays;
import java.util.List;

@Controller
public class CarsController {
    CarServiceImpl carServiceImpl;

    public CarsController(CarServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    @GetMapping(value = "/cars") //"/cars" -html adress: http://localhost:8080/cars
    public String printСars(@RequestParam(value = "count", defaultValue = "5") int count, Model model) {
        List<Car> listCarsByCount = carServiceImpl.getCarsByNumber(count);
        model.addAttribute("cars", listCarsByCount);
        return "cars";
    }
}
