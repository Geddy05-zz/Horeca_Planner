/**
 * Created by Geddy on 9-5-2017.
 */

$('#newProfile').hide();
$('#loading').show();

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
                console.log(user.email);
                $.ajax({
                    type: "POST",
                    url: "/login",
                    data: {userMail: user.email},
                    success: function (response) {

                            if(response == 0){
                                window.location = "/dashboard"
                            }else {
                                $('#newProfile').show();
                                $('#loading').hide();
                        }
                    }

                });
            } else {

                // window.location = "/"
            }
        });
    }

    function success(response) {
        if(response){
            $('#newProfile').show();
            $('#loading').hide();
        }
    }

    FirbaseInit();
    checkUser();
});

function logOut(){
    firebase.auth().signOut().then(function() {
        window.location = "/";
    }, function(error) {
        console.log(error);
    });
}