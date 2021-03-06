var i=2;
$(document).ready(function(){
    $("#add_row").click(function(){
        $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='price"+i+"' type='text' placeholder='Price'  class='form-control input-md'></td><td><input  name='hour"+i+"' type='text' placeholder='Hour'  class='form-control input-md'></td><td><input  name='minute"+i+"' type='text' placeholder='Minutes'  class='form-control input-md'></td>");

        $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
        i++;
    });
    $("#delete_row").click(function(){
        if(i>2){
            $("#addr"+(i-1)).html('');
            i--;
        }
    });
});

function addtrip() {
    // API key: AIzaSyBQRntCNeFwwIN0el_h-WcP5-9hbzaIJlw
    timeh = $('#starthour').val();
    timem = $('#startmin').val();

    ch1_name = $('input[name=name0]').val();
    ch1_price = $('input[name=price0]').val();
    ch1_h = $('input[name=hour0]').val();
    ch1_m = $('input[name=minute0]').val();
    var ch1_lat=0, ch1_lon=0,ch2_lat=0, ch2_lon=0;
    ch2_name = $('input[name=name1]').val();
    ch2_price = $('input[name=price1]').val();
    ch2_h = $('input[name=hour1]').val();
    ch2_m = $('input[name=minute1]').val();

    //getting latitude and longitude
    $.when(
        $.get( "https://maps.googleapis.com/maps/api/geocode/json"
            ,{'address':encodeURIComponent(ch1_name),'key':'AIzaSyBQRntCNeFwwIN0el_h-WcP5-9hbzaIJlw'}
            ,function( returneddata ) {
                ch1_lat = returneddata.results[0].geometry.location.lat;
                ch1_lon = returneddata.results[0].geometry.location.lng;
            }),
        $.get( "https://maps.googleapis.com/maps/api/geocode/json"
            ,{'address':encodeURIComponent(ch2_name),'key':'AIzaSyBQRntCNeFwwIN0el_h-WcP5-9hbzaIJlw'}
            ,function( returneddata ) {
                ch2_lat = returneddata.results[0].geometry.location.lat;
                ch2_lon = returneddata.results[0].geometry.location.lng;
            })
        )
        .done(function () {
            requestdata = {'timeh':timeh,'timem':timem,'ch1_name':ch1_name,'ch1_price':ch1_price,'ch1_h':ch1_h,'ch1_m':ch1_m
                ,'ch2_name':ch2_name,'ch2_price':ch2_price,'ch2_h':ch2_h,'ch2_m':ch2_m,'ch1_lat':ch1_lat
                ,'ch2_lat':ch2_lat,'ch1_lon':ch1_lon,'ch2_lon':ch2_lon};
            console.log(requestdata);
            $.post( "ajax/insertTrip.php",requestdata,function( returneddata ) {
                returneddata = JSON.parse(returneddata);
                console.log(returneddata);
                if(returneddata.status==="Error"){
                    alert("Error connecting to the database");
                }
                else{
                    if(returneddata.status==="Failure"){
                        alert("Couldn't add the trip!")
                    }
                    else{
                        alert("Trip added successfully!")
                    }
                }
            });
        });
}

