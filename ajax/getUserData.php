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
    $id = $_SESSION['id'];

    $sql = "SELECT * FROM credentials_view WHERE user_id = '$id'";
    $result = $conn->query($sql);
    if(!$row = mysqli_fetch_assoc($result)){
       $toprint = array('status' => 'Failure');
    }
    else{
       $sql = "SELECT * FROM user WHERE user_id = '$id'";
       $result = $conn->query($sql);
       $toprint = array('id'=>$id,'username'=>$row['username'],'email'=>$row['email']);
       $row = mysqli_fetch_assoc($result);
       $toprint['data'] = $row;

       //checking if user is driver
       $sql = "SELECT * FROM passenger WHERE user_id = '$id'";
       $result = $conn->query($sql);
       if($row = mysqli_fetch_assoc($result)){
            $toprint['driver'] = 'False';
       }
       else{
            $toprint['driver'] = 'True';
       }
    }
    echo json_encode($toprint);
?>