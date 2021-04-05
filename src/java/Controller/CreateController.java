/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import DTO.UserErrorDTO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vuong
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String SUCCESS = "login.html";
    private static final String ERROR = "create.jsp";

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
        UserErrorDTO errorObject = new UserErrorDTO("", "", "", "", "", "", "", "", "", "");
        UserDTO user = new UserDTO();
        try {
            boolean check = true;
            String userID = request.getParameter("txtUserID");
            String userName = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String phone = request.getParameter("txtPhone");
            String email = request.getParameter("txtEmail");
            String address = request.getParameter("txtAddress");
            String birth = request.getParameter("txtBirthday");
            Date birthday = null;
            Date dateOfCreate = new Date();
            String numberIDCard = request.getParameter("txtNumberIDCard");
            String numberAccountBank = request.getParameter("txtNumberAccountBank");
            Boolean availability = true;
            String roleID = "US";
            UserDAO dao = new UserDAO();
            if (userID.isEmpty()) {
                check = false;
                errorObject.setUserIDError("User ID is not empty!");
            } else if (!userID.matches("[\\w]+")) {
                check = false;
                errorObject.setUserIDError("User ID is not contain special character!");
            }
            if (userName.isEmpty()) {
                check = false;
                errorObject.setUserNameError("User Name is not empty!");
            } else if (!userName.trim().matches("^[a-zA-Z0-9_ ]*$")) {
                check = false;
                errorObject.setUserNameError("User Name is not contain special character!");
            }
            if (password.equals("")) {
                check = false;
                errorObject.setPasswordError("Password is not empty!");
            } else if (!password.equals(confirm)) {
                check = false;
                errorObject.setConfirmError("Confirm password is wrong");
            }

            if (phone.isEmpty()) {
                check = false;
                errorObject.setPhoneError("Phone is not empty!");
            } else if (!phone.matches("[0-9]+")) {
                check = false;
                errorObject.setPhoneError("Phone is contain number!");
            }

            if (!email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$") && !email.isEmpty()) {
                check = false;
                errorObject.setEmailError("Your email is wrong");
            }
            if (birth.isEmpty()) {
                check = false;
                errorObject.setBirthdayError("Birthday is not empty!");
            } else {
                if (!birth.matches("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}")) {
                    check = false;
                    errorObject.setBirthdayError("Enter your Birthday is following the format 'DD-MM-YY'!");
                } else {
                    String ar[] = birth.split("-|/");
                    if (Integer.parseInt(ar[1]) > 12 || Integer.parseInt(ar[0]) > 31 || Integer.parseInt(ar[0]) < 1 || Integer.parseInt(ar[1]) < 1) {
                        check = false;
                        errorObject.setBirthdayError("Enter your Birthday is following the format 'DD-MM-YY'!");
                    } else if (Integer.parseInt(ar[2]) < 1950 || Integer.parseInt(ar[2]) > 2002) {
                        check = false;
                        errorObject.setBirthdayError("Your age not conform for booking!");
                    } else {
                        birthday = dao.convertStringToDate(birth);
                    }
                }
            }
            if (numberIDCard.isEmpty()) {
                check = false;
                errorObject.setNumberIDCardError("ID Card is not empty!");
            } else if (!numberIDCard.matches("[0-9]+")) {
                check = false;
                errorObject.setNumberIDCardError("Number ID Card is contain number!");
            }
            if (!numberAccountBank.matches("[0-9]+") && !numberAccountBank.isEmpty()) {
                check = false;
                errorObject.setNumberAccountBankError("Number Account Bank is contain number!");
            }
            if (check) {
                user = new UserDTO(userID, userName, password, phone, email, address, birthday, dateOfCreate, numberIDCard, numberAccountBank, availability, roleID);
                dao.createUser(user);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR USER", errorObject);
            }
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                errorObject.setUserIDError("UserID is duplicate!");
                request.setAttribute("ERROR USER", errorObject);
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
