<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Firebase -->
    <script src="https://www.gstatic.com/firebasejs/3.2.1/firebase.js"></script>
    <script src="https://www.gstatic.com/firebasejs/3.1.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/3.1.0/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/ui/live/0.5/firebase-ui-auth.js"></script>
    <link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/live/0.5/firebase-ui-auth.css">

<%--<script src="/js/jquery.js"></script>--%>

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js"></script>


    <!-- Custom CSS -->
    <link href="/css/sb-admin.css" rel="stylesheet">
    <script>
        function logOut(){
            firebase.auth().signOut().then(function() {
                window.location = "/";
            }, function(error) {
                console.log(error);
            });
        }
    </script>

</head>
<body>
<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/dashboard">Horeca Planner</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li >
                <a onclick="logOut();" ><i class="fa fa-fw fa-sign-out"></i> log out</a>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li>
                    <a href="/dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>
                <li>
                    <a href="/profile"><i class="fa fa-fw fa-user"></i> Profile</a>
                </li>
                <li>
                    <a href="/locations"><i class="fa fa-fw fa-building"></i> Locations</a>
                </li>
                <%--<li>--%>
                    <%--<a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>--%>
                    <%--<ul id="demo" class="collapse">--%>
                        <%--<li>--%>
                            <%--<a href="#">Dropdown Item</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a href="#">Dropdown Item</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="index-rtl.html"><i class="fa fa-fw fa-dashboard"></i> RTL Dashboard</a>--%>
                <%--</li>--%>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">

        <div class="container-fluid">