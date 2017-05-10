<%@ page import="nl.planner.persistence.entity.Employee" %>
<%@ page import="nl.planner.persistence.entity.Location" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 13-4-2017
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/layout/header.jsp" %>
<% Location location = (Location) request.getAttribute("location");
    List<List<List<List<Employee>>>> planning = null;
    if(location != null) {
        planning = location.getPlanning();
    }
%>

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4 medium" >
        Week
        <button type="submit" class="btn btn-link btn-lg">
            <span class="fa fa-arrow-left fa-lg" aria-hidden="true"></span>
        </button>
        23
        <button type="submit" class="btn btn-link btn-lg">
            <span class="fa fa-arrow-right fa-lg" aria-hidden="true"></span>
        </button>
    </div>
</div>
<%
    if(planning.size() < 1){
%>
<div class = "row">
    <form class="form-horizontal" method="POST">
        <input  class="btn btn-success" type="submit" value="Submit" />
    </form>
</div>
<%
    }
%>

<% int weekday = 0;
    for (List<List<List<Employee>>> day : planning) {
        weekday++;
%>
<%-- shows planning--%>
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
