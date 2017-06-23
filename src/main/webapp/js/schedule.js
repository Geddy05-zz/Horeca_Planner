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
    // let html = "<div class='row'><br\/>";
    let weekday = 0;
    if (json.length > 0) {
        for (var i = 0 ; i < json.length; i++) {
            weekday++;
            var dayName = getWeekname(weekday);
            // html += "<h2>"+dayName+"</h2>";
            getDaySchedule(json[i],dayName);
            // html += "</div>"
        }
    }
    // $('#schedule').html(html);
}

function getWeekname(day){
    if(day === 1){
    return "Monday"
    }else if(day === 2){
        return "Tuesday"
    }else if(day === 3){
        return "Wednesday"
    }else if(day === 4){
        return "Thursday"
    }else if(day === 5){
        return "Friday"
    } else if(day === 6){
        return "Saturday"
    } else {
        return "Sunday"
    }
}

function getDaySchedule(day,weekday){

    // Loop shifts.
    for (let i = 0 ; i < day.length; i++) {

        let label = "16:00 - 22:00" ;
        let table;

        if(i === 0){
            label = "10:00 - 16:00"
        }

        // Loop for type of the shifts.
        for(let j = 0 ; j <  day[i].length; j++)  {

            // Load table from html.
            if (j === 0) {
                table = $("#waiter"+weekday);
            }else if (j === 1) {
                table = $("#bar"+weekday);
            }else{
                table = $("#kitchen"+weekday);
            }

            // Loop for planned employees if there is a table.
            if(table){
                for(let x = 0 ; x <  day[i][j].length; x++) {
                    table.append("<tr><td>"+ day[i][j][x].name+"</td><td>"+label+"</td></tr>")
                }
            }
        }
    }

    // Show schedule.
    $('#newSchedule').removeClass('hidden');
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
