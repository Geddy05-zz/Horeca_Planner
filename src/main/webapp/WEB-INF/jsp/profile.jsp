<%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 23-2-2017
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/layout/header.jsp" %>
<%--<jsp:include page="/layout/header.jsp"/>--%>
    <div class="row">
        <div class="col-md-10 ">
            <form class="form-horizontal" method="POST">
            <fieldset>

                <!-- Form Name -->
                <legend>User profile form requirement</legend>
                <input id="userID" type="hidden" name="userID">

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Name">Name (Full name)</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-user">
                                </i>
                            </div>
                            <input id="Name" name="Name (Full name)" type="text" placeholder="Name (Full name)" value="${person.displayName}" class="form-control input-md">
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="DateOfBirth">Date Of Birth</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-birthday-cake"></i>
                            </div>
                            <input id="DateOfBirth" name="Date Of Birth" type="text" placeholder="Date Of Birth"
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
                    <label class="col-md-4 control-label col-xs-12" for="Permanent_Address">Permanent Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input id="Permanent_Address" name="Permanent Address" type="text" placeholder="District" class="form-control input-md ">
                    </div>
                    <%--<div class="col-md-2 col-xs-4">--%>
                        <%--<input id="Permanent Address" name="Permanent Address" type="text" placeholder="Area" class="form-control input-md ">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="col-md-4 control-label" for="Permanent Address"></label>--%>
                    <%--<div class="col-md-2  col-xs-4">--%>
                        <%--<input id="Permanent Address" name="Permanent Address" type="text" placeholder="Street" class="form-control input-md ">--%>
                    <%--</div>--%>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Phone_number ">Phone number </label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <input id="Phone_number " name="Phone number " type="text" placeholder="Primary Phone number "
                                   class="form-control input-md" value="${person.primaryPhone}">
                        </div>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Email_Address">Email Address</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope-o"></i>
                            </div>
                            <input id="Email_Address" name="Email Address" type="text" placeholder="Email Address"
                                   class="form-control input-md">
                        </div>
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Overview">Overview (max 200 words)</label>
                    <div class="col-md-4">
                            <textarea class="form-control" rows="10"  id="Overview"
                                      name="Overview (max 200 words)" value="${person.overview}">Overview</textarea>
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

<script src="/js/profile.js"></script>

<%@ include file="/layout/footer.jsp" %>
<%--<jsp:include page="/layout/footer.jsp"/>--%>