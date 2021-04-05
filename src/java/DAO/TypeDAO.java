/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TypeDTO;
import UTILS.DBIUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuong
 */
public class TypeDAO {

    public List<TypeDTO> getAllType() throws SQLException {
        List<TypeDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select TypeID ,TypeName from tblType";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String typeID = rs.getString("TypeID");
                    String typeName = rs.getString("TypeName");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new TypeDTO(typeID, typeName));
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
