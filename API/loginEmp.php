<?php


if(isset($_GET['username']) && isset($_GET['password'])){
	require_once "connectTODB.php";
    $username = $_GET['username'];
	$password=$_GET['password'];
    // Create the SQL query string
	$resultarray = array();
        $sql = "SELECT userType FROM users WHERE name='$email'and password='" .$password. "'";
		
		$result = $conn->query($sql);
		$resultarray = array();
		while($row= mysqli_fetch_array($result))
		{
			$resultarray[] = $row;
		}
		echo json_encode($resultarray);
		
		$conn->close();
		
	}
	
?>

