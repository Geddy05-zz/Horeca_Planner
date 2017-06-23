/**
 * Created by Geddy on 10-5-2017.
 */

function getLocations(user) {
    console.log(user);

    $.ajax({
        type: "GET",
        url: "/getLocations",
        data: {userMail: user},
        dataType: "json",
        success: function (response) {

            let xTable=document.getElementById('locationsList');
            console.log(response);

            for(let i = 0; i < response.length ; i++) {
                let tr=document.createElement('tr');
                tr.innerHTML = "<td><a href = '/location/"+response[i].id+"'>"+response[i].name+"</a></td>"+
                    "<td>"+ response[i].address +"</td>" +"<td>"+ response[i].city+"</td>";
                xTable.appendChild(tr);
            }
        }
    });
}


$(document).ready(function() {
    var backendHostUrl = 'http://localhost:8081';

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
                $("#userID").val(user.email);
                $("#userIDMain").val(user.email);
                getLocations(user.email);
            } else {

                // window.location = "/"
            }
        });
    }

    FirbaseInit();
    checkUser();
});

function showSucces(){
    var user = $("#userIDMain").val();
    getLocations(user);
    $("#alert").html(`<div id="savedLocation" class="alert alert-success" role="alert">
                            <strong>Done!</strong> You successfully saved a location
                        </div>`);

}

$("#addLocation").submit(function (e) {
    let url = "/locations";
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: url,
        async:false,
        data: $("#addLocation").serialize(),
    });
    showSucces();
});
