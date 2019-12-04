package edu.wctc;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item")
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int id;

    @NotNull(message = "required")
    @Column(name = "item_name")
    private String itemName;

    @NotNull(message = "required")
    @Column(name = "item_desc")
    private String itemDescription;

    @NotNull(message = "required")
    @Min(value = 1, message = "must be greater than 0")
    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "item_image")
    private String imagePath;

    public GroceryItem() {
    }

    public GroceryItem(String itemName, String itemDescription, double itemPrice, String imagePath) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
