<?php
if(isset($_POST['name']) && isset($_POST['username']) && isset($_POST['password']) && isset($_POST['sallary'])){
    require_once "connectTODB.php";
    $name = $_POST['name'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $sallary = $_POST['sallary'];
    $sql = "INSERT INTO `employess`(`Name`, `userName`, `password`, `Sallary`) VALUES ('$name','$username','$password','$sallary')";
    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}
else{
    echo "NO THing";
}
?>