<%--
  Created by IntelliJ IDEA.
  User: Geddy
  Date: 23-2-2017
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/layout/header.jsp"/>

<div class="row">
    <%-- Folding barbuttons for adding data--%>
    <div class="col-lg-3">
        <div class="panel panel-green">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#filterPanel">Add employees</a>
                    <span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion"
                          href="#filterPanel">
                    <i class="glyphicon glyphicon-chevron-down"></i>
                </span>
                </h4>
            </div>
            <div id="filterPanel" class="panel-collapse panel-collapse collapse">
                <div class="panel-body">
                    <form action="addEmployee" method="post">
                        <input id="locationId" type="hidden" name="locationId" value="${locationId}">
                        <input id="userID" type="hidden" name="userID">

                        <div class="form-group ">
                            <label class="control-label requiredField" for="name">
                                Name
                                <span class="asteriskField">
                             *
                         </span>
                            </label>
                            <input class="form-control" id="name" name="name" type="text"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label " for="name1">
                                Surname
                            </label>
                            <input class="form-control" id="name1" name="name1" type="text"/>
                        </div>

                        <div class="form-group ">
                            <label class="control-label " for="date">
                                Date
                            </label>
                            <input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField">
                                Select skills
                                <span class="asteriskField">
                            *
                        </span>
                            </label>
                            <div class="">
                                <div class="radio">
                                    <label class="radio">
                                        <input name="skills" type="checkbox" value="1"/>
                                        Waiter
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="skills" type="checkbox" value="2"/>
                                        Barkeeper
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="skills" type="checkbox" value="3"/>
                                        Kitchen
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField">
                                Select available days
                                <span class="asteriskField">
                            *
                        </span>
                            </label>
                            <div class="">
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="0"/>
                                        Monday
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="1"/>
                                        Tuesday
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="2"/>
                                        Wednesday
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="3"/>
                                        Thursday
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="4"/>
                                        Friday
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="5"/>
                                        Saturday
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <input name="availableWeekdays" type="checkbox" value="6"/>
                                        Sunday
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="price">
                                Price per hour
                                <span class="asteriskField">
                            *
                        </span>
                            </label>
                            <input class="form-control" id="price" name="price" type="text"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="price">
                                Price per hour
                                <span class="asteriskField">    *   </span>
                            </label>
                            <select name="experience">
                                <option value="1">New</option>
                                <option value="10">Junior</option>
                                <option value="15">Medior</option>
                                <option value="20">Senior</option>
                            </select>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="contractHours">
                                Hours in contract
                                <span class="asteriskField">
                            *
                        </span>
                            </label>
                            <input class="form-control" id="contractHours" name="contractHours" type="text"/>
                        </div>
                        <div class="form-group">
                            <div>
                                <button class="btn btn-primary " name="submit" type="submit">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="panel panel-yellow">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#uploadPanel">Upload Csv</a>
                    <span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion"
                          href="#uploadPanel">
                        <i class="glyphicon glyphicon-chevron-down"></i>
                    </span>
                </h4>
            </div>
            <div id="uploadPanel" class="panel-collapse panel-collapse collapse">
                <div class="panel-body">
                    <form class="form-horizontal" action="uploadCSV/${locationId}" method="POST"
                          enctype="multipart/form-data">
                        <div class="col-md-4 col-md-offset-4">
                            <div class="form-group">
                                <input id="userIDCSV" type="hidden" name="userID">
                                <div class="input-group">
                                    <div class="fileUpload btn btn-primary">
                                        <span >Select file</span>
                                        <input type="file" name="file" class="upload" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-md-offset-4">
                            <div class="form-group">
                                <input class="btn btn-success" type="submit" value="Submit"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="panel panel-red">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#salesPanel">Add sales data</a>
                    <span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion"
                          href="#salesPanel">
                        <i class="glyphicon glyphicon-chevron-down"></i>
                    </span>
                </h4>
            </div>
            <div id="salesPanel" class="panel-collapse panel-collapse collapse">
                <div class="panel-body">
                    <form action="addSales" method="post">
                        <input type="hidden" name="locationId" value="${locationId}">
                        <input id="userIDSales" type="hidden" name="userID">

                        <div class="form-group ">
                            <label class="control-label " for="date">
                                Date
                            </label>
                            <input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="number">
                                Sales a day
                                <span class="asteriskField">
                                *
                            </span>
                            </label>
                            <input class="form-control" id="number" name="number" type="text"/>
                        </div>
                        <div class="form-group">
                            <div>
                                <button class="btn btn-primary " name="submit" type="submit">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="panel panel-blue">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#organisationPanel">Organisation
                        settings</a>
                    <span class="pull-right panel-collapse-clickable" data-toggle="collapse" data-parent="#accordion"
                          href="#organisationPanel">
                        <i class="glyphicon glyphicon-chevron-down"></i>
                    </span>
                </h4>
            </div>
            <div id="organisationPanel" class="panel-collapse panel-collapse collapse">
                <div class="panel-body">
                    <form id="editOrganisation" class="form-horizontal"  method="POST">
                        <div class="form-group ">
                            <label class="control-label requiredField" for="nameEdit">
                                Name
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="form-control" id="nameEdit" name="name" type="text" />
                        </div> <%-- name --%>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="postalEdit">
                                Postal code
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="form-control" id="postalEdit" name="postal" type="text" />
                        </div> <%-- Postal code --%>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="addressEdit">
                                Address
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="form-control" id="addressEdit" name="Adress" type="text"/>
                        </div> <%-- Adress --%>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="CityEdit">
                                City
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="form-control" id="CityEdit" name="City" type="text" />
                        </div> <%-- City --%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="mo">
                                Monday
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="col-md-6" id="mo" name="mo" type="text" />
                        </div> <%-- monday--%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="tu">
                                Tuesday
                            </label>
                            <input class="col-md-6" id="tu" name="tu" type="text" />
                        </div> <%-- tuesday--%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="we">
                                Wednesday
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="col-md-6" id="we" name="we" type="text" />
                        </div> <%-- wednesday--%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="th">
                                Thursday
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="col-md-6" id="th" name="th" type="text" />
                        </div> <%-- thursday --%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="fr">
                                Friday
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="col-md-6" id="fr" name="fr" type="text" />
                        </div> <%-- friday--%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="sa">
                                Saturday
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="col-md-6" id="sa" name="sa" type="text" />
                        </div> <%-- saturday --%>
                        <div class="form-group col-md-12">
                            <label class="col-md-6" for="mo">
                                Sunday
                                <span class="asteriskField">
                                *
                                </span>
                            </label>
                            <input class="col-md-6" id="su" name="su" type="text" />
                        </div> <%-- sunday --%>
                        <input id="locationIdEdit" type="hidden" name="locationId" value="${locationId}">
                        <input id="userIDOrganisation" type="hidden" name="userID">
                        <div class="form-group">
                            <input class="btn btn-success" type="submit" value="submit" >
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="page-header">
    <%-- empty header gives a separator line --%>
