package web.model;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String modelCar;
    private int series;
    private int yearManufactureCar;

    public Car() {
    }

    public Car(String modelCar, int series, int yearManufactureCar) {
        this.modelCar = modelCar;
        this.series = series;
        this.yearManufactureCar = yearManufactureCar;
    }

    @Override
    public String toString() {
        return "Car{" +
                "modelCar='" + modelCar + '\'' +
                ", series=" + series +
                ", yearManufactureCar=" + yearManufactureCar +
                '}';
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getYearManufactureCar() {
        return yearManufactureCar;
    }

    public void setYearManufactureCar(int yearManufactureCar) {
        this.yearManufactureCar = yearManufactureCar;
    }
}
