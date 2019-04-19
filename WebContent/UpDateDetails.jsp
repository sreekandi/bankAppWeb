<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
 
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>

<h3>Update Account Details</h3>

<div class="container">
  <form action="UpDateDetails.do" method="post">
    
  </br>
    <label for="lname">  Account number </label>
    <input type="tel"  name="Accountno"  value="${account.accountId}" readonly="readonly"></br>
    <label for="lname">  AccountHolderName </label>
    <input type="tel"  name="AccountHolderName"  value="${account.accountHolderName}"></br>
   
    <label for="account type">Account Type</label> <select
			name="accounttype">
			<option value="Cuurent">Current</option>
			<option value="Savings">Savings</option>
		</select>
		<label for="lname">  AccountType</label>
    <input type="tel" name="AccountBalance"  value="${account.accountBalance}"></br>
  
    <button type="submit" value="Update Details">UpdateDetails</button>
  </form>
</div>

</body>
</html>
