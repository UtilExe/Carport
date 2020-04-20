<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<title>Welcome page</title>

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
                        <a class="nav-link text-white" href="#"><h3>Forside</h3> <span class="sr-only">(current)</span></a>
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

<img src="images/header.jpeg" class="img-fluid mx-auto d-block" alt="Responsive header image"/>

<div class="container jumbotron ">

    <div class="jumbotron mr-5 mt-20 mt-lg-0">
        <h3> Byg din egen carport</h3>
        <p> Her kan du designe og udvikle din egen ønskede Carport med egne mål.
            På den måde sikrer du, at du får netop din ønskede Carport!</p>
        <p>Vælg din ønskede Carport type for at starte med at designe den:</p>

        <div class="row">
        <div class="col">
        <a href="FrontController?target=redirect&destination=fladttag">
            <p class="carport-text">Carport med fladt tag</p>
            <img src="images/fladttag.png" class="img-fluid mx-auto d-block carport-billeder"
                 alt="Carport med fladt tag"/>
        </a>
        </div>
        <div class="col">
        <a href="FrontController?target=redirect&destination=medrejsning">
            <p class="carport-text">Carport med rejsning</p>
            <img src="images/rejsning.png" class="img-fluid mx-auto d-block carport-billeder"
                 alt="Carport med rejsning"/>
        </a>
        </div>
        </div>

    </div>

</div>

<%@include file="include/footer.inc" %>