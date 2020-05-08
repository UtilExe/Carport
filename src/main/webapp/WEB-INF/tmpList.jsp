<%@ page import="FunctionLayer.Initialisation" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Danie
  Date: 27-04-2020
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tmp liste indtil nyt møde</title>
</head>
<body>

<%!
    @Override
    public void jspInit(){
        Initialisation.initMaterialList();
    }
%>

<%
    request.setAttribute("materialList", Initialisation.getMaterialList());
%>

<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <table style="width:100%">
            <tr>
                <th>Kategori</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            <c:forEach items="${requestScope.materialList}">
                <tr>
                <c:forEach var="material" items="${requestScope.materials.list}">
                    <tr>${material.get(0)}</tr>
                    <tr>${material.get(1)}</tr>
                    <tr>${material.get(2)}</tr>
                    <tr>${material.get(3)}</tr>
                    <tr>${material.get(4)}</tr>
                    <tr>${material.get(5)}</tr>
                </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-3"></div>
</div>

</body>
</html>
