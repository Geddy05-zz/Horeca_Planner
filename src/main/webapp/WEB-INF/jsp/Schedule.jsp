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
    List<List<List<List<Employee>>>> planning = new ArrayList();
    if(location != null) {
        planning = location.getPlanning();
    }
%>
<div class="row" style="visibility: hidden">
    <div class="col-md-3"></div>
    <div class="col-md-6 medium" >
        Week
        <button onclick="lastWeek()" class="btn btn-link btn-lg">
            <span class="fa fa-arrow-left fa-lg" aria-hidden="true"></span>
        </button>
        <span id="weeknr">23</span>
        <button onclick="nextWeek()" class="btn btn-link btn-lg">
            <span class="fa fa-arrow-right fa-lg" aria-hidden="true"></span>
        </button>
    </div>
</div>
<%
    if(planning.size() < 1){
%>
<div class = "row">
    <form class="form-horizontal" method="POST">
        <input name="userID" id="userID" type="hidden" >
        <input id = "locationID" type="hidden" name="locationID" value=${locationId}>

        <input  class="btn btn-success" type="submit" value="Create a schedule" />
    </form>
</div>

<div class="row">
    <p>The schedule is based on the following constraints:<br>
        Employee works not longer than 8 hours. <br>
        Employee works on the shift that match with their skills<br>
        The experience of the group. What means that there is no shift with only very experienced employees
        or with only new and/or employees with less experienced</p>
</div>
<div id="newSchedule" class="hidden">
    <table class="col-md-12 schedule">
        <thead>
            <tr>
                <td></td>
                <td>Monday</td>
                <td>Tuesday</td>
                <td>Wednesday</td>
                <td>Thursday</td>
                <td>Friday</td>
                <td>Saturday</td>
                <td>Sunday</td>
            </tr>
        </thead>
        <tr>
            <td class = "job-type">Waiter</td>
            <td class="inner-table">
                <table id="waiterMonday" class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="waiterTuesday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="waiterWednesday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="waiterThursday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="waiterFriday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="waiterSaturday"  class="no-border">

                </table>
            </td>
            <td class="inner-table">
                <table id="waiterSunday"  class="no-border">
                </table>
            </td>
        </tr>
        <tr>
            <td class = "job-type">Bar</td>
            <td class="inner-table">
                <table id="barMonday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="barTuesday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="barWednesday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="barThursday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="barFriday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="barSaturday" class="no-border">

                </table>
            </td>
            <td class="inner-table">
                <table id="barSunday" class="no-border">
                </table>
            </td>
        </tr>
        <tr>
            <td class = "job-type">Kitchen</td>
            <td class="inner-table">
                <table id="kitchenMonday"  class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="kitchenTuesday" class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="kitchenWednesday" class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="kitchenThursday" class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="kitchenFriday" class="no-border">
                </table>
            </td>
            <td class="inner-table">
                <table id="kitchenSaturday" class="no-border">

                </table>
            </td>
            <td class="inner-table">
                <table id="kitchenSunday" class="no-border">
                </table>
            </td>
        </tr>
    </table>
</div>

<div id="schedule"></div>
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
<script src="/js/schedule.js"></script>
<%@ include file="/layout/footer.jsp" %>
