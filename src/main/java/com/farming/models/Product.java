package com.farming.models;


import javax.persistence.*;

@Entity
@Table(name = "Farmer_Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    private String productName;
    private Integer quantity;
    private String priceQuantity;
    private String productDescription;
    private String imagePath; // This can be used to store the image path in the database
    private String farmerEmail;// // This will store the email of the farmer who adds the product

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPriceQuantity() {
        return priceQuantity;
    }

    public void setPriceQuantity(String priceQuantity) {
        this.priceQuantity = priceQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFarmerEmail() {
        return farmerEmail;
    }

    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }
}