/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author vuong
 */
public class OrderDTO {
    private String orderID;
    private String userID;
    private Date date;
    private int quantitiRoom;
    private boolean statusPayment;
    private float totalPrice;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, Date date, int quantitiRoom, boolean statusPayment, float totalPrice) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.quantitiRoom = quantitiRoom;
        this.statusPayment = statusPayment;
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantitiRoom() {
        return quantitiRoom;
    }

    public void setQuantitiRoom(int quantitiRoom) {
        this.quantitiRoom = quantitiRoom;
    }

    public boolean isStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(boolean statusPayment) {
        this.statusPayment = statusPayment;
    }
    

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
