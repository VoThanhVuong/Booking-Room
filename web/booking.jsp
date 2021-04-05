<%-- 
    Document   : booking.jsp
    Created on : Oct 29, 2020, 4:43:10 PM
    Author     : vuong
--%>


<%@page import="DTO.RoomDTO"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Page</title>
    </head>
    <body>
        <h1>Thanh Vuong Hotel</h1>
        <%
            UserDTO user =(UserDTO) session.getAttribute("USER");
        %>
        <h2><%=user.getUserName()%></h2>
        <a href="MainController?btnAction=Logout">Logout</a>
        <a href="MainController?btnAction=View">Different Room</a>
        <%  
            RoomDTO dto = (RoomDTO) session.getAttribute("ROOM");
        %>
        <div>
            <img src="<%=dto.getImage()%>" alt="Room" width="300px" height="300px"/><br>
            <i><%=dto.getTypeID()%> - For: <%=dto.getMaxOfPeople()%> people</i><br>
            <b><%=dto.getPrice()%>$/night</b><br>

        </div>
        <%
            if (request.getAttribute("MESSAGE") != null) {
        %>
        <h3><%=request.getAttribute("MESSAGE")%></h3>
        <a href="MainController?btnAction=View">Booking more!</a>
        <a href="MainController?btnAction=Cart">Check and Confirm</a>
        <%
        } else {
        %>
        <form action="MainController" method="POST">
            Check in: <input type="date" name="CheckIn" value="<%=session.getAttribute("CHECKIN") == null ? "" : session.getAttribute("CHECKIN")%>"/>
            Check out: <input type="date" name="CheckOut" value="<%=session.getAttribute("CHECKOUT") == null ? "" : session.getAttribute("CHECKOUT")%>" /><br>
            <input type="submit" name="btnAction" value="Booking"/>
        </form>
        <%
            }
        %>
        <%
            if (request.getAttribute("ERROR") != null) {
        %>
        <h3><%=request.getAttribute("ERROR")%></h3>
        <%
            }
        %>
    </body>
</html>