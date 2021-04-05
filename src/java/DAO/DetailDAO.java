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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vuong
 */
public class DetailDAO {

    public String convertDateToString(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return formatter.format(d);
    }

    public Date convertStringToDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(s);
    }

    public void createDetail(DetailDTO dto, String orderID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Insert into tblOrderDetail (OrderID, RoomID, CheckIn, CheckOut) VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, dto.getRoomID());
                stm.setString(3, dto.getCheckIn());
                stm.setString(4, dto.getCheckOut());
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

    

    public List<DetailDTO> getListDetailByDetailID(String detailID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<DetailDTO> result = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select DetailID, OrderID, RoomID, CheckIn, CheckOut from tblOrderDetail where DetailID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, detailID + "%");
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
