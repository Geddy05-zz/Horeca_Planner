<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 9-3-2017
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/header.jsp"/>
<div>
    <h1>${location.name}</h1>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Sales graph</h3>
            </div>
            <div class="panel-body">
                <div id="salesChart"></div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Tasks</h3>
            </div>

            <form class="form-horizontal" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="file">Select file</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-user">
                                </i>
                            </div>
                            <input type="file" name="file" id="file" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" ></label>
                    <div class="col-md-4">
                        <input  class="btn btn-success" type="submit" value="Submit" />
                    </div>
                </div>
            </form>

            <div class="panel-body">
                <div class="row">
                    <div class="col-md-2">
                    </div>
                    <div class="list-group">
                        <label class="col-md-4 control-label" >date:</label>
                        <div class="col-md-4">
                            <input type="date" id="datefield">
                        </div>
                    </div>
                </div>
                <div class="text-right">
                    <a href="#">View All Activity <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Sales graph</h3>
            </div>
            <div class="panel-body">
                <div id="morris-donut-chart"></div>
                <div class="text-right">
                    <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<script>
    <%
    List<String[]>  forecast = (List<String[]> ) request.getAttribute("forecast");
    %>

    new Morris.Line({
        // ID of the element in which to draw the chart.
        element: 'salesChart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data: [
            <%
            int i = 0;
            for(String[] map: forecast){

                if(map[1].equals("-")){
            %>
                { year:  "<%= map[0] %>" ,  forecast: <%= map[2]%>},
            <%
                }else{
            %>
                { year:  "<%= map[0] %>" , value:  <%= map[1] %>, forecast: <%= map[2]%>},

            <%  }
            }; %>
        ],
        // The name of the data record attribute that contains x-values.
        xkey: 'year',
        xLabels:"month",

        pointSize:0,
        // A list of names of data record attributes that contain y-values.
        ykeys: ['value','forecast'],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        resize: true,
        labels: ['Sales','Forecast']
    });
</script>
<jsp:include page="/layout/footer.jsp"/>