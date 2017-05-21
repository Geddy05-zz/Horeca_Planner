function drawSalesChart (data){
    var dataList = [];
    for(var i = 0; i < data.length;i++){
        if(data[i][1] == "-"){
            dataList.push({year:  data[i][0]  ,  forecast: data[i][2] })
        }else{
            dataList.push({year:  data[i][0] ,value:  data[i][1]  ,  forecast: data[i][2] })
        }
    }

    new Morris.Line({
        // ID of the element in which to draw the chart.
        element: 'salesChart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data: dataList,
        // The name of the data record attribute that contains x-values.
        xkey: 'year',
        xLabels:"day",

        pointSize:0,
        // A list of names of data record attributes that contain y-values.
        ykeys: ['value','forecast'],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        resize: true,
        labels: ['Sales','Forecast']
    });
}

function getForecast(user) {
    console.log(user.email);

    $.ajax({
        type: "GET",
        url: "/dashboard/forecast",
        data: {userMail: user.email},
        async: true,
        success: function (response) {
            {
                console.log(response);
                drawSalesChart(response)
                // $('#newProfile').show();SS
                // $('#loading').hide();
            }
        }
    });
}

function setPanelUrl(locations){
    if (locations.length > 0) {
        let loc = locations[0];
        let url = "location/"+loc.id;
        $("#schedule").attr("href",url+"/createSchedule");
        $("#detail").attr("href",url);
    }
}

function getSales() {
    $.ajax({
        url: '/getSales',
        type: "GET",
        dataType: "json",
        async: true,
        success: function (data) {
            drawSalesGraph(data);
        }
    });
}

function tour() {
    let tour = new Tour({
        steps: [
            {
                element: "#detail",
                title: "location detail",
                content: "by clicking on this button you go to the detail page for the location. Here you can add sales data, employees and change the location details"
            },{
                element: "#Sales-Chart",
                title: "Sales",
                content: "After adding sales data you can see here a line chart with 2 lines. The blue one is the real sales data and the grey one shows you the prediction"
            },{
                element: "#Schedule",
                title: "Schedule",
                content: "When the sales prediction is made the program can a schedule for this location. NOTE: you first have to add employees to your location"
            },{
                element: "#Tasks-Panel",
                title: "Task panel",
                content: "In the task panel can you see the task you have to do for the best results of this system"
            },{
                element: "#Logbook",
                title: "Task panel",
                content: "Here you see the last actions the system did, When the schedule is created you see a message here"
            }
        ]
    });

    // Initialize the tour
    tour.init();

    // Start the tour
    tour.start();
}

function getLocations(user){
    $.ajax({
        type:"GET",
        url: "dashboard/getLocations",
        data: {userMail: user.email},
        dataType: "json",
        success: function (response) {
            console.log(response);
            setPanelUrl(response);
            getForecast(user);
            getSales();
            tour();
        }
    })
}

$(document).ready(function() {
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
        firebase.auth().onAuthStateChanged(function(user) {

            if (user) {
                getLocations(user);
            } else {

                // window.location = "/"
            }
        });
    }

    FirbaseInit();
    checkUser();
});


$("#datepicker").datepicker({
    dateFormat: "yy-mm-dd",
    onSelect: function (dateText) {
        $.ajax({
            url: '/getEmployeeDemand/'+dateText.toString(),
            type: "GET",
            dataType: "json",
            success: function (data) {
                showEmployeesNeeded(data);
            }
        })
    }
});

function showEmployeesNeeded(json){
    console.log(json);

    $("#waiters_needed").text(json.waiters);
    $("#barkeepers_needed").text(json.barkeepers);
    $("#kitchen_needed").text(json.kitchen);

}

function drawSalesGraph(data){
    new Morris.Line({
        // ID of the element in which to draw the chart.
        element: 'morris-sales-chart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data:[ { date:  "2017-3-29" , waiters:  5, barkeepers: 2, kitchen: 3},
            { date:  "2017-3-30" , waiters:  6, barkeepers: 4, kitchen: 4},
            { date:  "2017-3-31" , waiters:  8, barkeepers: 5, kitchen: 6},
            { date:  "2017-4-1" , waiters:  8, barkeepers: 5, kitchen: 6},
            { date:  "2017-4-2" , waiters:  7, barkeepers: 4, kitchen: 5}
        ],
        // The name of the data record attribute that contains x-values.
        xkey: 'date',
        xLabels:"day",

        pointSize:0,
        // A list of names of data record attributes that contain y-values.
        ykeys: ['waiters','barkeepers','kitchen'],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        resize: true,
        labels: ['waiters','barkeepers','kitchen']
});
}

