$('.carousel').carousel({
    interval: 3000
})

function login() {
    username = $('#usrnm').text();
    password = $('#passwd').text();
    $.post( "ajax/login.php",{'un':username ,'pw':password},function( returneddata ) {
        returneddata = JSON.parse(returneddata);
        if(returneddata.status==="Error"){
            alert("Error connecting to the database");
        }
        else{
            if(returneddata.status==="Failure"){
                alert("Username/Password combination is wrong");
            }
            else{
                window.location.href = 'search.html';
            }
        }
    });
}

function register() {
    password = $('#pw').text();
    retrypassword = $('#retrypw').text();
    //must do this
    if (retrypassword != password){
        alert("Passwords don't match!");
        return;
    }
    role = $('#rolepicker label.active input').val();
    if(role != 1 && role != 2){
        alert("You must choose a role!");
        return;
    }
    username = $('#email').text();
    email = $('#un').text();

    $.post( "ajax/register.php",{'mail':email,'un':username, 'pw':password,'role':role},function( returneddata ) {
        returneddata = JSON.parse(returneddata);
        if(returneddata.status==="Error") {
            alert("Error connecting to the database");
        }
        else {
            if(returneddata.status==="Failure") {
                alert("Username/Password combination is not allowed");
            }
            else {
                window.location.href = 'search.html';
            }
        }
    });
}