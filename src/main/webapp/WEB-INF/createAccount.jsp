<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Opret bruger</title>
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
        <h1 class="display-4" style="text-align: center">Her kan du oprette en profil</h1>
        <div class="container register-form">
            <div class="form">
                <form name="register" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="register">
                    <div class="form-content">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group"> E-mail adresse
                                    <input type="email" class="form-control" name="email" placeholder="Indtast email *" value=""/>
                                </div>
                                <div class="form-group"> Dit kodeord
                                    <input type="password" class="form-control" name="password" placeholder="Indtast kodeord *" value=""/>
                                </div>

                            </div>
                            <div class="col-md-6">
                                <div class="form-group"> Dit fulde navn
                                    <input type="text" class="form-control" name="name" placeholder="Indtast dit for & efternavn *"
                                           value=""/>
                                </div>
                                <div class="form-group"> Dit telefon-nummer
                                    <input type="number" class="form-control" name="mobilNr" placeholder="Indtast dit telefon-nummer *"
                                           value=""/>
                                </div>
                            </div>
                        </div>
                        <p class="beskedRød">${requestScope.createAccountBesked1}</p>
                        <p class="beskedGrøn">${requestScope.createAccountBesked2}</p>
                        <button type="submit" class="btnSubmit" style="text-align: center">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="../include/footer.inc" %>
