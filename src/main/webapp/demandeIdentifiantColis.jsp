<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Demande d'identifiant de Colis</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
</head>
<body>
    <h1>Entrer l'identifiant du Colis à mettre à jour</h1>
    <form action="updateColis.jsp" method="GET"> <!-- L'action dirige vers la page de mise à jour -->
        <label for="identifiant">Identifiant du Colis:</label>
        <input type="number" id="identifiant" name="id" required> <!-- Le champ pour l'identifiant -->
        <br><br>
        <button type="submit">Soumettre</button>
    </form>
</body>
</html>
