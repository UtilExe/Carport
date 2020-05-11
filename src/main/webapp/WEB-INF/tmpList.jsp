<%@ page import="FunctionLayer.Initialisation" %>
<%@include file="../include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Danie
  Date: 27-04-2020
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
    <title>Tmp liste indtil nyt møde</title>
</head>
<body>


<div style="text-align: center">
    <h2 class="pt-4">Samlet pris: ${requestScope.finalPrice} kr.</h2>
</div>

<div class="pt-4 pb-4">
        <table class="materialListStyle">
            <tr>
                <th>Kategori</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            <c:forEach var="material" items="${requestScope.materialList.list}">
                <tr>
                    <td>${material.get(0)}</td>
                    <td>${material.get(4)}</td>
                    <td>${material.get(3)}</td>
                    <td>${material.get(1)}</td>
                    <td>${material.get(2)}</td>
                </tr>
            </c:forEach>
        </table>
</div>
<div class="ml-4 mb-4">${requestScope.svgdrawing}</div>


</body>
</html>
