<%@ page import="nl.planner.persistence.entity.Employee" %>
<%@ page import="nl.planner.persistence.entity.Location" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 13-4-2017
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/layout/header.jsp" %>
<% Location location = (Location) request.getAttribute("location");
    List<List<List<List<Employee>>>> planning = location.getPlanning();
%>

<%--<div class="row col-lg-12">--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Monday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-lg-6">--%>
<%--<p>Day</p>--%>
<%--<div class="panel panel-green" style="border-width: 3px;">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/user_photos/000/332/699/datas/profile.jpg" class="img-responsive">--%>
<%--</div>--%>
<%--<div class="col-md-8">--%>
<%--<h6>Serveerster 1</h6>--%>
<%--<p style="font-size: 12px;"> 06-12345678 </p>--%>
<%--<button class="btn btn-primary btn-xs">--%>
<%--<i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--Waiter</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="panel panel-green" style="background-color:#bef1bf ">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/user_photos/000/332/699/datas/profile.jpg" class="img-responsive">--%>
<%--</div>--%>
<%--<div class="col-md-8">--%>
<%--<h6>Serveerster 1</h6>--%>
<%--<p style="font-size: 12px;"> 06-12345678 </p>--%>
<%--<button class="btn btn-primary btn-xs">--%>
<%--<i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--Waiter</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="panel panel-blue" style="background-color:#a9dcf1 ">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/user_photos/000/332/699/datas/profile.jpg" class="img-responsive">--%>
<%--</div>--%>
<%--<div class="col-md-8">--%>
<%--<h6>Serveerster 1</h6>--%>
<%--<p style="font-size: 12px;"> 06-12345678 </p>--%>
<%--<button class="btn btn-primary btn-xs">--%>
<%--<i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--Waiter</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="panel panel-blue">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/user_photos/000/332/699/datas/profile.jpg" class="img-responsive">--%>
<%--</div>--%>
<%--<div class="col-md-8">--%>
<%--<h6>Serveerster 1</h6>--%>
<%--<p style="font-size: 12px;"> 06-12345678 </p>--%>
<%--<button class="btn btn-primary btn-xs">--%>
<%--<i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--Waiter</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>
<%--<div class="col-lg-2"></div>--%>

