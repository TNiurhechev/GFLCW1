package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Souvenir implements Serializable {
    private String name;
    private Manufacturer manufacturer;
    private LocalDate productionDate;
    private int price;

    public Souvenir(String name, Manufacturer manufacturer, LocalDate productionDate, int price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenir souvenir = (Souvenir) o;
        return price == souvenir.price && Objects.equals(name, souvenir.name)
                && Objects.equals(manufacturer, souvenir.manufacturer)
                && Objects.equals(productionDate, souvenir.productionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer, productionDate, price);
    }

    @Override
    public String toString() {
        return "Souvenir's name: " + name + ", manufacturer: " + manufacturer
                + ", production date: " + productionDate.toString() + ", price: " + price;
    }
}
