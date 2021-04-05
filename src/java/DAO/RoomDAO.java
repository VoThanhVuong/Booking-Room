/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.RoomDTO;
import UTILS.DBIUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author vuong
 */
public class RoomDAO {

    public String convertDateToString(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(d);
    }

    public List<RoomDTO> getAllRoomAvailability() throws SQLException {
        List<RoomDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select RoomID, TypeID, Price, MaxOfPeople, Describe, Image from tblRoom "
                        + "where Availability = 'true'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("RoomID");
                    String typeID = rs.getString("TypeID");
                    float price = Float.parseFloat(rs.getString("Price"));
                    int maxOfPeople = Integer.parseInt(rs.getString("MaxOfPeople"));
                    String describe = rs.getString("Describe");
                    String image = rs.getString("Image");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new RoomDTO(roomID, typeID, price, maxOfPeople, describe, image, true));
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

    public RoomDTO getRoomByID(String roomID) throws SQLException {
        RoomDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select RoomID, TypeID, Price, MaxOfPeople, Describe, Image from tblRoom where RoomID = ? and Availability = 'true'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, roomID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return result = new RoomDTO(roomID, rs.getString("TypeID"), Float.parseFloat(rs.getString("Price")),
                            Integer.parseInt(rs.getString("MaxOfPeople")), rs.getString("Describe"), rs.getString("Image"), true);
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

    public List<RoomDTO> getListRoomByID(String id) throws SQLException {
        List<RoomDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select RoomID, TypeID, Price, MaxOfPeople, Describe, Image from tblRoom where RoomID like ? and Availability = 'true'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + id + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("RoomID");
                    String typeID = rs.getString("TypeID");
                    float price = Float.parseFloat(rs.getString("Price"));
                    int maxOfPeople = Integer.parseInt(rs.getString("MaxOfPeople"));
                    String describe = rs.getString("Describe");
                    String image = rs.getString("Image");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new RoomDTO(roomID, typeID, price, maxOfPeople, describe, image, true));
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

    public List<RoomDTO> findRoom(String checkIn, String checkOut, String type, String pri) throws SQLException {
        List<RoomDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select RoomID, TypeID, Price, MaxOfPeople, Describe, Image from tblRoom "
                        + "where Availability = 'true' and Price <= ? and TypeID like ? and RoomID "
                        + "not in (select RoomID from tblOrderDetail where (checkIn <= ? and checkIn >= ?) "
                        + "or (checkOut <= ? and checkOut >= ?) or (checkIn <= ? and checkOut >= ?))";
                stm = conn.prepareStatement(sql);
                stm.setString(1, pri);
                stm.setString(2, type);
                stm.setString(3, checkOut);
                stm.setString(4, checkIn);
                stm.setString(5, checkOut);
                stm.setString(6, checkIn);
                stm.setString(7, checkIn);
                stm.setString(8, checkOut);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("RoomID");
                    String typeID = rs.getString("TypeID");
                    float price = Float.parseFloat(rs.getString("Price"));
                    int maxOfPeople = Integer.parseInt(rs.getString("MaxOfPeople"));
                    String describe = rs.getString("Describe");
                    String image = rs.getString("Image");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new RoomDTO(roomID, typeID, price, maxOfPeople, describe, image, true));
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

    public void updateRoom(RoomDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Update tblRoom "
                        + "set TypeID = ?, Price = ?, MaxOfPeople = ?, Describe = ?, Image = ? "
                        + "where RoomID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getTypeID());
                stm.setString(2, Float.toString(dto.getPrice()));
                stm.setString(3, Integer.toString(dto.getMaxOfPeople()));
                stm.setString(4, dto.getDescribe());
                stm.setString(5, dto.getImage());
                stm.setString(6, dto.getRoomID());
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

    public void deleteRoom(String roomID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Update tblRoom "
                        + "set Availability = 'false' "
                        + "where RoomID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, roomID);
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

    public void createRoom(RoomDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Insert into tblRoom (roomID, typeID, price, maxOfPeople, describe, image, availability) VALUES(?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getRoomID());
                stm.setString(2, dto.getTypeID());
                stm.setFloat(3, dto.getPrice());
                stm.setInt(4, dto.getMaxOfPeople());
                stm.setString(5, dto.getDescribe());
                stm.setString(6, dto.getImage());
                stm.setBoolean(7, true);
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
    public boolean checkRoom(String roomID, Date checkIn, Date checkOut) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        Boolean result = false;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select DetailID from tblOrderDetail where roomID = ? and ((checkIn <= ? and checkIn >= ?) "
                        + "or (checkOut <= ? and checkOut >= ?) or (checkIn <= ? and checkOut >= ?))";
                stm = conn.prepareStatement(sql);
                stm.setString(1, roomID);
                stm.setString(2, convertDateToString(checkOut));
                stm.setString(3, convertDateToString(checkIn));
                stm.setString(4, convertDateToString(checkOut));
                stm.setString(5, convertDateToString(checkIn));
                stm.setString(6, convertDateToString(checkIn));
                stm.setString(7, convertDateToString(checkOut));
                stm.setString(4, convertDateToString(checkOut));
                stm.setString(5, convertDateToString(checkIn));
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = true;
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
