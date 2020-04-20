<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.inc" %>
<link rel="stylesheet" href="css/styles.css">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Carport med Fladt tag</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="#"> <img src="../images/logo.png" width="100" height="100" alt="Logo"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto ">
            <ul class="navbar-nav mr-5 mt-60 mt-lg-0">
                <div class="col-lg-auto">
                    <li class="nav-item">
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

<%@include file="../include/footer.inc"%>
