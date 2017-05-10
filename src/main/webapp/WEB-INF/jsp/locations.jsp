<%@ page import="nl.planner.persistence.entity.Location" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 23-2-2017
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/layout/header.jsp"/>
<div class="row">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title"><i class="fa fa-money fa-fw"></i> Locations</h1>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>address</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<%--%>
                            <%--List<Location> locations=(List<Location>) request.getAttribute("locations");--%>
                            <%--for(Location location: locations){--%>
                                <%--%>--%>
                                 <%--<tr>--%>
                                     <%--<td><a href = <%=  "/location/"+location.getId() %> ><%= location.getName()%></a></td>--%>
                                    <%--<td><%= location.getAddress() %></td>--%>
                                 <%--</tr>--%>
                           <%--<%--%>
                            <%--}--%>
                        <%--%>--%>
                        </tbody>
                    </table>
                </div>
                <br />

                <form class="form-horizontal" method="POST">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>Create a location</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="Name">Name</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-user">
                                        </i>
                                    </div>
                                    <input id="Name" name="Name" type="text" placeholder="Name" class="form-control input-md" required>
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="Adress">Adress</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-user">
                                        </i>
                                    </div>
                                    <input id="Adress" name="Adress" type="text" placeholder="Adress" class="form-control input-md" required>
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="Adress">Adress</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-user">
                                        </i>
                                    </div>
                                    <input id="City" name="City" type="text" placeholder="City" class="form-control input-md" required>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" ></label>
                            <div class="col-md-4">
                                <input  class="btn btn-success" type="submit" value="Submit" />
                                <%--<a href="#" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> Submit</a>--%>
                                <a href="#" class="btn btn-danger" value=""><span class="glyphicon glyphicon-remove-sign"></span> Clear</a>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/js/locations.js"></script>


<jsp:include page="/layout/footer.jsp"/>