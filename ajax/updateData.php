<?php
    session_start();
    require_once('database_credentials.php');
    $row = "";

    $conn = mysqli_connect($HOST , $NAME , $PASS , $DB_NAME);
    if(!$conn){
        $toprint = array('status' => 'Error');
        echo json_encode($toprint);
        die();
    }

    $id = $_SESSION['id'];
    $name = $_POST['name'];
    $surname = $_POST['sname'];
    $birth_date = $_POST['dob'];
    $gender = $_POST['gen'];
    $phone_number = $_POST['pno'];

    $year = date("Y");
    $age =  $year - $birth_date;

    $sql = "UPDATE user SET name = '$name', surname = '$surname', age = '$age', gender = '$gender', phone_number = $phone_number, birth_year = $birth_date WHERE user_id = $id";
    $result = $conn->query($sql);

    if(!$result){
        $toprint = array('status' => 'Failure');
    }
    else{
        $toprint = array('status' => 'Success');
    }
    echo json_encode($toprint);
?>