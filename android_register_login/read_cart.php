<?php

if ($_SERVER['REQUEST_METHOD'] =='POST') {

    $id = $_POST['id'];
    require_once 'connect.php';
     
    $sql = "SELECT distinct Product.imageURL, Product.description, Product.price, Product.id, Product.rating \n"

    . "FROM \n"

    . "order_item, users_table, Product \n"

    . "WHERE\n"

    . "order_item.id_user = '$id' and Product.id = order_item.id_product and users_table.id = '$id'";

    $stmt = $conn->prepare($sql);
    $stmt->execute();
    $stmt->bind_result($imageURL, $description, $price, $id, $rating);
    $product = array();
    while($stmt->fetch()) {

	$temp = array();
	$temp['image_url'] = $imageURL;
	$temp['description'] = $description;
	$temp['price'] = $price;
	$temp['id'] = $id;
        $temp['rating'] = $rating;

	array_push($product, $temp);
   }
        echo json_encode($product);
}else { 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 }

 ?>
