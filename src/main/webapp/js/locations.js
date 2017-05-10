/**
 * Created by Geddy on 10-5-2017.
 */
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
                getForecast(user);
            } else {

                // window.location = "/"
            }
        });
    }

    function getForecast(user) {
        console.log(user.email);

        $.ajax({
            type: "GET",
            url: "/getLocations",
            data: {userMail: user.email},
            success: function (response) {
                {
                    console.log(response);
                    // $('#newProfile').show();SS
                    // $('#loading').hide();
                }
            }
        });
    }

    FirbaseInit();
    checkUser();
});
