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
<title>Materialeliste og skitser</title>
</head>
<body>

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
                        <a class="nav-link text-white" href="FrontController?target=redirect&destination=index"><h3>
                            Tilbage til forsiden</h3></a>
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


<div style="text-align: center">
    <h3 class="pt-4 text-success">Deres carport-mål er blevet gemt og vil snarest muligt blive efterset af en ansat, der vil vende tilbage til Dem!</h3>
    <h3 class="text-success">De kan nu forlade siden.</h3>
</div>

<div style="text-align: center">
    <h2 class="pt-4">Samlet pris: ${requestScope.finalPrice} kr.</h2>
</div>

<div class="pt-4 pb-4" style="margin-bottom: 50px">
    <p class="centerHorizontal">NB: De vil modtage en stykliste, når carporten er betalt.</p>
</div>

<div class="mb-4 centerHorizontal">${requestScope.svgdrawingfront}</div>

<div class="mb-4 centerHorizontal">${requestScope.svgdrawing}</div>

<%@include file="../include/footer.inc" %>