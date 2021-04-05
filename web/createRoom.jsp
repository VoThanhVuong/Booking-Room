<%-- 
    Document   : createRoom
    Created on : Nov 8, 2020, 4:55:50 PM
    Author     : vuong
--%>

<%@page import="DTO.RoomErrorDTO"%>
<%@page import="DTO.TypeDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Room Page</title>
    </head>
    <body>
        <%
            List<TypeDTO> listType = (List<TypeDTO>) session.getAttribute("LIST TYPE");
            RoomErrorDTO error=(RoomErrorDTO)request.getAttribute("ERROR ROOM");
            if(error==null){          
               error= new RoomErrorDTO("", "", "", "", "");
            }
            String typeID = request.getParameter("cbbType") == null ? "%" : request.getParameter("cbbType");
            String typeName = "";
            if (listType != null) {
                for (TypeDTO type : listType) {
                    if (type.getTypeID().equalsIgnoreCase(typeID)) {
                        typeName = type.getTypeName();
                    }
                }
            }
        %>
        <form action="MainController" method="POST">
            Room ID:<mark>(*)</mark> <input type="text" name="txtRoomID" value="<%=request.getParameter("txtRoomID")==null?"":request.getParameter("txtRoomID")%>"/>
            <%= error.getRoomIDError()%></br>
            Choose Type Room:<mark>(*)</mark> <select name="cbbType" >
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
            <%= error.getTypeIDError()%></br>
            Price:<mark>(*)</mark> <input type="text" name="txtPrice" value="<%=request.getParameter("txtPrice")==null?"":request.getParameter("txtPrice")%>"/>
            <%= error.getPriceError()%></br>
            Max of people:<mark>(*)</mark> <input type="number" name="max" value="<%=request.getParameter("max")==null?1:request.getParameter("max")%>" min="1"/><br>
            Describe:<mark>(*)</mark> <input type="text" name="txtDescribe" value="<%=request.getParameter("txtDescribe")==null?"":request.getParameter("txtDescribe")%>"/>
            <%= error.getDescribeError()%></br>
            Upload Image:<mark>(*)</mark> <input type="file" name="fileImage" accept="image/png, image/jpeg"/>
            <%= error.getImageError()%></br>           
            <input type="submit" name="btnAction" value="New Room"/>
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
