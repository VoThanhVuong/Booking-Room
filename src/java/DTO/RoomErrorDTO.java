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
public class RoomErrorDTO {
    private String roomIDError;
    private String typeIDError;
    private String priceError;
    private String describeError;
    private String imageError;

    public RoomErrorDTO() {
    }

    public RoomErrorDTO(String roomIDError, String typeIDError, String priceError, String describeError, String imageError) {
        this.roomIDError = roomIDError;
        this.typeIDError = typeIDError;
        this.priceError = priceError;
        this.describeError = describeError;
        this.imageError = imageError;
    }

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getTypeIDError() {
        return typeIDError;
    }

    public void setTypeIDError(String typeIDError) {
        this.typeIDError = typeIDError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getDescribeError() {
        return describeError;
    }

    public void setDescribeError(String describeError) {
        this.describeError = describeError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
    
}
