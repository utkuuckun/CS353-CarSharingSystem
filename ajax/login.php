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

   $username = $_POST['un'];
   $password = $_POST['pw'];

   $sql = "SELECT * FROM user_login WHERE password = '$password' AND username = '$username'";
   $result = $conn->query($sql);


   if(!$row = mysqli_fetch_assoc($result)){
        $toprint = array('status' => 'Failure');
   }
   else {
       $_SESSION['id'] = $row['user_id'];
       $toprint = array('status' => 'Success','id'=>$row['user_id']);
   }
   echo json_encode($toprint);

?>
