<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Page d'Accueil</title>
    <link rel="stylesheet" type="text/css" href="css/acceuil.css">
</head>
<body>

<div class="container">
    <h1>Bienvenue sur la Gestion de l'Entreprise</h1>
    <div class="button-group">
        <!-- Bouton pour accéder à la gestion des stocks -->
        <form action="${pageContext.request.contextPath}/gestionColis.jsp" method="get">
            <button type="submit">Stock</button>
        </form>
        <!-- Boutons fictifs pour les autres fonctionnalités -->
        <button onclick="alert('Fonctionnalité en développement.');">Personnel</button>
        <button onclick="alert('Fonctionnalité en développement.');">Transport</button>
    </div>
</div>

</body>
</html>