</div>
<div class="row">

    <%-- Organistaion Detail --%>
    <div class="col-md-6">
        <h3> Organisation details</h3>
        <div class="col-lg-12">
            <div class="col-md-4">
                <label>Organisation name:</label>
            </div>
            <div class="col-md-8" id="organisationName">
                Incentro
            </div>
        </div>
        <div class="col-lg-12">
            <div class="col-lg-4" >
                <label>Postal code:</label>
            </div>
            <div class="col-md-8" id="organisationPostal">
                5555 LL
            </div>
        </div>
        <div class="col-lg-12">
            <div class="col-md-4">
                <label>Adress:</label>
            </div>
            <div class="col-md-8" id="organisationAddress">
                Papendorpseweg 107
            </div>
        </div>
        <div class="col-lg-12">
            <div class="col-md-4">
                <label>City:</label>
            </div>
            <div class="col-md-8" id="organisationCity">
                Utrecht
            </div>
        </div>
        <div class="col-lg-12">
            <div class="col-md-4">
                <label>Opening Hours:</label>
            </div>
            <div class="col-md-8">
                <ul style="list-style-type:none; padding: 0">
                    <li id="moOpen">mo: 14:00 - 20:00</li>
                    <li id="tuOpen">tu: 12:00 - 20:00</li>
                    <li id="weOpen">we: 12:00 - 21:00</li>
                    <li id="thOpen">th: 12:00 - 23:00</li>
                    <li id="frOpen">fr: 12:00 - 23:00</li>
                    <li id="saOpen">sa: 12:00 - 23:00</li>
                    <li id="suOpen">su: 12:00 - 21:00</li>
                </ul>
            </div>
        </div>
    </div>

    <%-- Employee list--%>
    <div class="col-md-6">
        <h3> Employee list</h3>
        <div class="col-lg-12">
            <div class="col-md-4">
                <label>Name:</label>
            </div>
            <div class="col-md-6">
                <label>Skils:</label>
            </div>
        </div>
        <div id="employeeList">

        </div>
    </div>
</div>

<script src="/js/locationSettings.js"></script>


<jsp:include page="/layout/footer.jsp"/>