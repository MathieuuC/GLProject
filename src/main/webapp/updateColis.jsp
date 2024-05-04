<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="gestionstock.modelstock.Colis, dao.DaoManager"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mise à jour du Colis</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
</head>
<body>
    <%
        String idString = request.getParameter("id");
        Colis colisToUpdate = null;
        if (idString != null && !idString.trim().isEmpty()) {
            try {
                int colisId = Integer.parseInt(idString);
                colisToUpdate = DaoManager.getInstance().getDaoStock().getColisById(colisId);
            } catch (NumberFormatException e) {
                out.println("<p>Identifiant invalide. Veuillez réessayer.</p>");
            }
        }

        if (colisToUpdate == null) {
            out.println("<p>Colis non trouvé. Veuillez réessayer avec un identifiant valide.</p>");
        } else {
    %>
    <h1>Mise à jour du Colis</h1>
    <form action="Control" method="POST">
        <input type="hidden" name="action" value="updateColis">
        <input type="hidden" name="identifiant" value="<%= colisToUpdate.getIdentifiant() %>">

        <label for="nature">Nature:</label>
        <input type="text" id="nature" name="nature" value="<%= colisToUpdate.getNature() %>" required><br><br>

        <label for="volume">Volume:</label>
        <input type="text" id="volume" name="volume" value="<%= colisToUpdate.getVolume() %>" required><br><br>

        <label for="cotes">Cotes:</label>
        <input type="text" id="cotes" name="cotes" value="<%= colisToUpdate.getCotes() %>" required><br><br>

        <label for="designation">Désignation:</label>
        <input type="text" id="designation" name="designation" value="<%= colisToUpdate.getDesignation() %>" required><br><br>

        <label for="valeurHT">Valeur HT:</label>
        <input type="number" id="valeurHT" name="valeurHT" value="<%= colisToUpdate.getValeurHT() %>" required><br><br>

        <button type="submit">Mettre à jour le colis</button>
    </form>
    <% } %>
</body>
</html>
