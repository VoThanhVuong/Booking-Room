/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vuong
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.html";
    private static final String LOGIN = "LoginController";
    private static final String ERROR = "invalid.html";
    private static final String CREATE_PAGE = "create.jsp";
    private static final String CREATE = "CreateController";
    private static final String VIEW_ROOM = "RoomController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH_ROOM = "SearchRoomController";
    private static final String SEARCH = "SearchController";
    private static final String UPDATE_ROOM = "UpdateRoomController";
    private static final String DELETE_ROOM = "DeleteRoomController";
    private static final String DELETE = "DeleteController";
    private static final String VIEW = "view.jsp";
    private static final String VIEW_CART = "cart.jsp";
    private static final String BOOKING = "BookingController";
    private static final String CONFIRM = "ConfirmController";
    private static final String CREATE_ROOM = "createRoom.jsp";
    private static final String NEW_ROOM = "CreateRoomController";

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
        RequestDispatcher r = null;
        String url = ERROR;
        try {
            String action = request.getParameter("btnAction");
            if (action.equals("Login Account")) {
                url = LOGIN_PAGE;
            } else if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Create Account".equals(action)) {
                url = CREATE_PAGE;
            } else if ("Create".equals(action)) {
                url = CREATE;
            } else if ("View Room".equals(action)) {
                url = VIEW_ROOM;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Search Room".equals(action)) {
                url = SEARCH_ROOM;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if ("Delete Room".equals(action)) {
                url = DELETE_ROOM;
            } else if ("Update Room".equals(action)) {
                url = UPDATE_ROOM;
            } else if ("View".equals(action)) {
                url = VIEW;
            } else if ("Cart".equals(action)) {
                url = VIEW_CART;
            } else if ("Booking".equals(action)) {
                url = BOOKING;
            } else if ("Confirm".equals(action)) {
                url = CONFIRM;
            } else if ("Create Room".equals(action)) {
                url = CREATE_ROOM;
            } else if ("New Room".equals(action)) {
                url = NEW_ROOM;
            } else if ("Delete".equals(action)) {
                url = DELETE;
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
