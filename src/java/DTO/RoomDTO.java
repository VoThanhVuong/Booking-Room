/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author vuong
 */
public class RoomDTO {
    private String roomID;
    private String typeID;
    private float price;
    private int maxOfPeople;
    private String describe;
    private String image;
    private boolean availability;

    public RoomDTO() {
    }

    public RoomDTO(String roomID, String typeID, float price, int maxOfPeople, String describe, String image, boolean availability) {
        this.roomID = roomID;
        this.typeID = typeID;
        this.price = price;
        this.maxOfPeople = maxOfPeople;
        this.describe = describe;
        this.image = image;
        this.availability = availability;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxOfPeople() {
        return maxOfPeople;
    }

    public void setMaxOfPeople(int maxOfPeople) {
        this.maxOfPeople = maxOfPeople;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    
}
