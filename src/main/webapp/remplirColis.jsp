<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter Matériel au Colis</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
</head>
<body>
<div>
    <h1>Ajouter Matériel au Colis</h1>
    <form action="Control" method="post">
        <input type="hidden" name="action" value="ajouterMaterielAuColis"/>
        
        <label for="colisId">ID du Colis:</label>
        <input type="number" id="colisId" name="colisId" required/><br/>
        
        <label for="nom">Nom du Matériel:</label>
        <input type="text" id="nom" name="nom" required/><br/>
        
        <label for="fonction">Fonction:</label>
        <input type="text" id="fonction" name="fonction" required/><br/>
        
        <input type="submit" value="Ajouter Matériel"/>
    </form>
</div>
</body>
</html>