<%--<div class="col-lg-6">--%>
<%--<p>Evening</p>--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/user_photos/000/332/699/datas/profile.jpg" class="img-responsive">--%>
<%--</div>--%>
<%--<div class="col-md-8">--%>
<%--<h6>Serveerster 1</h6>--%>
<%--<p style="font-size: 12px;"> 06-12345678 </p>--%>
<%--<button class="btn btn-primary btn-xs">--%>
<%--<i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--Waiter</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/user_photos/000/332/699/datas/profile.jpg" class="img-responsive">--%>
<%--</div>--%>
<%--<div class="col-md-8">--%>
<%--<h6>Serveerster 1</h6>--%>
<%--<p style="font-size: 12px;"> 06-12345678 </p>--%>
<%--<button class="btn btn-primary btn-xs">--%>
<%--<i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--Waiter</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Tuesday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<form action="addEmployee" method="post">--%>
<%--<input type="hidden" name="locationId" value="${location.id}">--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="name">--%>
<%--Name--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="name" name="name" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="name1">--%>
<%--Surname--%>
<%--</label>--%>
<%--<input class="form-control" id="name1" name="name1" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="date">--%>
<%--Date--%>
<%--</label>--%>
<%--<input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select skills--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "1"/>--%>
<%--Waiter--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "2" />--%>
<%--Barkeeper--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "3" />--%>
<%--Kitchen--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select available days--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "0" />--%>
<%--Monday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "1" />--%>
<%--Tuesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "2" />--%>
<%--Wednesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "3" />--%>
<%--Thursday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "4" />--%>
<%--Friday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "5" />--%>
<%--Saturday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "6" />--%>
<%--Sunday--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="price">--%>
<%--Price per hour--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="price" name="price" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="contractHours">--%>
<%--Hours in contract--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="contractHours" name="contractHours" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div>--%>
<%--<button class="btn btn-primary " name="submit" type="submit">--%>
<%--Submit--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row col-lg-12">--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Wednesday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<form action="addEmployee" method="post">--%>
<%--<input type="hidden" name="locationId" value="${location.id}">--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="name">--%>
<%--Name--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="name" name="name" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="name1">--%>
<%--Surname--%>
<%--</label>--%>
<%--<input class="form-control" id="name1" name="name1" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="date">--%>
<%--Date--%>
<%--</label>--%>
<%--<input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select skills--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "1"/>--%>
<%--Waiter--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "2" />--%>
<%--Barkeeper--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "3" />--%>
<%--Kitchen--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select available days--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "0" />--%>
<%--Monday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "1" />--%>
<%--Tuesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "2" />--%>
<%--Wednesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "3" />--%>
<%--Thursday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "4" />--%>
<%--Friday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "5" />--%>
<%--Saturday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "6" />--%>
<%--Sunday--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="price">--%>
<%--Price per hour--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="price" name="price" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="contractHours">--%>
<%--Hours in contract--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="contractHours" name="contractHours" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div>--%>
<%--<button class="btn btn-primary " name="submit" type="submit">--%>
<%--Submit--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Thursday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<form action="addEmployee" method="post">--%>
<%--<input type="hidden" name="locationId" value="">--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="name">--%>
<%--Name--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="name" name="name" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="name1">--%>
<%--Surname--%>
<%--</label>--%>
<%--<input class="form-control" id="name1" name="name1" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="date">--%>
<%--Date--%>
<%--</label>--%>
<%--<input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select skills--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "1"/>--%>
<%--Waiter--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "2" />--%>
<%--Barkeeper--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "3" />--%>
<%--Kitchen--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select available days--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "0" />--%>
<%--Monday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "1" />--%>
<%--Tuesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "2" />--%>
<%--Wednesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "3" />--%>
<%--Thursday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "4" />--%>
<%--Friday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "5" />--%>
<%--Saturday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "6" />--%>
<%--Sunday--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="price">--%>
<%--Price per hour--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="price" name="price" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="contractHours">--%>
<%--Hours in contract--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="contractHours" name="contractHours" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div>--%>
<%--<button class="btn btn-primary " name="submit" type="submit">--%>
<%--Submit--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row col-lg-12">--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Friday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<form action="addEmployee" method="post">--%>
<%--<input type="hidden" name="locationId" value="${location.id}">--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="name">--%>
<%--Name--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="name" name="name" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="name1">--%>
<%--Surname--%>
<%--</label>--%>
<%--<input class="form-control" id="name1" name="name1" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="date">--%>
<%--Date--%>
<%--</label>--%>
<%--<input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select skills--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "1"/>--%>
<%--Waiter--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "2" />--%>
<%--Barkeeper--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "3" />--%>
<%--Kitchen--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select available days--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "0" />--%>
<%--Monday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "1" />--%>
<%--Tuesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "2" />--%>
<%--Wednesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "3" />--%>
<%--Thursday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "4" />--%>
<%--Friday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "5" />--%>
<%--Saturday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "6" />--%>
<%--Sunday--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="price">--%>
<%--Price per hour--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="price" name="price" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="contractHours">--%>
<%--Hours in contract--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="contractHours" name="contractHours" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div>--%>
<%--<button class="btn btn-primary " name="submit" type="submit">--%>
<%--Submit--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Saterday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<form action="addEmployee" method="post">--%>
<%--<input type="hidden" name="locationId" value="${location.id}">--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="name">--%>
<%--Name--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="name" name="name" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="name1">--%>
<%--Surname--%>
<%--</label>--%>
<%--<input class="form-control" id="name1" name="name1" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="date">--%>
<%--Date--%>
<%--</label>--%>
<%--<input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select skills--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "1"/>--%>
<%--Waiter--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "2" />--%>
<%--Barkeeper--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "3" />--%>
<%--Kitchen--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select available days--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "0" />--%>
<%--Monday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "1" />--%>
<%--Tuesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "2" />--%>
<%--Wednesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "3" />--%>
<%--Thursday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "4" />--%>
<%--Friday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "5" />--%>
<%--Saturday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "6" />--%>
<%--Sunday--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="price">--%>
<%--Price per hour--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="price" name="price" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="contractHours">--%>
<%--Hours in contract--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="contractHours" name="contractHours" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div>--%>
<%--<button class="btn btn-primary " name="submit" type="submit">--%>
<%--Submit--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row col-lg-12">--%>
<%--<div class="col-lg-6">--%>
<%--<div class="panel panel-green">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Sunday</a>--%>
<%--<span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion" href="#filterPanel">--%>
<%--<i class="glyphicon glyphicon-chevron-down"></i>--%>
<%--</span>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="filterPanel" class="panel-collapse panel-collapse collapse">--%>
<%--<div class="panel-body">--%>
<%--<form action="addEmployee" method="post">--%>
<%--<input type="hidden" name="locationId" value="${location.id}">--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="name">--%>
<%--Name--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="name" name="name" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="name1">--%>
<%--Surname--%>
<%--</label>--%>
<%--<input class="form-control" id="name1" name="name1" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label " for="date">--%>
<%--Date--%>
<%--</label>--%>
<%--<input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select skills--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "1"/>--%>
<%--Waiter--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "2" />--%>
<%--Barkeeper--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="skills" type="checkbox" value= "3" />--%>
<%--Kitchen--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField">--%>
<%--Select available days--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<div class="">--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "0" />--%>
<%--Monday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "1" />--%>
<%--Tuesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "2" />--%>
<%--Wednesday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "3" />--%>
<%--Thursday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "4" />--%>
<%--Friday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "5" />--%>
<%--Saturday--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="radio">--%>
<%--<label class="radio">--%>
<%--<input name="availableWeekdays" type="checkbox" value= "6" />--%>
<%--Sunday--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="price">--%>
<%--Price per hour--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="price" name="price" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label requiredField" for="contractHours">--%>
<%--Hours in contract--%>
<%--<span class="asteriskField">--%>
<%--*--%>
<%--</span>--%>
<%--</label>--%>
<%--<input class="form-control" id="contractHours" name="contractHours" type="text"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div>--%>
<%--<button class="btn btn-primary " name="submit" type="submit">--%>
<%--Submit--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>

