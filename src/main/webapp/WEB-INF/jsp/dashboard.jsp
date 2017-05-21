<%@ page import="java.util.List" %>
<%@ page import="nl.planner.persistence.entity.Location" %>
<%@ page import="org.apache.commons.lang3.ObjectUtils" %>
<%@ page import="nl.planner.persistence.entity.Weather" %>
<jsp:include page="/layout/header.jsp"/>

<%
    List<Location> locations = null;
    Location location = null;
    if(request.getAttribute("locations") != null) {
        locations = (List<Location>) request.getAttribute("locations");
        if(locations != null) {
//            location = (locations.size() >= 1) ? locations.get(0) : null;
        }
    }

    List<Weather> weather = (List<Weather>) request.getAttribute("weatherForecast");
%>

<!-- Page Heading -->
<div class="row">
    <div class="col-lg-12">
        <div class="page-header">
        <h1 >
            Dashboard <small>for location:
                <%--<select name="locations" id="locations" value="1">--%>
                    <%--<%--%>
                        <%--if(locations != null){--%>
                            <%--for(Location loc: locations){--%>
                    <%--%>--%>
                    <%--<option value=<%= loc.getId()%>><%= loc.getName()%></option>--%>
                    <%--<%--%>
                            <%--};--%>
                        <%--}--%>
                    <%--%>--%>
                <%--</select>--%>
        </small>
        </h1>
        </div>
        <ol class="breadcrumb">
            <li class="active">
                <i class="fa fa-dashboard"></i> Dashboard
            </li>
        </ol>
    </div>
