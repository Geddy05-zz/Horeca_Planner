<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Horeca Planner</title>

    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
®
    <!-- Custom CSS -->
    <link href="/css/landing-page.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="/css/price.css">
</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
    <div class="container topnav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand topnav" href="\">horeca toolkit</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#top">Top</a>
                </li>
                <li>
                    <a href="#how">Het Product</a>
                </li>
                <li>
                    <a href="#price">Prijs</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>


<div id="main">
    <!-- Header -->
    <a name="top"></a>
    <div class="intro-header">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>horeca toolkit</h1>
                        <h3>Let us do your planning</h3>
                        <hr class="intro-divider">
                        <ul class="list-inline intro-social-buttons">
                            <%--<li>--%>
                                <%--${message}--%>
                            <%--</li>--%>
                        <li>
                            <button id ="login" class="btn btn-danger btn-lg">Inloggen</button>
                        </li><li>
                            <button id ="aanmelden" class="btn btn-danger btn-lg">Aanmelden</button>
                        </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Page Content -->
    <a  name="how"></a>
    <div class="content-section-a">

        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Het Product:</h2>
                    <p class = "lead">
                        Een  systeem welke aan de hand van machine learning sales prognoses maakt.
                        Met de sales prognoses wordt er een personeelsplanningen gemaakt welke verrijkt wordt  met
                        actuele weersverwachtingen, met vakantiedagen van potentiele gasten van het restaurant en met de
                        kwaliteiten van personeel. Dit wordt gecontroleerd aan de hand van salescijfers en restaurant
                        reviews. De planner checkt real-time bij wijzigingen of er meer personeel nodig is en checkt bij
                        het personeel wat de beschikbaarheid is. Dit aan de hand van de slimste technieken en kanalen
                        die het personeel op dit moment al gebruikt. Totale ontzorging waardoor u zich als restaurant
                        ondernemer bezig kan houden waar u goed in bent, uw gasten blij maken.
                    </p><p class = "lead">
                        Tevens kan aan de hand van salescijfers, bestellingen bepaald worden wat er door mensen besteld
                        kan worden waarop er slimmer ingekocht kan worden door het restaurant. </p>
                </div>
                <div class="col-lg-4 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" style="padding-top: 40%" src="img/ipad.png" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>

    <div class="content-section-b">

        <div class="container">

            <div class="row">
                <div class="col-lg-6 col-sm-push-6  col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Voordelen van het systeem voor de horeca ondernemer</h2>
                    <p class="lead">
                        Uitendelijk worden uw gasten door deze oplossing gelukkiger waardoor betere reviews over
                        jouw restaurant binnen komen. Het systeem zorgt voor een goede personeelsplanner u hoeft
                        alleen maar te controleren en valideren.
                    </p>
                    <p class="lead">
                        Personeelsplanner 2.0 zorgt ervoor dat u veel minder tijd kwijt bent met het rond krijgen
                        van uw planning. Ook zorgt het ervoor dat het systeem altijd op zoek gaat naar de best passende
                        planning qua kwaliteiten van het personeel waardoor de kans op de beste reviews groter wordt.
                    </p>
                </div>
                <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                    <img class="img-responsive" src="img/dog.png" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>

    <a  name="price"></a>
    <div class="content-section-a">

        <div class="container">
            <div class="row">

                <div class="col-xs-12 col-sm-6 col-md-6">
                    <ul class="pricing p-red">
                        <li>
                            <img src="http://bread.pp.ua/n/settings_r.svg" alt="">
                            <big>Een locatie</big>
                        </li>
                        <li>Maak roosters voor één locatie</li>
                        <li>Omzetprognose</li>
                        <li>Maak planningen vanaf elk type scherm</li>
                        <li>
                            <h3>€35</h3>
                            <span>per maand</span>
                        </li>
                        <li>
                            <button id ="aanmelden">Aanmelden</button>
                        </li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-6">
                    <ul class="pricing p-blue">
                        <li>
                            <img src="http://bread.pp.ua/n/settings_b.svg" alt="">
                            <big>Meerdere locaties</big>
                        </li>
                        <li>Maak roosters voor meerdere locaties</li>
                        <li>Omzetprognose</li>
                        <li>Maak planningen vanaf elk type scherm</li>
                        <li>
                            <h3>€75</h3>
                            <span>per maand</span>
                        </li>
                        <li>
                            <button id ="aanmelden">Aanmelden</button>
                        </li>
                    </ul>
                </div>

            </div>

        </div>
        <!-- /.container -->

    </div>

    <div class="banner">

        <div class="container">

            <div class="row">
                <div class="col-lg-6">
                    <h2>Meld u aan. <br/> En ontdek de vele mogelijkheden</h2>
                </div>
                <div class="col-lg-6">
                    <ul class="list-inline banner-social-buttons">
                        <li>
                            <button id ="login" class="btn btn-danger btn-lg">Inloggen</button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- /.container -->

    </div>
    <!-- /.banner -->
</div>

<div id="log-in">
    <div class="content-section-a">
        <div class="container">
            <div class="row">
                <div id="firebaseui-auth-container"></div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="list-inline">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="#top">Top</a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="#how">Het product</a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="#price">Prijs</a>
                    </li>
                </ul>
                <p class="copyright text-muted small">Copyright &copy; Horeca Planning 2017. All Rights Reserved</p>
            </div>
        </div>
    </div>
</footer>

<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<script src="https://www.gstatic.com/firebasejs/3.2.1/firebase.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.1.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.1.0/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/ui/live/0.5/firebase-ui-auth.js"></script>
<link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/live/0.5/firebase-ui-auth.css">

<script src="/js/main.js"></script>

</body>

</html>

