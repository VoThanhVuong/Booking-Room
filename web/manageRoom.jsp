<%-- 
    Document   : manage.jsp
    Created on : Oct 29, 2020, 5:13:10 PM
    Author     : vuong
--%>


<%@page import="DTO.UserDTO"%>
<%@page import="DTO.RoomDTO"%>
<%@page import="DTO.TypeDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("USER");
        %>
        <h1>Welcome <%=user.getUserName() == null ? "admin" : user.getUserName()%></h1>
        <a href="MainController?btnAction=Logout">Logout</a><br>
        <!--        <a href="MainController?btnAction=Management User">Management User</a><br>-->
        <a href="MainController?btnAction=Create Room">Create New Room</a>
        <%String nameSearch = request.getParameter("txtSearchRoom");
            String s = nameSearch == null ? "" : nameSearch;%>
        <form action="SearchRoomController">
            Search <input type="text" name="txtSearchRoom" value="<%= s%>"/>
            <input type="submit" value="Search Room"/>
        </form>
        <%
            List<TypeDTO> listType = (List<TypeDTO>) session.getAttribute("LIST TYPE");
            List<RoomDTO> list = (List<RoomDTO>) request.getAttribute("LIST ROOM");
            int count = 0;
            if (list == null) {
                list = (List<RoomDTO>) session.getAttribute("ALL ROOM");
            }
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Room ID</th>
                    <th>Type ID</th>
                    <th>Price</th>
                    <th>Max</th>
                    <th>Describe</th>
                    <th>Image</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>

            <%
                for (RoomDTO room : list) {
            %>
            <form action="MainController">
                <tr>
                    <td><%= ++count%></td>
                    <td>
                        <%= room.getRoomID()%>
                        <input type="hidden" name="txtRoomID" value="<%= room.getRoomID()%>"/>
                    </td>
                    <td>
                        <%
                            String typeName = "";
                            if (listType != null) {
                                for (TypeDTO type : listType) {
                                    if (type.getTypeID().equalsIgnoreCase(room.getTypeID())) {
                                        typeName = type.getTypeName();
                                    }
                                }
                            }
                        %>
                        <select name="cbbType" >
                            <option value="<%=room.getTypeID()%>"><%=typeName%></option>
                            <%
                                if (listType != null) {
                                    for (TypeDTO type : listType) {
                                        if (!type.getTypeID().equalsIgnoreCase(room.getTypeID())) {

                            %>
                            <option value="<%=type.getTypeID()%>"><%=type.getTypeName()%></option>
                            <%}
                                    }
                                }%>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="txtPrice" value="<%= room.getPrice()%>"/>
                    </td>
                    <td>
                        <input type="number" name="txtMaxOfPeople" value="<%= room.getMaxOfPeople()%>" min="1"/>
                    </td>
                    <td>
                        <input type="text" name="txtDescribe" value="<%= room.getDescribe()%>"/>
                    </td>
                    <td>
                        <input type="hidden" name="image" value="<%= room.getImage()%>"/>
                        <img src="<%=room.getImage()%>" alt="Room<%=count%>" width="50px" height="50px"/>
                        <input type="file" name="fileImage" accept="image/png, image/jpeg"/> 
                        <input type="reset" value="Reset">
                    </td>
                    <td>
                        <a href="MainController?btnAction=Delete Room&roomID=<%=room.getRoomID()%>&txtSearchRoom=<%= s%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="txtSearchRoom" value="<%=s%>"/>
                        <input type="submit" name="btnAction" value="Update Room"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>


        </table>

    </body>
</html>
