$(document).ready(function(){
    // This is the host for the backend.
    // TODO: When running Firenotes locally, set to http://localhost:8081. Before
    // deploying the application to a live production environment, change to
    // https://backend-dot-<PROJECT_ID>.appspot.com as specified in the
    // backend's app.yaml file.
    var newUser = false;

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

    // This is passed into the backend to authenticate the user.
    var userIdToken = null;

    // Firebase log-in
    function configureFirebaseLogin() {

        firebase.initializeApp(config);

        // [START onAuthStateChanged]
        firebase.auth().onAuthStateChanged(function(user) {
            if (user) {
                    window.location = "/createProfile";
            } else {
                $('#logged-in').hide();
                $('#logged-out').show();

            }
            // [END onAuthStateChanged]

        });

    }

    // [START configureFirebaseLoginWidget]
    // Firebase log-in widget
    function configureFirebaseLoginWidget(registration) {
        var succesURL = "/";
        if(registration){
            succesURL = "/createProfile"
        }

        console.log(succesURL);
        var uiConfig = {
            queryParameterForWidgetMode: 'mode',
            // Query parameter name for sign in success url.
            queryParameterForSignInSuccessUrl: 'signInSuccessUrl',
            // Will use popup for IDP Providers sign-in flow instead of the default, redirect.
            signInFlow: 'popup',
            signInSuccessUrl: '/createProfile',
            // Will use popup for IDP Providers sign-in flow instead of the default, redirect.
            // signInSuccessUrl: '/createProfile',
            signInOptions: [
                // Leave the lines as is for the providers you want to offer your users.
                firebase.auth.GoogleAuthProvider.PROVIDER_ID,
                // firebase.auth.FacebookAuthProvider.PROVIDER_ID,
                // firebase.auth.TwitterAuthProvider.PROVIDER_ID,
                // firebase.auth.GithubAuthProvider.PROVIDER_ID,
                firebase.auth.EmailAuthProvider.PROVIDER_ID
            ],
            // Terms of service url
            tosUrl: '<your-tos-url>',
        };

        var ui = new firebaseui.auth.AuthUI(firebase.auth());
        ui.start('#firebaseui-auth-container', uiConfig);

    }

    // [START signOutBtn]
    // Sign out a user
    var signOutBtn =$('#sign-out');
    signOutBtn.click(function(event) {
        event.preventDefault();

        firebase.auth().signOut().then(function() {
            console.log("Sign out successful");
        }, function(error) {
            console.log(error);
        });
    });
    // [END signOutBtn]

    configureFirebaseLogin();

    document.getElementById("login").onclick = function() {
        configureFirebaseLoginWidget(false);
        $('#main').hide();
        $('#log-in').show();
    }

    document.getElementById("aanmelden").onclick = function() {
        configureFirebaseLoginWidget(true);
        newUser = true;
        $('#main').hide();
        $('#log-in').show();
    }
});
