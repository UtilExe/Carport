<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<title>Carport med Fladt tag</title>
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
                    <li class="nav-item">
                        <a class="nav-link text-white" href="FrontController?target=redirect&destination=index"><h3>
                            Forside</h3></a>
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

        <div class="row">
            <div class="col-9">
                <h2>Caport med fladt tag</h2>
                <p>En carport med fladt tag giver mange muligheder –
                    selv på ejendomme med knap så meget plads mellem hus og skel.
                    Ud over at fremstå enkel og minimalistisk, giver en carport med fladt tag,
                    mulighed for at bygge helt i skel, og stadig overholde grænsen for maksimal højde.
                </p>
            </div>
            <div class="col-3">
                <img src="images/fladttag.png" class="img-fluid mx-auto d-block" alt="Carport med fladt tag"/>
            </div>
        </div>

        <h3>Byg din egen Carport</h3>
        <p>Nedenunder kan du tilpasse mål og materialer efter egne ønsker</p>

        <div class="row">
            <div class="col-3">
                <select class="form-control-smaller">
                    <option value="">Vælg Længde</option>
                    <c:forEach var="tmpLength" items="${requestScope.carport_lengths}">
                        <option name="length">${tmpLength}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-3">
                <select class="form-control-smaller">
                    <option value="">Vælg Bredde</option>
                    <c:forEach var="tmpWidth" items="${requestScope.carport_width}">
                        <option name="width">${tmpWidth}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-3">
                <select class="form-control-smaller">
                    <option value="">Vælg Tag</option>
                    <c:forEach var="tmpRoof" items="${requestScope.carport_roofs}">
                        <option name="roof">${tmpRoof}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-3"></div>
        </div>




    </div>
</div>

<%@include file="../include/footer.inc" %>
