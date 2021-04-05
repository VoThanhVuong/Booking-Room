<%-- 
    Document   : view
    Created on : Oct 12, 2020, 9:57:27 AM
    Author     : vuong
--%>

<%@page import="DTO.DetailDTO"%>
<%@page import="DTO.CartDTO"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Selected Room</title>
    </head>
    <body>
        <h1>Thanh Vuong Hotel</h1>
        <%
            UserDTO user = (UserDTO) session.getAttribute("USER");
        %>
        <h2><%=user.getUserName()%></h2>
        <a href="MainController?btnAction=Logout">Logout</a>
        <h2>Your booking cart:</h2>
        <%
            int no = 0;
            float total = 0;
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Room ID</th>
                    <th>Check In</th>
                    <th>Check Out</th>
                    <th>Into money</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (DetailDTO dto : cart.getCart().values()) {
                        total += dto.getIntoMoney();
                %>
            <form action="MainController">
                <tr>
                    <td><%= ++no%></td>
                    <td><%= dto.getRoomID()%></td>
                    <td><input type="date" name="CheckIn" value="<%=dto.getCheckIn()%>"/></td>
                    <td><input type="date" name="CheckOut" value="<%=dto.getCheckOut()%>"/></td>
                    <td><%=dto.getIntoMoney()%></td>
                    <td>
                        <input type="hidden" name="txtDetail" value="<%=dto.getRoomID()+dto.getCheckIn()%>"/>
                        <input type="submit" name="btnAction" value="Delete"/>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
        </table><br>
        <%
        }
    %>
    <%
        if (no > 0) {
    %>
    <h3>Thanks <%=user.getUserName()%> for booking</h3>
    <h3>Total: <%=total%>$ for <%=no%> room</h3>
    <input type="submit" name="btnAction" value="Confirm">
    <%
    } else if (no == 0) {
    %>
    <h3>Please booking more!</h3>
    <%
        }
    %>
    </form>
    <a href="view.jsp">Booking more</a>
</body>
</html>