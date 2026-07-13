package ng.ourChemist.data.models;

public class DispensedDrug {
    private int totalPrice;
    private Drug drug;
    private int quantity;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice){
        this.totalPrice= totalPrice;
    }

}
