<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Supprimer un Colis</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
</head>
<body>

<div class="container">
    <h1>Supprimer un Colis</h1>
    <form action="Control" method="post">
        <input type="hidden" name="action" value="deleteColis"/>
        Identifiant du colis à supprimer: <input type="number" name="identifiant" required/><br/>
        <input type="submit" value="Supprimer Colis"/>
    </form>
</div>

</body>
</html>
