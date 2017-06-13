/**
 * Created by Geddy on 17-5-2017.
 */

function nextWeek() {
    let week = parseInt(document.getElementById("weeknr").innerHTML);
    document.getElementById("weeknr").innerHTML = (week + 1);
    getSchedule();
}

function lastWeek() {
    let week = parseInt(document.getElementById("weeknr").innerHTML);
    document.getElementById("weeknr").innerHTML = (week - 1);
    getSchedule();
}

function drawSchedule(json) {
    let html = "<div class='row'><br\/>";
    if (json.length > 0) {
        for (var day in json) {
            weekday++;
            html += getWeekname(weekday);
            html += getDaySchedule(day);
        }
    }
    console.log(html);
    return html;
}

function getWeekname(day){
    if(day === 1){
    return "<h2>Monday</h2>"
    }else if(day === 2){
        return "<h2>Tuesday</h2>"
    }else if(day === 3){
        return "<h2>Wednesday</h2>"
    }else if(day === 4){
        return "<h2>Thursday</h2>"
    }else if(day === 5){
        return "<h2>Friday</h2>"
    } else if(day === 6){
        return "<h2>Saturday</h2>"
    } else {
        return "<h2>Sunday</h2>"
    }
}

function getDaySchedule(day){
    let html ="<div class='col-md-6'>";
    let count = 0;

    // loop shifts
    for (var shift in day) {
        count++;
        // name correct shift
        if(count === 1){
            html+= "<h4>Middag:</h4><table>";
        }else{
            html+= "<br/><h4>Avond:</h4><table>";
        }

        let typeCount = 0;
        // loop for type of the shifts
        for(let shiftType in shift) {
            typeCount++;

            if (typeCount === 1) {
                html+= "<td>Waiter</td>";
            }else if (typeCount === 2) {
                html += "<td>Bar</td>";
            }else{
                html += "<td>Kitchen</td>";
            }
            html += "<td>";

            // loop for planned employees
            for(let employee in shiftType) {
                html+= "<button type='button' class='btn btn-primary' style='margin-left: 10px;margin-right: 10px'>"+
                    employee.name+"</button>"
            }
        }
        html+= "</td> </tr>";
    }
    return html;
}

function getSchedule(){
    let usermail = $("#userID").val();
    let week = parseInt(document.getElementById("weeknr").innerHTML);
    $.ajax({
        type: "GET",
        url: "getSchedule",
        data: {userMail: usermail, weeknr : week},
        dataType: "json",
        success: function (response) {
            drawSchedule(response);
        }
    });
}

$(document).ready(function() {

    function FirbaseInit() {

        // Initialize Firebase
        // TODO: Replace with your project's customized code snippet
        var config = {
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
        firebase.auth().onAuthStateChanged(function(user) {

            if (user) {
                console.log(user.email);
                $("#userID").val(user.email);
                getSchedule()
            } else {

                // window.location = "/"
            }
        });
    }
    FirbaseInit();
    checkUser();
});