<% int weekday = 0;
    for (List<List<List<Employee>>> day : planning) {
        weekday++;
%>
<div class="row">
    <br/>
    <%

    if(weekday == 1){
        %>
        <h2>Monday</h2>
    <%
    }else if(weekday == 2){
    %>
    <h2>Tuesday</h2>
    <%
    }else if(weekday == 3){
    %>
    <h2>Wednesday</h2>
    <%
    }else if(weekday == 4){
    %>
    <h2>Thursday</h2>
    <%
    }else if(weekday == 5){
    %>
    <h2>Friday</h2>
    <%
    } else if(weekday == 5){
    %>
    <h2>Saturday</h2>
    <%
    } else{
    %>
    <h2>Sunday</h2>
    <%
    }

        int count = 0;
        for (List<List<Employee>> shift : day) {
            count++;
    %>

    <div class="col-md-6">

        <% if (count == 1) {%>
        <h4>Middag:</h4>
        <table>
                <%
                }else{
                    %>
            <br/>
            <h4>Avond:</h4>
            <table>
                <%
                    }
                    int typeCount = 0;
                    for (List<Employee> shiftType : shift) {
                        typeCount++;
                %>
                <tr>
                    <% if (typeCount == 1) {
                    %>
                    <td> Waiter</td>
                    <%
                    } else if (typeCount == 2) {
                    %>
                    <td> Bar</td>
                    <%

                    } else {%>
                    <td> Kitchen</td>

                    <% } %>
                    <td>
                        <%

                            for (Employee employee : shiftType) {

                        %>
                        <button type="button" class="btn btn-primary" style="margin-left: 10px;margin-right: 10px"><%= employee.getName()%>
                        </button>
                        <%
                            }
                        %>
                    </td>
                </tr>

                <%
                    }
                %>
            </table>
    </div>
    <%

        }
    %>
</div>
<%
    }%>

<%--</div>--%>

<%@ include file="/layout/footer.jsp" %>
