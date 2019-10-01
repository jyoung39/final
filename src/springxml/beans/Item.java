package springxml.beans;

import springxml.services.LogService;

public class Item {
    private int id;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private String imagePath;
    private LogService log;

    public Item() {
    }

    public Item(LogService ls){
        this.log = ls;
    }

    public Item(int id, String name, String description, double price, String path){
        this.setId(id);
        this.setItemName(name);
        this.setItemDescription(description);
        this.setItemPrice(price);
        this.setImagePath(path);
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

    public void setLog(String l) {
        log.setLog(l);
    }

    public void displayMessage(){
        System.out.println(log.getLog());
    }
}
