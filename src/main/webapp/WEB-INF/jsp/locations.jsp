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
    <input id = " Main" type="hidden" name="userID">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title"><i class="fa fa-building fa-fw"></i> Locations</h1>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>address</th>
                            <th>city</th>
                        </tr>
                        </thead>
                        <tbody  id = "locationsList">

                        </tbody>
                    </table>
                </div>
                <br />
                <div id="alert">

                </div>
                <script>
                </script>
                <form id="addLocation" class="form-horizontal" method="POST">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>Create a location</legend>
                        <input id = "userID" type="hidden" name="userID">

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="Name">Name</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input id="Name" name="Name" type="text" placeholder="Name" class="form-control input-md" required>
                                </div>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="Adress">Address</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input id="Adress" name="Adress" type="text" placeholder="Adress" class="form-control input-md" required>
                                </div>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="Adress">City</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input id="City" name="City" type="text" placeholder="City" class="form-control input-md" required>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" ></label>
                            <div class="col-md-4">
                                <input  class="btn btn-success" type="submit" value="submit" />
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