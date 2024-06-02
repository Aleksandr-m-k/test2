package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;
import web.service.CarServiceImpl;
import web.model.Car;

import java.util.List;

@Controller
public class CarsController {
    private CarService carService;


    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public String printСars(@RequestParam(value = "count", defaultValue = "5") int count, Model model) {
        List<Car> listCarsByCount = carService.getCarsByNumber(count);
        model.addAttribute("cars", listCarsByCount);
        return "cars";
    }
}
