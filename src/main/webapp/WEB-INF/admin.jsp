<%@ page import="FunctionLayer.Initialisation" %>
<%@ page import="FunctionLayer.UniversalSampleException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<title>Admin</title>
</head>
<body onload="showMaterialList()">

<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="#"> <img src="./images/logo.png" width="100" height="100" alt="Logo"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto ">
            <ul class="navbar-nav mr-5 mt-60 mt-lg-0">
                <div class="col-lg-auto">
                    <li class="nav-item active">
                        <a class="nav-link text-white" href="FrontController?target=redirect&destination=index"><h3>Forside</h3></a>
                    </li>
                </div>
            </ul>
        </ul>
        <span class="navbar-text mr-5">
            ${sessionScope.email}
        </span>
        <%@include file="/include/dropdownMenu.inc" %>
    </div>
</nav>

<div class="container jumbotron ">

    <div class="jumbotron mr-5 mt-20 mt-lg-0">
        <h1>Velkommen, ${sessionScope.email}</h1>


        <h3>Her kan du se ordre som afventer godkendelse:</h3>

        <div class="ml-4 mb-4">${requestScope.drawingAbove}</div>

        <div id="materialTable" style="visibility: hidden;">
            <table class="materialListStyle centerHorizontal">
                <tr>
                    <th>Kategori</th>
                    <th>Længde</th>
                    <th>Antal</th>
                    <th>Enhed</th>
                    <th>Beskrivelse</th>
                    <th>Pris</th>
                </tr>
                <c:forEach var="materialView" items="${requestScope.materialView.list}">
                    <tr>
                        <td>${materialView.get(0)}</td>
                        <td>${materialView.get(4)}</td>
                        <td>${materialView.get(3)}</td>
                        <td>${materialView.get(1)}</td>
                        <td>${materialView.get(2)}</td>
                        <td>${materialView.get(materialView.size()-1)}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="ml-4 mb-4">${requestScope.drawingSide}</div>

        <ul style="list-style-type:none;">
            <c:forEach var="order" items="${requestScope.orders}">
                <div class="mb-3 pb-2 borderSlightUnder">
                    <form action="FrontController" method="POST">
                        <input type="hidden" name="target" value="approve">
                        <li>
                            <input type="hidden" name="orderID" value="${order.orderID}">${order}
                        </li>
                        <div class="row mb-2">
                            <div class="col-3">
                                <button name="godkend" type="submit" class="btnSubmitAdmin" style="text-align: center; width: 100%; background-color: green;">Godkend</button>
                            </div>
                            <div class="col-3">
                                <button name="afvis" type="submit" class="btnSubmitAdmin" style="text-align: center; width: 100%; background-color: red;">Afvis</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-2"><a href="FrontController?target=showplan&orderID=${order.orderID}">Se plantegning</a></div>
                        <div class="col-2"><a href="FrontController?target=materialView&orderID=${order.orderID}">Se materialeliste</a></div>
                        <div class="col-2"><a href="FrontController?target=redirect&destination=editOrder&orderID=${order.orderID}">Ret i carporten</a></div>
                    </div>
                </div>
            </c:forEach>
        </ul>

    </div>
</div>

<script>
    function showMaterialList() {
        var materialTable = document.getElementById("materialTable");
        var showMaterials = ${requestScope.showMaterials};
        if(showMaterials == true) {
            materialTable.style.visibility = "visible";
            materialTable.classList.add('pt-4');
            materialTable.classList.add('pb-4');
        } else {
            materialTable.style.visibility = "hidden";
            materialTable.classList.remove('pt-4');
            materialTable.classList.remove('pb-4');
        }
    }
</script>


<%@include file="../include/footer.inc" %>