</div>
<!-- /.row -->
<div class="content">
    <div class="row">
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-green" id="Schedule">
                <div class="panel-heading">
                    <a class="whiteLink" id ="schedule" href="#">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-table fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">Schedule</div>
                            <div>Create a schedule</div>
                        </div>
                    </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-yellow">
                <div class="panel-heading">
                    <a class="whiteLink" id ="detail" href="#">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-building-o fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">Detail</div>
                                <div>Show location details</div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
        </div>
    </div>
    <!-- /.row -->

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default" id="Sales-Chart">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Sales Chart</h3>
                </div>
                <div class="panel-body">
                    <div id="salesChart"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.row -->

    <div class="row">
        <div class="col-lg-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Number of employees needed</h3>
                </div>
                <div class="panel-body">
                    <div class="text-center">
                        <i class="fa fa-fw fa-calendar"></i>
                        Select a date
                        <input type="text" id="datepicker">
                    </div>
                    <br />
                    <p>
                    <h3>Number of employees needed based on sales: </h3> <br/>

                        Waiters : <div id="waiters_needed"></div><br/>
                        Barkeerpers: <div id="barkeepers_needed"></div><br/>
                        Kitchen: <div id="kitchen_needed"></div><br/>
                    </p>
                    <h3> week overview employees needed:</h3>
                    <div id="morris-sales-chart"></div>
                    <div class="text-right">
                        <%--<a href=" <%= "/location/" + locations.get(0).getId() + "/createSchedule" %> ">--%>
                            <a href=" <%= "/locations" %> ">
                            Create Schedule for this week
                            <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="panel panel-default" id="Tasks-Panel">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Tasks Panel</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">28-03-2017</span>
                            <i class="fa fa-fw fa-truck"></i> Realisatie invoeren
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">01-04-2017</span>
                            <i class="fa fa-fw fa-comment"></i> Prognose aangepast herzie planning
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">02-04-2017</span>
                            <i class="fa fa-fw fa-comment"></i> Verandereing weersverwachting, herzie planning
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">10-04-2017</span>
                            <i class="fa fa-fw fa-calendar"></i> Personeel plannen
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">11-04-2017</span>
                            <i class="fa fa-fw fa-calendar"></i> Personeel plannen
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">12-04-2017</span>
                            <i class="fa fa-fw fa-calendar"></i> Personeel plannen
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">13-04-2017</span>
                            <i class="fa fa-fw fa-calendar"></i> Personeel plannen
                        </a>
                        <a href="#" class="list-group-item">
                            <span class="badge badge-info">14-04-2017</span>
                            <i class="fa fa-fw fa-calendar"></i> Personeel plannen
                        </a>
                    </div>
                    <div class="text-right">
                        <%--<a href="#">View All Activity <i class="fa fa-arrow-circle-right"></i></a>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="panel panel-default" id="Logbook">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-terminal fa-fw"></i> Logbook</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                            <tr>
                                <th>Date</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>27-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            <tr>
                                <td>27-03-2017</td>
                                <td>Personeel geplanned </td>
                            </tr>
                            <tr>
                                <td>26-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            <tr>
                                <td>25-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            <tr>
                                <td>24-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            <tr>
                                <td>23-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            <tr>
                                <td>22-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            <tr>
                                <td>21-03-2017</td>
                                <td>Realisatie ingevoerd</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="text-right">
                        <a href="#">View Logbook<i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% if(weather != null){ %>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Weather</h3>
                </div>
                <div class="panel-body">
                    <%--<div class="list-group">--%>
                        <p>
                        </p>
                        <table class="table table-striped">
                            <tr>
                                <th style="width: auto; text-align: center"> </th>
                                <th style="width: 13%; text-align: center"><%= weather.get(0).getWeekdayName()%></th>
                                <th style="width: 13%; text-align: center"><%= weather.get(1).getWeekdayName()%></th>
                                <th style="width: 13%; text-align: center"><%= weather.get(2).getWeekdayName()%></th>
                                <th style="width: 13%; text-align: center"><%= weather.get(3).getWeekdayName()%></th>
                                <th style="width: 13%; text-align: center"><%= weather.get(4).getWeekdayName()%></th>
                                <th style="width: 13%; text-align: center"><%= weather.get(5).getWeekdayName()%></th>
                                <th style="width: 13%; text-align: center"><%= weather.get(6).getWeekdayName()%></th>
                            </tr>
                            <tr>
                                <td>max temp</td>
                                <td style="text-align: center"><%= (int) weather.get(0).getMaxTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(1).getMaxTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(2).getMaxTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(3).getMaxTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(4).getMaxTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(5).getMaxTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(6).getMaxTemp()%></td>
                            </tr>
                            <tr>
                                <td>min temp</td>
                                <td style="text-align: center"><%= (int) weather.get(0).getMinTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(1).getMinTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(2).getMinTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(3).getMinTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(4).getMinTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(5).getMinTemp()%></td>
                                <td style="text-align: center"><%= (int) weather.get(6).getMinTemp()%></td>
                            </tr>
                            <tr>
                                <td>rain probability</td>
                                <td style="text-align: center"><%= (int) (weather.get(0).getRainProbability() * 100) %> %</td>
                                <td style="text-align: center"><%= (int) (weather.get(1).getRainProbability() * 100) %> %</td>
                                <td style="text-align: center"><%= (int) (weather.get(2).getRainProbability() * 100) %> %</td>
                                <td style="text-align: center"><%= (int) (weather.get(3).getRainProbability() * 100) %> %</td>
                                <td style="text-align: center"><%= (int) (weather.get(4).getRainProbability() * 100) %> %</td>
                                <td style="text-align: center"><%= (int) (weather.get(5).getRainProbability() * 100) %> %</td>
                                <td style="text-align: center"><%= (int) (weather.get(6).getRainProbability() * 100) %> %</td>
                            </tr>
                            <tr>
                                <td>rainfall</td>
                                <td style="text-align: center"><%= weather.get(0).getRain() > 0.01 ? weather.get(0).getRain() : 0 %></td>
                                <td style="text-align: center"><%= weather.get(1).getRain() > 0.01 ? weather.get(1).getRain() : 0  %></td>
                                <td style="text-align: center"><%= weather.get(2).getRain() > 0.01 ? weather.get(2).getRain() : 0 %></td>
                                <td style="text-align: center"><%= weather.get(3).getRain() > 0.01 ? weather.get(3).getRain() : 0 %></td>
                                <td style="text-align: center"><%= weather.get(4).getRain() > 0.01 ? weather.get(4).getRain() : 0 %></td>
                                <td style="text-align: center"><%= weather.get(5).getRain() > 0.01 ? weather.get(5).getRain() : 0 %></td>
                                <td style="text-align: center"><%= weather.get(6).getRain() > 0.01 ? weather.get(6).getRain() : 0 %></td>
                            </tr>
                            <tr>
                                <td>wind speed</td>
                                <td style="text-align: center"><%= (int) weather.get(0).getWindSpeed() %></td>
                                <td style="text-align: center"><%= (int) weather.get(1).getWindSpeed()%></td>
                                <td style="text-align: center"><%= (int) weather.get(2).getWindSpeed()%></td>
                                <td style="text-align: center"><%= (int) weather.get(3).getWindSpeed()%></td>
                                <td style="text-align: center"><%= (int) weather.get(4).getWindSpeed()%></td>
                                <td style="text-align: center"><%= (int) weather.get(5).getWindSpeed()%></td>
                                <td style="text-align: center"><%= (int) weather.get(6).getWindSpeed()%></td>
                            </tr>
                            <tr>
                                <td>summary</td>
                                <td style="text-align: center"><%= weather.get(0).getSummary() %></td>
                                <td style="text-align: center"><%= weather.get(1).getSummary()%></td>
                                <td style="text-align: center"><%= weather.get(2).getSummary()%></td>
                                <td style="text-align: center"><%= weather.get(3).getSummary()%></td>
                                <td style="text-align: center"><%= weather.get(4).getSummary()%></td>
                                <td style="text-align: center"><%= weather.get(5).getSummary()%></td>
                                <td style="text-align: center"><%= weather.get(6).getSummary()%></td>
                            </tr>
                        </table>
                </div>
            </div>
        </div>
    </div>
    <% }%>
