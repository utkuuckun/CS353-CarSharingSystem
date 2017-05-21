<?php
  session_start();
    $HOST = "localhost";
    $NAME = "root";
    $PASS = "Asdf!234";
    $DB_NAME = "schoolData";
    $row = "";

    $conn = mysqli_connect($HOST , $NAME , $PASS , $DB_NAME);
    if(!$conn){
        $toprint = array('status' => 'Error');
        echo json_encode($toprint);
        die();
    }

    $mail = $_POST['mail'];
    $username = $_POST['un'];
    $pass = $_POST['pw'];
    $role = $_POST['role'];

    $sql = "INSERT INTO user_login(username, email, password) VALUES('$username', '$mail', '$pass')";
    $result = $conn->query($sql);

    $sql = "SELECT * FROM user_login WHERE password = '$pass' AND username = '$username'";
    $result = $conn->query($sql);

    if(!$row = mysqli_fetch_assoc($result)){
       $toprint = array('status' => 'Failure');
    }
    else {
        $id = $row['user_id'];
        $_SESSION['id'] = $id;
        if($role == 1){
            $sql = "INSERT INTO passenger VALUES('$id')";
        }
        else{
            $sql = "INSERT INTO driver VALUES('$id','$id')";
        }
        $result = $conn->query($sql);

        $toprint = array('status' => 'Success','id'=>$id);
    }

    echo json_encode($toprint);
 ?>
