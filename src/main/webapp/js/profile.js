/**
 * Created by Geddy on 11-5-2017.
 */

function getUserDetails(user){
    $("#userID").val(user.email);

    $.ajax({
        type: "GET",
        url: "/getPerson",
        data: {userMail: user.email},
        dataType: "json",
        success: function (person) {

            $("#Name").val(person.displayName);
            $("#DateOfBirth").val(person.dateOfBirth);
            // $("#Permanent_Address").val(user.email);
            $("#Phone_number").val(person.primaryPhone);
            // $("#Email_Address").val(person.id);
            $("#Overview").val(person.overviewl);
        }
    });
}

$(document).ready(function () {

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
                getUserDetails(user)
            } else {

            }
        });
    }

    FirbaseInit();
    checkUser();
});