</div>
<link rel="stylesheet" href="/css/bootstrap-tour.min.css">
<script src="/js/bootstrap-tour.min.js"></script>
<script src="/js/dashboard.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<script>
    <%--<%--%>
    <%--List<String[]>  forecast = (List<String[]> ) request.getAttribute("forecast");--%>
    <%--List<String[]>  weekForecast = (List<String[]> ) request.getAttribute("weekForecast");--%>
    <%--%>--%>

    <%--new Morris.Line({--%>
        <%--// ID of the element in which to draw the chart.--%>
        <%--element: 'salesChart',--%>
        <%--// Chart data records -- each entry in this array corresponds to a point on--%>
        <%--// the chart.--%>
        <%--data: [--%>
            <%--<%--%>
            <%--int i = 0;--%>
            <%--if(forecast.size() > 60){--%>
            <%--for(String[] map: forecast.subList(forecast.size()-60,forecast.size())){--%>

                <%--if(map[1].equals("-")){--%>
            <%--%>--%>
            <%--{ year:  "<%= map[0] %>" ,  forecast: <%= map[2]%>},--%>
            <%--<%--%>
                <%--}else{--%>
            <%--%>--%>
            <%--{ year:  "<%= map[0] %>" , value:  <%= map[1] %>, forecast: <%= map[2]%>},--%>

            <%--<%  }--%>
            <%--}; }%>--%>
        <%--],--%>
        <%--// The name of the data record attribute that contains x-values.--%>
        <%--xkey: 'year',--%>
        <%--xLabels:"day",--%>

        <%--pointSize:0,--%>
        <%--// A list of names of data record attributes that contain y-values.--%>
        <%--ykeys: ['value','forecast'],--%>
        <%--// Labels for the ykeys -- will be displayed when you hover over the--%>
        <%--// chart.--%>
        <%--resize: true,--%>
        <%--labels: ['Sales','Forecast']--%>
    <%--});--%>

    <%--new Morris.Line({--%>
        <%--// ID of the element in which to draw the chart.--%>
        <%--element: 'morris-sales-chart',--%>
        <%--// Chart data records -- each entry in this array corresponds to a point on--%>
        <%--// the chart.--%>
        <%--data:[--%>
            <%--<%--%>
            <%--if(weekForecast.size() > 0){--%>
             <%--for(String[] map: weekForecast){--%>
                 <%--%>--%>
            <%--{ date:  "<%=map[0]%>" , waiters:  <%=map[1]%>, barkeepers: <%=map[2]%>, kitchen: <%=map[3]%>},--%>

            <%--<%--%>
             <%--}}--%>
            <%--%>--%>
        <%--],--%>
        <%--// The name of the data record attribute that contains x-values.--%>
        <%--xkey: 'date',--%>
        <%--xLabels:"day",--%>

        <%--pointSize:0,--%>
        <%--// A list of names of data record attributes that contain y-values.--%>
        <%--ykeys: ['waiters','barkeepers','kitchen'],--%>
        <%--// Labels for the ykeys -- will be displayed when you hover over the--%>
        <%--// chart.--%>
        <%--resize: true,--%>
        <%--labels: ['waiters','barkeepers','kitchen']--%>
    <%--});--%>

</script>
<!-- /.row -->

<jsp:include page="/layout/footer.jsp"/>