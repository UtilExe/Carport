        <ul class="list-unstyled">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <img class="login-image" src="./images/foto.png" height="70" alt="Logo">
                </a>
                <div class="dropdown-menu" style="left: -110px;" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="FrontController?target=redirect&destination=createAccount">Opret profil</a>
                    <a class="dropdown-item"> Eller login:</a>
                    <ul id="login-dp" class="dropdown-item list-unstyled">
                        <li>
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form" role="form" method="post" action="FrontController" method="POST" accept-charset="UTF-8" id="login-nav">
                                    <input type="hidden" name="target" value="login">
                                        <div class="form-group">
                                            <label class="sr-only" for="exampleInputEmail2">E-mail adresse</label>
                                            <input type="email" name="email" class="form-control" id="exampleInputEmail2"
                                                   placeholder="E-mail adresse" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="exampleInputPassword2">Adgangskode</label>
                                            <input type="password" name="password" class="form-control" id="exampleInputPassword2"
                                                   placeholder="Adgangskode" required>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
        </ul>