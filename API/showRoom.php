<?php 

$connection = mysqli_connect("localhost","root","","hotelSystem");
	
	$result = array();
	$result['data'] = array();
	$select= "SELECT * from 'room'";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['Name']= $row['0'];
			$index['Price']= $row['1'];
			$index['Type']   = $row['2'];
			$index['isAvailable'] = $row['3'];
			
			
			array_push($result['data'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>