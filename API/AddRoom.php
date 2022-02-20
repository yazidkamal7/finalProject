<?php

    if(isset($_POST['NameRoom']) && isset($_POST['PriceRoom']) && isset($_POST['TypeRoom'])){
   
    require_once "connectTODB.php";
  
    $name =$_POST['NameRoom'];
    $price = $_POST['PriceRoom'];
    $type = $_POST['TypeRoom'];

    $sql = "INSERT INTO `room`(`Name`, `Price`, `Type`, `isAvailable`) VALUES ('$name','$price','$type','1')"; 


    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}else{
    echo "filed";
}

?>