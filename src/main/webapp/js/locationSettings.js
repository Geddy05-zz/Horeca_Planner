/**
 * Created by Geddy on 11-5-2017.
 */


function prepareHTMLForEmployeeList(userID,locationID , employeeName, employeeID , skills){

    var skill = "";
    for(var i = 0 ; i < skills.length ; i++){
        if( i!= 0){
            skill += ", ";
        }
        skill += skills[i];
    }

    return `<div class="col-lg-12">
    <form action="/location/deleteEmployee" id = '${employeeName}' method="post">
    <div class="col-md-4"> ${employeeName}
    </div>
    <div class="col-md-6">
    ${skill}
    <input type="hidden" name="userID" value= ${userID}>
    <input type="hidden" name="employeeId" value= ${employeeID}>
    <input type="hidden" name="locationId" value= ${locationID}>
</div>
<div class="col-md-2">

    <button class="btn btn-danger" id =${employeeName} aria-label="Delete"> 

    <i class="fa fa-trash-o" aria-hidden="true"></i>
    </button>
    </div>
    </form>
    </div>`;

}

function getEmployees(){
    let locationID = document.getElementById('locationId').value;
    let userID = document.getElementById('userID').value;

    $.ajax({
        type: "GET",
        url: "/location/getEmployees",
        data: {userMail: userID,locationID: locationID},
        async: true,
        dataType: "json",
        success: function (employeees) {
            {
                for(let i =0 ; i < employeees.length; i++){
                    let employee = employeees[i];
                    let employeeHTML = prepareHTMLForEmployeeList(userID,locationID,employee.name, employee.id, employee.skills);

                    $('#employeeList').append(employeeHTML)
                }
            }
        }
    });
}

function getLocation(){
    let locationID = document.getElementById('locationIdEdit').value;
    let userID = document.getElementById('userIDOrganisation').value;
    $.ajax({
        type: "GET",
        url: "/getLocation",
        data: {userMail: userID,locationID: locationID},
        async: true,
        dataType: "json",
        success: function (location) {
            {
                // change values in organisation settings panel
                $("#nameEdit").val(location.name);
                $("#postalEdit").val(location.postal);
                $("#addressEdit").val(location.address);
                $("#CityEdit").val(location.city);
                $("#mo").val(location.openingHoursMonday);
                $("#tu").val(location.openingHoursTuesday);
                $("#we").val(location.openingHoursWednesday);
                $("#th").val(location.openingHoursThursday);
                $("#fr").val(location.openingHoursFriday);
                $("#sa").val(location.openingHoursSaturday);
                $("#su").val(location.openingHoursSunday);

                // change values in organisation detail
                $("#organisationName").text(location.name);
                $("#organisationPostal").text(location.postal);
                $("#organisationAddress").text(location.address);
                $("#organisationCity").text(location.city);
                $("#moOpen").text("mo: " +location.openingHoursMonday);
                $("#tuOpen").text(location.openingHoursTuesday);
                $("#weOpen").text(location.openingHoursWednesday);
                $("#thOpen").text(location.openingHoursThursday);
                $("#frOpen").text(location.openingHoursFriday);
                $("#saOpen").text(location.openingHoursSaturday);
                $("#suOpen").text(location.openingHoursSunday);
            }
        }
    });
}

function addEmployee() {
    console.log("fadfas");
    document.addEmployee.submit(function(event){
        let locationID = document.getElementById('locationId').value;
        let userID = document.getElementById('userID').value;

        var formData = {
            'locationID' : locationID,
            'userMail' : userID,
            'name'              : $('input[name=name]').val(),
            'date'             : $('input[name=date]').val(),
            'skills'    : $('input[name=skills]:checkbox:checked').val(),
            'availableWeekdays'    : $('input[name=availableWeekdays]:checkbox:checked').val(),
            'experience'    : $('select[name=experience]').val(),
            'price'             : $('input[name=price]').val(),
            'contractHours'             : $('input[name=contractHours]').val(),
        };

        $.ajax({
            type: "POST",
            url: "/location/addEmployee",
            data: formData,
            async: true,
            dataType: "json",
            success: function (employeees) {
                getEmployees()
            }
        });
    });
}

$(document).ready(function () {
    let backendHostUrl = 'http://localhost:8081';

    function FirbaseInit() {

        // Initialize Firebase
        // TODO: Replace with your project's customized code snippet
        let config = {
            apiKey: "AIzaSyAzxgKvTMUIO7X1SBlPLz0jt4WUIportt4",
            authDomain: "horecaplanner-159312.firebaseapp.com",
            databaseURL: "https://horecaplanner-159312.firebaseio.com",
            projectId: "horecaplanner-159312",
            storageBucket: "horecaplanner-159312.appspot.com",
            messagingSenderId: "1081580401496"
        };

        firebase.initializeApp(config);
    }

    function checkUser() {
        firebase.auth().onAuthStateChanged(function (user) {

            if (user) {
                $("#userID").val(user.email);
                $("#userIDSales").val(user.email);
                $("#userIDCSV").val(user.email);
                $("#userIDOrganisation").val(user.email);
                getEmployees();
                getLocation();
            } else {

            }
        });
    }

    FirbaseInit();
    checkUser();
});

$("#editOrganisation").submit(function (e) {
    let locationID = document.getElementById('locationId').value;
    let userID = document.getElementById('userID').value;
    let formdata =  $("#editOrganisation").serialize();
    formdata.locationID = locationID;
    formdata.userMail = userID;

    let url = "/editOrganisation";

    $.ajax({
        type: "POST",
        url: url,
        data: formdata,
        success: function (data) {
            alert(data);
            location.reload(true);
        }
    });

    console.log(formdata);
    e.preventDefault();
});