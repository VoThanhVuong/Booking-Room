/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetailDAO;
import DAO.OrderDAO;
import DTO.CartDTO;
import DTO.DetailDTO;
import DTO.OrderDTO;
import DTO.UserDTO;
import UTILS.EmailUtility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vuong
 */
public class ConfirmController extends HttpServlet {

    private static final String SUCCESS = "view.jsp";
    private static final String ERROL = "cart.jsp";

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
        String url = ERROL;
        try {
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            OrderDAO oDAO = new OrderDAO();
            DetailDAO dDAO = new DetailDAO();
            int quantity = 0;
            float total = 0;
            for (DetailDTO dto : cart.getCart().values()) {
                total += dto.getIntoMoney();
                quantity++;
            }
            OrderDTO order = (OrderDTO) session.getAttribute("ORDER");
            order.setQuantitiRoom(quantity);
            order.setStatusPayment(true);
            order.setTotalPrice(total);
            oDAO.createOrder(order);
            for (DetailDTO dto : cart.getCart().values()) {
                dDAO.createDetail(dto, order.getOrderID());
            }
//            dDAO.createDetail((List<DetailDTO>) cart.getCart().values());
            session.setAttribute("ORDER", null);
            session.setAttribute("CART", null);
            if(user!=null&&user.getUserName().equals(user.getEmail())){
                EmailUtility.sendEmail(user.getEmail(), "Thanh Vuong Hotel's notification!!!", "Booking Thanh Vuong Hotel Successfull");
            }
            url = SUCCESS;
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
