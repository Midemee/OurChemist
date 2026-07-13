package ng.ourChemist.data.models;
import java.time.LocalDate;

public class Drug {
    private int id;
    private String name;
    private String brand;
    private LocalDate expiryDate;
    private int price;

    public Drug() {}

    public Drug(String name, String brand, LocalDate expiryDate, int price){
        this.name = name;
        this.brand = brand;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


