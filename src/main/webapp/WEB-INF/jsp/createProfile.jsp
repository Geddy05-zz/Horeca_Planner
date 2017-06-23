<%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 15-3-2017
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap Core CSS -->
<link href="/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/css/plugins/morris.css" rel="stylesheet">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!-- Custom Fonts -->
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<div id="page-wrapper">
    <div class="container-fluid">
        <div id= "newProfile" class="row">
    <div class="col-md-12 ">
        <form class="form-horizontal" method="POST">
            <fieldset>

                <!-- Form Name -->
                <legend style="text-align: center"><h2>Create your user profile for the horeca planner</h2></legend>

                <!-- Text input-->
                <div class="form-group">
                    <input id="userID" type="hidden" name="userID">
                    <label class="col-md-4 control-label" for="Name (Full name)">Name (Full name)</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-user">
                                </i>
                            </div>
                            <input id="Name (Full name)" name="Name (Full name)" type="text" placeholder="Name (Full name)" value="${person.displayName}" class="form-control input-md">
                        </div>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Date Of Birth">Date Of Birth</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-birthday-cake"></i>
                            </div>
                            <input id="Date Of Birth" name="Date Of Birth" type="text" placeholder="Date Of Birth"
                                   class="form-control input-md" value="${person.dateOfBirth}">
                        </div>
                    </div>
                </div>

                <!-- Multiple Radios (inline) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Gender">Gender</label>
                    <div class="col-md-4">
                        <label class="radio-inline" for="Gender-0">
                            <input type="radio" name="Gender" id="Gender-0" value="1" checked="checked">
                            Male
                        </label>
                        <label class="radio-inline" for="Gender-1">
                            <input type="radio" name="Gender" id="Gender-1" value="2">
                            Female
                        </label>
                        <label class="radio-inline" for="Gender-2">
                            <input type="radio" name="Gender" id="Gender-2" value="3">
                            Other
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label col-xs-12" for="Permanent Address">Permanent Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input id="Permanent Address" name="Permanent Address" type="text" placeholder="District" class="form-control input-md ">
                    </div>
                    <div class="col-md-2 col-xs-4">
                        <input id="Permanent Address" name="Permanent Address" type="text" placeholder="Area" class="form-control input-md ">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Permanent Address"></label>
                    <div class="col-md-2  col-xs-4">
                        <input id="Permanent Address" name="Permanent Address" type="text" placeholder="Street" class="form-control input-md ">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Phone number ">Phone number </label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <input id="Phone number " name="Phone number " type="text" placeholder="Primary Phone number "
                                   class="form-control input-md" value="${person.primaryPhone}">
                        </div>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Email Address">Email Address</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope-o"></i>
                            </div>
                            <input id="Email Address" name="Email Address" type="text" placeholder="Email Address"
                                   class="form-control input-md" value="${person.mainEmail}">
                        </div>
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Overview (max 200 words)">Overview (max 200 words)</label>
                    <div class="col-md-4">
                            <textarea class="form-control" rows="10"  id="Overview (max 200 words)"
                                      name="Overview (max 200 words)" value="${person.overview}">Overview</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" ></label>
                    <div class="col-md-4">
                        <input  class="btn btn-success" type="submit" value="Submit" />
                        <%--<a href="#" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> Submit</a>--%>
                        <a onclick="logOut();" class="btn btn-danger" value=""><span class="glyphicon glyphicon-remove-sign"></span> Cancel</a>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</div>
        <div id="loading" class="row" style="height: 100%">
            <i class="fa fa-circle-o-notch fa-spin fa-5x fa-fw" style="position: absolute; top: 50%;left: 50%;"></i>
            <span class="sr-only">Loading....</span>
            <h4 style="position: absolute; top: 60%;left: 51%;">Loading</h4>
        </div>
    </div>
</div>


<script src="https://www.gstatic.com/firebasejs/3.2.1/firebase.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.1.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.1.0/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/ui/live/0.5/firebase-ui-auth.js"></script>
<link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/live/0.5/firebase-ui-auth.css">

<script src="/js/createProfile.js"></script>
