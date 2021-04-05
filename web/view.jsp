<%-- 
    Document   : view
    Created on : Oct 24, 2020, 12:42:44 PM
    Author     : vuong
--%>


<%@page import="DTO.RoomDTO"%>
<%@page import="DTO.TypeDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
    </head>
    <body>
        <h1>Thanh Vuong Hotel</h1>
        <%
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null) {
        %>
        <form action="MainController" method="POST">
            <a href="MainController?btnAction=login">Login</a>
            <a href="MainController?btnAction=create account">Create Account</a>
            <a href="MainController?btnAction=cart">Check and Confirm Room</a>
        </form>
        <%
        } else {
        %>
        <h2>Welcome: <%=user.getUserName()%></h2>
        <a href="MainController?btnAction=logout">logout</a>
        <a href="MainController?btnAction=cart">Check and Confirm Room</a>
        <%
            }
            List<TypeDTO> listType = (List<TypeDTO>) session.getAttribute("LIST TYPE");
        %>
        <%
            String checkIn = request.getParameter("CheckIn") == null ? null : request.getParameter("CheckIn");
            String checkOut = request.getParameter("CheckOut") == null ? null : request.getParameter("CheckOut");
            String typeID = request.getParameter("cbbType") == null ? "%" : request.getParameter("cbbType");
            String typeName = "";
            if (listType != null) {
                for (TypeDTO type : listType) {
                    if (type.getTypeID().equalsIgnoreCase(typeID)) {
                        typeName = type.getTypeName();
                    }
                }
            }
            String price = request.getParameter("rangePrice") == null ? "50000" : request.getParameter("rangePrice");
        %>

        <form action="SearchController">
            <input type="date" name="CheckIn" value="<%=checkIn%>"/>
            <input type="date" name="CheckOut" value="<%=checkOut%>"/>
            Choose Type Room: <select name="cbbType" >
                <%
                    if (typeID.equalsIgnoreCase("%")) {
                %>
                <option value="%">---Choose Type---</option>
                <%
                } else {
                %>
                <option value="<%=typeID%>"><%=typeName%></option>
                <option value="%">---Choose Type---</option>
                <%
                    }
                    if (listType != null) {
                        for (TypeDTO type : listType) {
                            if (!type.getTypeID().equalsIgnoreCase(typeID)) {

                %>
                <option value="<%=type.getTypeID()%>"><%=type.getTypeName()%></option>
                <%}
                        }
                    }%>
            </select>
            500 <input type="range" max="50000" min="500" step="10" name="rangePrice" value="<%=price%>"/> 50000
            <input type="submit" value="Search"/>
        </form>
        <%
            List<RoomDTO> list = (List<RoomDTO>) request.getAttribute("LIST");
            int i = 1;
            if (list == null) {
                list = (List<RoomDTO>) session.getAttribute("ALL ROOM");
            }
            for (RoomDTO room : list) {
        %>
        <div>
            <a href="MainController?btnAction=View Room&roomID=<%=room.getRoomID()%>&checkIn=<%=request.getParameter("CheckIn")%>&checkOut=<%=request.getParameter("CheckOut")%>">
                <img src="<%=room.getImage()%>" alt="Room<%=i++%>" width="200px" height="200px"/>
            </a>
            <div>
                <%
                    if (listType != null) {
                        for (TypeDTO type : listType) {
                            if (type.getTypeID().equalsIgnoreCase(room.getTypeID()))
                %>
                <i><%=type.getTypeName()%> - For: <%=room.getMaxOfPeople()%> people</i><br>
                <%
                            break;
                        }
                    }
                %>
                <%=room.getDescribe()%><br>
                <b><%=room.getPrice()%>$/night</b>
            </div>
        </div>

        <%
            }%>
    </body>
</html>
