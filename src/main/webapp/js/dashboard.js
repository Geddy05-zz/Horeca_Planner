/**
 * Created by Geddy on 27-3-2017.
 */
$( function() {
    $( "#datepicker" ).datepicker({ defaultDate: new Date()});
    // drawSalesGraph()
} );

$.ajax({
    url: '/getSales',
    type: "GET",
    dataType: "json",
    success: function (data) {
        // drawSalesGraph();
    }
});

function drawSalesGraph(){
    new Morris.Line({
        // ID of the element in which to draw the chart.
        element: 'morris-sales-chart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data:[ { date:  "29-3-2017" , waiters:  5, barkeepers: 2, kitchen: 3},
            { date:  "30-3-2017" , waiters:  6, barkeepers: 4, kitchen: 4},
            { date:  "31-3-2017" , waiters:  8, barkeepers: 5, kitchen: 6},
            { date:  "1-4-2017" , waiters:  8, barkeepers: 5, kitchen: 6},
            { date:  "2-4-2017" , waiters:  7, barkeepers: 4, kitchen: 5}
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