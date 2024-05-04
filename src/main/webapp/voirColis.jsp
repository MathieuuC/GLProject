<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="gestionstock.modelstock.Colis" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voir Stock des Colis</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
    <script>
        function chercherColis() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("inputChercher");
            filter = input.value.toUpperCase();
            table = document.getElementById("tableColis");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }       
            }
        }
    </script>
</head>
<body>

<div>
    <h1>Liste des Colis</h1>
    <input type="text" id="inputChercher" onkeyup="chercherColis()" placeholder="Entrer l'identifiant du colis...">
    <table id="tableColis">
        <tr>
            <th>Identifiant</th>
            <th>Nature</th>
            <th>Volume</th>
            <th>Cotes</th>
            <th>Designation</th>
            <th>Valeur HT</th>
        </tr>
        <% 
           List<Colis> listeColis = (List<Colis>) request.getAttribute("listeColis");
           if (listeColis != null) {
               for (Colis colis : listeColis) {
        %>
        <tr>
            <td><%= colis.getIdentifiant() %></td>
            <td><%= colis.getNature() %></td>
            <td><%= colis.getVolume() %></td>
            <td><%= colis.getCotes() %></td>
            <td><%= colis.getDesignation() %></td>
            <td><%= colis.getValeurHT() %></td>
        </tr>
        <%   }
           }
        %>
    </table>
</div>

</body>
</html>
