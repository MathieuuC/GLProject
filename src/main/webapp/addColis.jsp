<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un nouveau Colis</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
</head>
<body>

<div>
    <h1>Ajouter un nouveau Colis</h1>
    <form action="Control" method="get">
        <input type="hidden" name="action" value="addColis"/>
        Nature : <input type="text" name="nature" required/><br/>
        Volume : <input type="number" name="volume" step="0.01" required/><br/>
        Cotes : <input type="text" name="cotes"/><br/>
        Designation : <input type="text" name="designation" required/><br/>
        Valeur HT : <input type="number" name="valeurHT" required/><br/>
        <input type="submit" value="Ajouter Colis"/>
    </form>
</div>

</body>
</html>
