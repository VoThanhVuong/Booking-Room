/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RoomDAO;
import DTO.RoomDTO;
import DTO.RoomErrorDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vuong
 */
public class CreateRoomController extends HttpServlet {

    private static final String SUCCESS = "manageRoom.jsp";
    private static final String ERROR = "createRoom.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        RoomErrorDTO errorObject = new RoomErrorDTO("", "", "", "", "");
        RoomDTO room = new RoomDTO();
        try {
            boolean check = true;
            String roomID = request.getParameter("txtRoomID");
            String typeID = request.getParameter("cbbType");
            String price = request.getParameter("txtPrice");
            int maxOfPeople = Integer.parseInt(request.getParameter("max"));
            String describe = request.getParameter("txtDescribe");
            String image = request.getParameter("fileImage");
            RoomDAO dao = new RoomDAO();
            if (roomID.isEmpty()) {
                check = false;
                errorObject.setRoomIDError("Room ID is not empty!");
            } else if (!roomID.matches("[\\w]+")) {
                check = false;
                errorObject.setRoomIDError("Room ID is not contain special character!");
            }
            if (typeID.equalsIgnoreCase("%")) {
                check = false;
                errorObject.setTypeIDError("Please choose room's type!");
            }
            if (price.isEmpty()) {
                check = false;
                errorObject.setPriceError("Price is not empty!");
            } else if (!price.matches("[0-9]+[.][0-9]+") && !price.matches("[0-9]+")) {
                check = false;
                errorObject.setPriceError("Float is contain number greater than 0!");
            }
            if (describe.isEmpty()) {
                check = false;
                errorObject.setDescribeError("Decribe is not empty!");
            }
            if (image == null || image == "") {
                check = false;
                errorObject.setImageError("Please upload image!");
            }
            if (check) {
                room = new RoomDTO(roomID, typeID, Float.parseFloat(price), maxOfPeople, describe, "Image_Web\\" + image, true);
                dao.createRoom(room);
                HttpSession session = request.getSession();
                RoomDAO rDao = new RoomDAO();
                List<RoomDTO> list = rDao.getAllRoomAvailability();
                session.setAttribute("ALL ROOM", list);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR ROOM", errorObject);
            }
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                errorObject.setRoomIDError("RoomID is duplicate!");
                request.setAttribute("ERROR ROOM", errorObject);
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
