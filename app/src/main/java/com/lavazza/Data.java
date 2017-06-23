package com.lavazza;

/**
 * Created by Krackerz4 on 21-Feb-17.
 */

public class Data {
    public String serialNo;
    public String date;
    public String time;
    public String quality;
    public String status;
    public String product_no;
    public String description;

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

//        public int imageId;
    public String imageId;

    public int cart;

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    //    Data(String person, String detail,String mail, int imageId) {
//        this.person = person;
//        this.detail = detail;
//        this.mail = mail;
//        this.imageId = imageId;
//    }
//
//    public Data() {
//
//    }


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public int getImageId() {
//        return imageId;
//    }
//
//    public void setImageId(int imageId) {
//        this.imageId = imageId;
//    }
}