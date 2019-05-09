<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    $id=$_POST['id'];
    require_once 'connect.php';
    $stmt = $conn->prepare("SELECT photo FROM users_table where id = '$id'");
    $stmt->execute();

    $stmt->bind_result($photo);
    $product = array();
    while($stmt->fetch()) {

	$temp = array();
	$temp['photo'] = $photo;
	array_push($product, $temp);
    }  
 	echo json_encode($product);
 }else { 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 }
 
 ?>
