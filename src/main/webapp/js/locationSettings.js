/**
 * Created by Geddy on 11-5-2017.
 */

// When the user clicks on i, open the popup
function popUpExperience() {
    let popup = document.getElementById("popUpExperience");
    popup.classList.toggle("show");
}

function popUpPrice() {
    let popup = document.getElementById("popUpPrice");
    popup.classList.toggle("show");
}

function prepareHTMLForEmployeeList(userID,locationID , employeeName, employeeID , skills,hours,nr){

    var skill = "";
    for(var i = 0 ; i < skills.length ; i++){
        if( i!= 0){
            skill += ", ";
        }
        skill += skills[i];
    }

    let style = (nr%2 == 0 ? "#F0F0F0" : "#FFFFFF");

    return `<div class="col-lg-12" style="background-color: ${style}">
    <form action="/location/deleteEmployee" id = '${employeeName}' method="post">
    <div class="col-md-4"> ${employeeName}
    </div>
    <div class="col-md-3">
    ${skill}
    <input type="hidden" name="userID" value= ${userID}>
    <input type="hidden" name="employeeId" value= ${employeeID}>
    <input type="hidden" name="locationId" value= ${locationID}>
</div>
<div class="col-md-3">
${hours}
</div>
<div class="col-md-2">

    <button class="btn" style="background-color: Transparent;" id =${employeeName} aria-label="Delete"> 

    <i class="fa fa-trash-o" aria-hidden="true" style="color:#c9302c;"></i>
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
                    let employeeHTML = prepareHTMLForEmployeeList(userID,locationID,employee.name, employee.id, employee.skills,employee.hoursInContract,i);

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
        async: false,
        dataType: "json",
        success: function (location) {
            {
                console.log(location);
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
                if (location.postal) $("#organisationPostal").text(location.postal);
                $("#organisationAddress").text(location.address);
                $("#organisationCity").text(location.city);

                if (location.openingHoursMonday) $("#moOpen").text("mo: " +location.openingHoursMonday);
                if (location.openingHoursTuesday) $("#tuOpen").text("tu: " +location.openingHoursTuesday);
                if (location.openingHoursWednesday) $("#weOpen").text("we: " +location.openingHoursWednesday);
                if (location.openingHoursThursday) $("#thOpen").text("th: " +location.openingHoursThursday);
                if (location.openingHoursFriday) $("#frOpen").text("fr: " +location.openingHoursFriday);
                if (location.openingHoursSaturday) $("#saOpen").text("sa: " +location.openingHoursSaturday);
                if (location.openingHoursSunday) $("#suOpen").text("su: " +location.openingHoursSunday);
            }
        }
    });
}

$(document).ready(function () {

    $("#csvForm").submit(function (event) {
        event.preventDefault();

        $("#uploadFile").hide();
        $("#spinner").html(`<i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i><span class="sr-only">Loading...</span>`);

        //grab all form data
        var formData = new FormData($(this)[0]);

        // create url
        var url = "uploadCSV/"+$("#locationIdCSV").val()+"/"+$("#userIDCSV").val();

        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                $("#uploadFile").show();
                $("#spinner").html("");
                if( returndata){
                    $("#alertViewCSV").html(`<div class="alert alert-success" role="alert">
                            <strong>Done!</strong> You successfully saved the data
                        </div>`)
                }else{
                    $("#alertViewCSV").html(`<div class="alert alert-danger" role="alert">
                            <strong>Oepss!</strong> Something went wrong check if type is csv.
                        </div>`)
                }
            }
        });
    });


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
                var action = $("#csvForm").attr("action");
                $("#csvForm").attr("action", action+"/"+user.email);
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

$("#addSales").submit(function(event){
    event.preventDefault();

    let userID = document.getElementById('userIDSales').value;

    // Get values from the form
    var date = $('input[name=dates]').val();
    var sales = $('input[name=number]').val();
    var locationId = $('input[name=locationId]').val();

    var pattern =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;

    // Check if date is valid
    if (date == null || date == "" || !pattern.test(date)) {

        // show error message
        $("#alertView").html(`<div id="savedLocation" class="alert alert-danger" role="alert">
                            <strong>Error!</strong> Invalid date check format DD/MM/YY
                        </div>`);
        return false;
    }

    // Prepare data for post
    var formData = {
        'locationId': locationId,
        'userID': userID,
        'date': date,
        'number': sales,
    };

    $.ajax({
        type: "POST",
        url: "/location/addSales",
        data: formData,
        dataType: "json",
        success: function (employeees) {
        }
    });

    // Show succes message
    $("#alertView").html(`<div id="savedLocation" class="alert alert-success" role="alert">
                            <strong>Done!</strong> You successfully saved sales data
                        </div>`
    );


});

$("#addEmployee").submit(function(event){
    let locationID = document.getElementById('locationId').value;
    let userID = document.getElementById('userID').value;

    var arr=[];

    $.each($("input[name=availableWeekdays]:checkbox:checked"),function(){
        arr.push($(this).val());
    });

    var skills=[];

    $.each($("input[name=skills]:checkbox:checked"),function(){
        skills.push($(this).val());
    });

    var formData = {
        'locationID' : locationID,
        'userMail' : userID,
        'name'              : $('input[name=name]').val(),
        'date'             : $('input[name=date]').val(),
        'skills'    : skills,
        'availableWeekdays'    : arr,
        'experience'    : $('select[name=experience]').val(),
        'price'             : $('input[name=price]').val(),
        'contractHours'             : $('input[name=contractHours]').val(),
    };

    console.log(formData);

    $.ajax({
        type: "POST",
        url: "/location/addEmployee",
        data: formData,
        dataType: "json",
        success: function (employeees) {
            location.reload(true);
        }
    });
    event.preventDefault();
    location.reload(true);
});

$("#helpItem").click(function () {
    var intro = introJs();
    intro.setOptions({
        steps: [
            {
                element: "#addEmployeePanel",
                intro: "In this panel you can add new employees. The system need this employees for creating a schedule",
                position: 'bottom'
            },
            {
                element: '#UploadCSV',
                intro: "In this panel you have to upload a csv file with your sales data, neerslag,",
                position: 'bottom'
            },{
                element: '#addSales',
                intro: "In this panel you can add sales data for a single day. Becareful with the data format it is DD/MM/YYYY",
                position: 'bottom'
            },
        ],
        showStepNumbers:false
    });

    intro.start();
});