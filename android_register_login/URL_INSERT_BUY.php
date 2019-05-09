<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $getID = $_POST['getID'];
    $id = $_POST['id'];
    $msg = $_POST['msg'];
    $card_numer = $_POST['card_numer'];
    $MM = $_POST['MM'];
    $YY = $_POST['YY'];
    $total = $_POST['total'];

    require_once 'connect.php';

    $sql = "INSERT INTO buy_now (id_user , id_item , message, card, month, year, price) VALUES ('$getID', '$id', '$msg', '$card_numer', '$MM', '$YY', '$total')";

    if ( mysqli_query($conn, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);

    } else {

        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}

?>
