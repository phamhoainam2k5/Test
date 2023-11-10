package com.example.rental_house_project.Model;

public class House {
    private int width;
    private String timeRental;
    private String imgHouse;
    private int userId;
    private int houseId;
    private String houseName;
    private String price;
    private String address;
    private double revenue;
    private int numberBath;
    private int numberBed;
    private String describeHouse;
    private String status;

    public House(int houseId, int userId, String imgHouse, String houseName, String price, String timeRental, String address, int revenue, int numberBath, int numberBed, int width, String describeHouse, String status) {
        this.houseId = houseId;
        this.userId = userId;
        this.imgHouse = imgHouse;
        this.houseName = houseName;
        this.price = price;
        this.timeRental = timeRental;
        this.address = address;
        this.revenue = revenue;
        this.numberBath = numberBath;
        this.numberBed = numberBed;
        this.width = width;
        this.describeHouse = describeHouse;
        this.status = status;
    }

    public House(String houseName, String price, String address, double revenue, int numberBath, int numberBed, String describeHouse, String status) {
        this.houseName = houseName;
        this.price = price;
        this.address = address;
        this.revenue = revenue;
        this.numberBath = numberBath;
        this.numberBed = numberBed;
        this.describeHouse = describeHouse;
        this.status = status;
    }

    public House(int houseId, String houseName, String price, String address, double revenue, int numberBath, int numberBed, String describeHouse, String status) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.price = price;
        this.address = address;
        this.revenue = revenue;
        this.numberBath = numberBath;
        this.numberBed = numberBed;
        this.describeHouse = describeHouse;
        this.status = status;
    }

    public House(String imgHouse, String houseName, String address, int numberBath, int numberBed, String describeHouse) {
        this.imgHouse = imgHouse;
        this.houseName = houseName;
        this.address = address;
        this.numberBath = numberBath;
        this.numberBed = numberBed;
        this.describeHouse = describeHouse;
    }

    public House(String imgHouse, int houseId, String houseName, String address, int numberBath, int numberBed, String describeHouse) {
        this.imgHouse = imgHouse;
        this.houseId = houseId;
        this.houseName = houseName;
        this.address = address;
        this.numberBath = numberBath;
        this.numberBed = numberBed;
        this.describeHouse = describeHouse;
    }

    public House(int houseId, String houseName, String address, int numberBath, int numberBed, String describeHouse, String imgHouse) {
        this.imgHouse = imgHouse;
        this.houseId = houseId;
        this.houseName = houseName;
        this.address = address;
        this.numberBath = numberBath;
        this.numberBed = numberBed;
        this.describeHouse = describeHouse;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getNumberBath() {
        return numberBath;
    }

    public void setNumberBath(int numberBath) {
        this.numberBath = numberBath;
    }

    public int getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(int numberBed) {
        this.numberBed = numberBed;
    }

    public String getDescribeHouse() {
        return describeHouse;
    }

    public void setDescribeHouse(String describeHouse) {
        this.describeHouse = describeHouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTimeRental() {
        return timeRental;
    }

    public void setTimeRental(String timeRental) {
        this.timeRental = timeRental;
    }

    public String getImgHouse() {
        return imgHouse;
    }

    public void setImgHouse(String imgHouse) {
        this.imgHouse = imgHouse;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFormattedPrice() {
        if (!price.equals("Thảo thuận")) {
            if (Integer.parseInt(price) >= 1000000) {
                int priceInMillions = Integer.parseInt(price) / 1000000;
                return String.format("%d Triệu", priceInMillions);
            } else {
                int priceInHundred = Integer.parseInt(price) / 1000;
                return String.format("%dK", priceInHundred);
            }
        } else {
            return price;
        }
    }
}

