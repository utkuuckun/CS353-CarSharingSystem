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
    timeh = $('#starthour').val();
    timem = $('#startminute').val();

    ch1_name = $('[name=name0]').val();
    ch1_price = $('[name=price0]').val();
    ch1_h = $('[name=hour0]').val();
    ch1_m = $('[name=minute0]').val();

    ch1_name = $('[name=name1]').val();
    ch1_price = $('[name=price1]').val();
    ch1_h = $('[name=hour1]').val();
    ch1_m = $('[name=minute1]').val();


}

