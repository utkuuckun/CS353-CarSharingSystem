$(document).ready(function () {
        $.post( "ajax/getUserData.php",function( returneddata ) {
            returneddata = JSON.parse(returneddata);
            if(returneddata.status==="Error"){
                alert("Error connecting to the database");
            }
            else{
                if(returneddata.status==="Failure"){
                    window.location.replace('index.html');
                }
                else{
                    loadpage(returneddata);
                }
            }
        });
    }
);

function loadpage(jsonData) {
    console.log(jsonData);
    $('.navbar-brand').text("Profile of "+jsonData.username);
    $('#namesnamespan').text(""+jsonData.data.name+" "+jsonData.data.surname);
    $('#emailspan').text(""+jsonData.email);
    $('#dobspanspan').text(jsonData.data.birth_year);
    $('#genderspan').text(jsonData.data.gender);
    $('#phonespan').text(jsonData.data.phone_number);
}

function updateData() {
    name = $('#dataName').val();
    sname = $('#dataSurname').val();
    year = $('#dataYear').val();
    gen = $('#dataGender').val();
    pno = $('#dataNumber').val();

    $.post( "ajax/updateData.php",{'name':name,'sname':sname,'dob':year,'gen':gen,'pno':pno}
           ,function( returneddata ) {
        returneddata = JSON.parse(returneddata);
        if(returneddata.status==="Error"){
            alert("Error connecting to the database");
        }
        else{
            if(returneddata.status==="Failure"){
                window.location.replace('index.html');
            }
            else{
                alert("Data updated successfully! Refresh to see the changes!");
            }
        }
    });
}