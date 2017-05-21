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
    $('.navbar-brand').text("Welcome "+jsonData.username);
}