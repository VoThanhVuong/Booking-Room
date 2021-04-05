/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vuong
 */
public class CartDTO {

    String customerName;
    Map<String, DetailDTO> cart;

    public CartDTO() {
    }

    public CartDTO(String customerName, Map<String, DetailDTO> cart) {
        this.customerName = customerName;
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, DetailDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, DetailDTO> cart) {
        this.cart = cart;
    }

    public void add(DetailDTO dto) {
        if (cart == null) {
            this.cart = new HashMap<String, DetailDTO>();
        }
      
        cart.put(dto.getRoomID()+dto.getCheckIn(), dto);
    }

    public void delete(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(DetailDTO dto) {
        if (this.cart != null) {
            if (this.cart.containsKey(dto.getDetailID())) {
                this.cart.replace(dto.getDetailID(), dto);
            }
        }
    }
//    public boolean checkDetail(Date checkIn, Date checkOut){
//        
//    }
}
