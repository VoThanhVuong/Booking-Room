/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDAO;
import DAO.RoomDAO;
import DAO.TypeDAO;
import DAO.UserDAO;
import DTO.CartDTO;
import DTO.DetailDTO;
import DTO.GooglePojo;
import DTO.OrderDTO;
import DTO.RoomDTO;
import DTO.TypeDTO;
import DTO.UserDTO;
import UTILS.GoogleUtils;
import UTILS.VerifyUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vuong
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String SUCCESS = "manageRoom.jsp";
    private static final String ERROR = "invalid.html";
    private static final String USER = "view.jsp";
    private static final String BOOKING = "booking.jsp";
    private static final String LOGIN = "login.html";

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
        Boolean valid = false;
        try {
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            UserDTO user = dao.checkLorgin(userID, password);
            if (session.getAttribute("ALL ROOM") == null) {
                RoomDAO rDao = new RoomDAO();
                List<RoomDTO> list = rDao.getAllRoomAvailability();
                session.setAttribute("ALL ROOM", list);
            }
            if (session.getAttribute("LIST TYPE") == null) {
                TypeDAO tDao = new TypeDAO();
                List<TypeDTO> list = tDao.getAllType();
                session.setAttribute("LIST TYPE", list);
            }
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            valid = VerifyUtils.verify(gRecaptchaResponse);
            if (user != null && valid) {
                session.setAttribute("USER", user);
                if (user.getRoleID().equals("AD")) {
                    url = SUCCESS;
                }
                if (user.getRoleID().equals("US")) {
                    if (session.getAttribute("ROOM") == null) {
                        url = USER;
                    } else {
                        url = BOOKING;
                    }
                }
            } else {
                String code = request.getParameter("code");
                if (code == null || code.isEmpty()) {
                    url = LOGIN;
                } else {
                    String accessToken = GoogleUtils.getToken(code);
                    GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                    userID = googlePojo.getId();
                    user = dao.checkLorgin(userID);
                    if (user == null) {
                        String email = googlePojo.getEmail();
                        Date date = new Date();
                        user = new UserDTO(userID, email, null, null, email, null, null, date, null, null, true, "US");
                        dao.createUser(userID, date, email);
                    }
                    session.setAttribute("USER", user);
                    if (session.getAttribute("ROOM") == null) {
                        url = USER;
                    } else {
                        url = BOOKING;
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response); // Chuyen trang
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
