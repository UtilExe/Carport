<%@ page import="FunctionLayer.Initialisation" %>
<%@ page import="FunctionLayer.UniversalSampleException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<title>Admin</title>
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

        ${orderID}

        <form action="FrontController" method="POST">
            <input type="hidden" name="target" value="edit">

            <input type="hidden" name="orderID" value="${orderID}">

        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="carportLength" name="measure" value="carport_length" required>
            <label class="custom-control-label" for="carportLength">Carport Længde</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="carportWidth" name="measure" value="carport_width" required>
            <label class="custom-control-label" for="carportWidth">Carport Bredde</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="carportHeigth" name="measure" value="carport_height" required>
            <label class="custom-control-label" for="carportHeigth">Carport Højde</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="shedLength" name="measure" value="shedLength" required>
            <label class="custom-control-label" for="shedLength">Skur Længde</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="shedWidth" name="measure" value="shedWidth" required>
            <label class="custom-control-label" for="shedWidth">Skur Bredde</label>
        </div>
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="carportPitch" name="measure" value="roof_pitch" required>
            <label class="custom-control-label" for="carportPitch">Tag hældning</label>
        </div>

            <h5>Indtast ny enhed</h5>
            <input type="number" name="amount" min="50" max="1000">
            <button type="submit" class="btnSubmit" style="text-align: center">Submit</button>

        </form>
    </div>
</div>
<%@include file="../include/footer.inc" %>