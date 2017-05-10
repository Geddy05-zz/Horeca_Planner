/**
 * Created by Geddy on 8-5-2017.
 */
firebase.auth().onAuthStateChanged(function(user) {
    if (!user) {
        window.location = "/";
    }
});