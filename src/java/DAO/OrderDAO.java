/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DetailDTO;
import UTILS.DBIUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import DTO.OrderDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuong
 */
public class OrderDAO {

    public String convertDateToString(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(d);
    }

    public Date convertStringToDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(s);
    }

    public void createOrder(OrderDTO order) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Insert into tblOrder (OrderID, UserID, Date, QuantityRoom, TotalPrice, StatusPayment) VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, order.getOrderID());
                stm.setString(2, order.getUserID());
                stm.setString(3, convertDateToString(order.getDate()));
                stm.setString(4, Integer.toString(order.getQuantitiRoom()));
                stm.setString(5, Float.toString(order.getTotalPrice()));
                stm.setBoolean(6, true);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
//    public int saveCart(List<DetailDTO> list, OrderDTO order) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stm = null;
//        int result = 0;
//        try {
//            conn = DBIUtils.getConnnection();
//            if (conn != null) {
//                String sql = "Insert into tblOrder (OrderID, UserID, Date, QuantityRoom, TotalPrice, StatusPayment) VALUES(?,?,?,?,?,?)";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, order.getOrderID());
//                stm.setString(2, order.getUserID());
//                stm.setString(3, convertDateToString(order.getDate()));
//                stm.setString(4, Integer.toString(order.getQuantitiRoom()));
//                stm.setString(5, Float.toString(order.getTotalPrice()));
//                stm.setBoolean(6, true);
//                result = stm.executeUpdate();
//                if(result>0){
//                    for (DetailDTO dto : list) {
//                    sql = "Insert into tblOrderDetail (OrderID, RoomID, CheckIn, CheckOut) VALUES(?,?,?,?)";
//                    stm = conn.prepareStatement(sql);
//                    stm.setString(1, dto.getOrderID());
//                    stm.setString(2, dto.getRoomID());
//                    stm.setString(3, dto.getCheckIn());
//                    stm.setString(4, dto.getCheckOut());
//                    result = stm.executeUpdate();
//                }
//                }
//            }
//        } catch (Exception e) {
//            conn.rollback();
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return result;
//    }


    public void updateOrder(OrderDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Update tblOrder set OrderID = ? , UserID = ?, Date = ?, QuantityRoom = ?, TotalPrice = ?, StatusPayment = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getOrderID());
                stm.setString(2, dto.getUserID());
                stm.setString(3, convertDateToString(dto.getDate()));
                stm.setString(4, Integer.toString(dto.getQuantitiRoom()));
                stm.setString(5, Boolean.toString(dto.isStatusPayment()));
                stm.setString(6, Float.toString(dto.getTotalPrice()));
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<DetailDTO> getListDetailByOrderID(String orderID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<DetailDTO> result = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select DetailID, OrderID, RoomID, CheckIn, CheckOut from tblOrderDetail where OrderID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    DetailDTO dto = new DetailDTO(rs.getString("OrderID"), rs.getString("RoomID"), rs.getDate("CheckIn"),
                            rs.getDate("CheckOut"));
                    result.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
