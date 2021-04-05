/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.RoomDAO;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vuong
 */
public class DetailDTO {

    private String detailID;
    private String orderID;
    private String roomID;
    private Date checkIn;
    private Date checkOut;
    private float intoMoney;
    RoomDAO dao = new RoomDAO();

    public DetailDTO() {
    }

    public DetailDTO(String orderID, String roomID, Date checkIn, Date checkOut) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.roomID = roomID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public float getIntoMoney() throws SQLException {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(checkIn);
        cal2.setTime(checkOut);
        long numberOfDay = ((cal2.getTime().getTime()-cal1.getTime().getTime()) / (24 * 3600 * 1000));
        float price = dao.getRoomByID(roomID).getPrice();
        return price*numberOfDay;
    }

    public String getCheckIn() {
        return dao.convertDateToString(checkIn);
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return dao.convertDateToString(checkOut);
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

}
