/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetailDAO;
import DAO.RoomDAO;
import DTO.CartDTO;
import DTO.DetailDTO;
import DTO.OrderDTO;
import DTO.RoomDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vuong
 */
public class BookingController extends HttpServlet {

    private static final String ERROR = "booking.jsp";
    private static final String SUCCESS = "booking.jsp";

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
        try {
            HttpSession session = request.getSession();
            RoomDTO room = (RoomDTO) session.getAttribute("ROOM");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            CartDTO cart = ((CartDTO) session.getAttribute("CART"));
            if (cart == null) {
                cart = new CartDTO("", null);
            }
            DetailDAO dao = new DetailDAO();
            String orderID = "";
            Date date = new Date();
            OrderDTO order = (OrderDTO)session.getAttribute("ORDER");
            if(order==null){
                orderID = user.getUserID() + "_" + date.getTime();
                order = new OrderDTO(orderID, user.getUserID(), date, 0, false, 0);
            }
            String roomID = room.getRoomID();
            String checkIn = request.getParameter("CheckIn");
            String checkOut = request.getParameter("CheckOut");
            RoomDAO rDao = new RoomDAO();
            if (checkIn == "" || checkOut == "" || rDao.checkRoom(roomID, dao.convertStringToDate(checkIn), dao.convertStringToDate(checkOut)) || checkIn.compareTo(checkOut) >= 0) {
                request.setAttribute("ERROR", "Please choose check in and check out day!");
            } else {

                DetailDTO dto = new DetailDTO(orderID, roomID, dao.convertStringToDate(checkIn), dao.convertStringToDate(checkOut));
                cart.add(dto);
                session.setAttribute("CART", cart);
                request.setAttribute("MESSAGE", "You have bought: " + room.getRoomID() + " successfull!");
                session.setAttribute("ORDER", order);
                url = SUCCESS;
            }
        } catch (Exception e) {
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
