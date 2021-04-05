<%-- 
    Document   : create
    Created on : Oct 5, 2020, 9:44:42 AM
    Author     : vuong
--%>

<%@page import="DTO.UserErrorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <%
            UserErrorDTO error=(UserErrorDTO)request.getAttribute("ERROR USER");
            if(error==null){          
               error= new UserErrorDTO("", "", "", "", "", "", "", "", "", "");
            }
        %>
        <form action="MainController" method="POST">
            User ID:<mark>(*)</mark> <input type="text" name="txtUserID" value="<%=request.getParameter("txtUserID")==null?"":request.getParameter("txtUserID")%>"/>
            <%= error.getUserIDError()%></br>
            User Name<mark>(*)</mark> <input type="text" name="txtUserName" value="<%=request.getParameter("txtUserName")==null?"":request.getParameter("txtUserName")%>"/>
            <%= error.getUserNameError()%></br>
            Password:<mark>(*)</mark> <input type="password" name="txtPassword">
            <%= error.getPasswordError()%></br>
            Confirm:<mark>(*)</mark> <input type="password" name="txtConfirm">
            <%= error.getConfirmError()%></br>
            Phone:<mark>(*)</mark> <input type="text" name="txtPhone" value="<%=request.getParameter("txtPhone")==null?"":request.getParameter("txtPhone")%>">
            <%= error.getPhoneError()%></br>
            Email: <input type="text" name="txtEmail" value="<%=request.getParameter("txtEmail")==null?"":request.getParameter("txtEmail")%>">
            <%= error.getEmailError()%></br>
            Address: <input type="text" name="txtAddress" value="<%=request.getParameter("txtAddress")==null?"":request.getParameter("txtAddress")%>">
            <%= error.getAddressError()%></br>
            Birthday:<mark>(*)</mark> <input type="text" name="txtBirthday" value="<%=request.getParameter("txtBirthday")==null?"DD-MM-YYYY":request.getParameter("txtBirthday")%>">
            <%= error.getBirthdayError()%></br>
            Your ID Card:<mark>(*)</mark> <input type="text" name="txtNumberIDCard" value="<%=request.getParameter("txtNumberIDCard")==null?"":request.getParameter("txtNumberIDCard")%>">
            <%= error.getNumberIDCardError()%></br>
            Number Account Bank: <input type="text" name="txtNumberAccountBank" value="<%=request.getParameter("txtNumberAccountBank")==null?"":request.getParameter("txtNumberAccountBank")%>">
            <%= error.getNumberAccountBankError()%></br>
            <input type="submit" name="btnAction" value="Create"/>
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
