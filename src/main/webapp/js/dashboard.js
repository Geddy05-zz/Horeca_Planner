function drawSalesChart (data){
    if(data.length > 0) {
        $("#salesChartNoData").hide();
        $("#salesChart").show();
        $("#salesChart").html("");

        // Set data in the correct format.
        let dataList = [];
        for (let i = 0; i < data.length; i++) {
            if (data[i][1] == "-") {
                dataList.push({year: data[i][0], forecast: data[i][2]})
            } else {
                dataList.push({year: data[i][0], value: data[i][1], forecast: data[i][2]})
            }
        }

        // Add data to the chart and draw the lines.
        new Morris.Line({
            element: 'salesChart',
            data: dataList,
            xkey: 'year',
            pointSize: 0,
            ykeys: ['value', 'forecast'],
            labels: ['Sales', 'Forecast'],
            resize: true
        });

    }else{
        $("#salesChartNoData").show();
        $("#salesChart").hide();
        $("#salesChart").html("");
    }
}

function getForecast(user,locationId) {
    console.log(user);
    $.ajax({
        type: "GET",
        url: "/dashboard/forecast",
        data: {userMail: user, locationId: locationId},
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

function setPanelUrl(val){
    if (val) {
        let url = "location/"+val;
        $("#schedule").attr("href",url+"/createSchedule");
        $("#scheduleLink").attr("href",url+"/createSchedule");
        $("#detail").attr("href",url);
    }
}

function createLocationSelector(locations){
    var inputSel = "";

    // Create a selector is there are locations
    if (locations.length > 0) {
        inputSel += "<select name='locations' id='locations' onchange='changeLocation(this);' value= "+ locations[0].id+">";
        $("#locationId").val(locations[0].id);

        for (var i = 0 ; i < locations.length; i++){

            inputSel += `<option value= ${locations[i].id} > ${ locations[i].name }</option>`
        }

        $("#locationSelector").html(inputSel);
    }
}

function getLogItems(user){
    $.ajax({
        type: "GET",
        url:"/getLogItems",
        data: {userMail: user},
        async: true,
        dataType: "json",
        success: function(data){
            $('#logList').html("");
            for(let i =0 ; i < data.length; i++) {
                let log = data[i];
                date = UnixDateToDate(log.date);
                let logHTML = `<tr> <td> ${date} </td> <td> ${log.message}</td></tr>`

                $('#logList').append(logHTML);
            }
        }
    });
}

function UnixDateToDate(unixDate){
    var date = new Date(unixDate*1000);

    // Hours part from the timestamp
    var hours = date.getHours();

    // Minutes part from the timestamp
    var minutes = "0" + date.getMinutes();

    // Seconds part from the timestamp
    var seconds = "0" + date.getSeconds();

    var year = date.getFullYear();
    var month = "0"+(date.getMonth() +1);
    var day = "0"+ date.getDate();

    let formattedTime =  day.substr(-2)+"/"+month.substr(-2)+"/"+year +" - "+hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
    return formattedTime;
}

function tour() {
    var intro = introJs();
    intro.setOptions({
        steps: [
            {
                element: "#step1",
                intro: "First we need some information of your company / location. By using this button you can add and edit a location",
                position: 'bottom'
            },
            {
                element: '#Sales-Chart',
                intro: "After you uploaded data of the previous 3 weeks. The application can predict your upcoming sales",
                position: 'bottom'
            },{
                element: '#panelEmployeeDemand',
                intro: "After the application create a sales forecast the application can show you your employee demand for a day",
                position: 'bottom'
            },{
                element: '#Logbook',
                intro: "Here you can find the last activities and it shows you when a new schedule is created",
                position: 'bottom'
            },
        ],
        showStepNumbers:false
    });

    intro.start();
}

function getLocations(user){
    $.ajax({
        type:"GET",
        url: "dashboard/getLocations",
        data: {userMail: user},
        dataType: "json",
        success: function (response) {
            console.log(response);
            $("#userId").val(user);
            createLocationSelector(response);
            if (response.length > 0) {
                setPanelUrl(response[0].id);
                getForecast(user,response[0].id);
            }
            let date = new Date();
            let dateText = "" +date.getDate()+"-"+ date.getMonth()+"-"+date.getFullYear();
            getEmployeeDemand(dateText);
            getLogItems(user);
        }
    })
}

function changeLocation (sel){
    $("#locationId").val(sel.value);
    let user = $("#userId").val();
    setPanelUrl(sel.value);
    getForecast(user, sel.value);
    getLogItems(user);
}

function showEmployeesNeeded(json){

    console.log(json);
    if(json.waiters) {
        console.log("hallo");
        $("#numberOfEmployees").show();
        $("#noDataEmployee").hide();

        $("#waiters_needed").text(json.waiters);
        $("#barkeepers_needed").text(json.barkeepers);
        $("#kitchen_needed").text(json.kitchen);
    }else{
        console.log("dag");
        $("#numberOfEmployees").hide();
        $("#noDataEmployee").show();
    }

}

function drawSalesGraph(data) {
    new Morris.Line({
        // ID of the element in which to draw the chart.
        element: 'morris-sales-chart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data: [{date: "2017-3-29", waiters: 5, barkeepers: 2, kitchen: 3},
            {date: "2017-3-30", waiters: 6, barkeepers: 4, kitchen: 4},
            {date: "2017-3-31", waiters: 8, barkeepers: 5, kitchen: 6},
            {date: "2017-4-1", waiters: 8, barkeepers: 5, kitchen: 6},
            {date: "2017-4-2", waiters: 7, barkeepers: 4, kitchen: 5}
        ],
        // The name of the data record attribute that contains x-values.
        xkey: 'date',
        xLabels: "day",

        pointSize: 0,
        // A list of names of data record attributes that contain y-values.
        ykeys: ['waiters', 'barkeepers', 'kitchen'],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        resize: true,
        labels: ['waiters', 'barkeepers', 'kitchen']
    });
}

function getEmployeeDemand(dateText) {
    $.ajax({
        url: '/getEmployeeDemand/'+dateText.toString(),
        type: "GET",
        data: {userMail: $("#userId").val(), locationId: $("#locationId").val()},
        dataType: "json",
        success: function (data) {
            showEmployeesNeeded(data);
        }
    });
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
                $("#userId").val(user.email);
                getLocations(user.email);
            } else {

                // window.location = "/"
            }
        });
    }

    FirbaseInit();
    checkUser();

    $("#datepicker").datepicker({
        dateFormat: "dd-mm-yy",
        // defaultDate: new Date(),
        onSelect: function (dateText) {
            getEmployeeDemand(dateText)
        }
    });

    $("#datepicker").datepicker('setDate',new Date());

});

$("#helpItem").click(function () {
    tour();
});
