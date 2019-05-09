<?php

if ($_SERVER['REQUEST_METHOD']=='GET') {
    
    require_once 'connect.php';
    $stmt = $conn->prepare("SELECT id, title, description, rating, price, imageURL FROM Product");
    $stmt->execute();

    $stmt->bind_result($id, $title, $description, $rating, $price, $image);
    $product = array();
    while($stmt->fetch()) {

	$temp = array();
        $temp['id'] = $id;
	$temp['title'] = $title;
	$temp['description'] = $description;
	$temp['rating'] = $rating;
	$temp['price'] = $price;
	$temp['imageURL'] = $image;
	

	array_push($product, $temp);
    }  
 	echo json_encode($product);
 }else { 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 }
 
 ?>
