<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <h1>Display All Account Details</h1>
    <table class="table table-bordered">
            <thead>
              <tr>
               
                <th scope="col">account Id</th>
                <th scope="col">customer name</th>
                <th scope="col">account_type</th>
               <th scope="col">balance_amount</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="account" items="${accounts}" >
            	<tr>
            	<td>${account.accountId}</td>
            	<td>${account.accountHolderName}</td>
            	<td>${account.accountType}</td>
            	<td>${account.accountBalance}</td>
            	</tr>
            </c:forEach>
              
            </tbody>
          </table>
</body>
</html>