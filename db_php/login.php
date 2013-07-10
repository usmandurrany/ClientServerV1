<?php

    #Ensure that the client has provided a value for "FirstNameToSearch"
   // if (isset($_POST["FirstNameToSearch"]) && $_POST["FirstNameToSearch"] != ""){
        
        #Setup variables
       // $firstname = $_POST["FirstNameToSearch"];
        
        #Connect to Database
        $con = mysql_connect("localhost","root","");
		mysql_select_db("ubit");
        
        #Check connection
        if (!mysql_connect()) {
            echo 'Database connection error: ' . mysql_error();
            exit();
        }

        #Escape special characters to avoid SQL injection attacks
     //   $firstname = mysql_real_escape_string($firstname);
        
        #Query the database to get the user details.
        $userdetails = mysql_query("SELECT * FROM student",$con);

        #If no data was returned, check for any SQL errors
        if (!$userdetails) {
            echo 'Could not run query: ' . mysql_error($con);
            exit;
        }

        #Get the first row of the results
       // $row = mysql_fetch_row($userdetails);

        #Build the result array (Assign keys to the values)
		while($row = mysql_fetch_array($userdetails))
		{
        $result_data[] = array($row[1]);
}
			echo json_encode($result_data); 

        #Output the JSON data
        
   // }else{
     //   echo "Could not complete query. Missing parameter"; 
    //} 
	?>