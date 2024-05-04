package gestionstock.controlstock;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionstock.modelstock.Colis;
import gestionstock.modelstock.Materiel;
import gestionstock.modelstock.ModelStock;
import dao.DaoManager;

@WebServlet("/Control")
public class Control extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Control() {
        super();
    }

    /**
     * Méthode doGet pour gérer les requêtes GET.
     * 
     * @param request  l'objet HttpServletRequest contenant les informations de la requête
     * @param response l'objet HttpServletResponse pour envoyer la réponse
     * @throws ServletException si une erreur de servlet se produit
     * @throws IOException      si une erreur d'entrée/sortie se produit
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère le paramètre 'action' de la requête pour déterminer l'action à effectuer.
        String action = request.getParameter("action");

        try {
            // Utilisation d'un switch pour traiter différentes actions basées sur le paramètre 'action'.
            switch (action) {
                case "viewStock":
                    // Récupération des colis depuis la base de données et stockage dans l'attribut de requête.
                    request.setAttribute("listeColis", DaoManager.getInstance().getDaoStock().getAllColis());
                    // Transfère la requête vers la page JSP pour afficher les colis.
                    request.getRequestDispatcher("voirColis.jsp").forward(request, response);
                    break;
                case "addColis":
                    // Appelle la méthode pour ajouter un colis.
                    addColis(request);
                    // Redirige l'utilisateur vers la page de gestion des colis après ajout.
                    response.sendRedirect("gestionColis.jsp");
                    break;
                case "deleteColis":
                    // Appelle la méthode pour supprimer un colis.
                    deleteColis(request);
                    // Redirige l'utilisateur vers la page de gestion des colis après suppression.
                    response.sendRedirect("gestionColis.jsp");
                    break;
                case "updateColis":
                    // Appelle la méthode pour mettre à jour un colis.
                    updateColis(request);
                    // Redirige l'utilisateur vers la page de gestion des colis après mise à jour.
                    response.sendRedirect("gestionColis.jsp");
                    break;
                case "ajouterMaterielAuColis":
                    // Appelle la méthode pour ajouter du matériel à un colis.
                    ajouterMaterielAuColis(request, response);
                    break;
                default:
                    // Définit un message d'erreur si l'action n'est pas reconnue.
                    request.setAttribute("errorMessage", "Action non reconnue");
                    // Transfère la requête vers la page d'erreur.
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            // Attrape les exceptions générales, définit un message d'erreur et transfère vers la page d'erreur.
            request.setAttribute("errorMessage", "Une erreur est survenue : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Méthode doPost pour gérer les requêtes POST.
     * 
     * @param request  l'objet HttpServletRequest contenant les informations de la requête
     * @param response l'objet HttpServletResponse pour envoyer la réponse
     * @throws ServletException si une erreur de servlet se produit
     * @throws IOException      si une erreur d'entrée/sortie se produit
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    /**
     * Méthode pour ajouter un colis.
     * 
     * @param request l'objet HttpServletRequest contenant les informations de la requête
     */
    private void addColis(HttpServletRequest request) {
        String nature = request.getParameter("nature");
        double volume = Double.parseDouble(request.getParameter("volume"));
        String cotes = request.getParameter("cotes");
        String designation = request.getParameter("designation");
        int valeurHT = Integer.parseInt(request.getParameter("valeurHT"));
        Colis colis = new Colis(-1, nature, volume, cotes, designation, valeurHT);
        ModelStock.getInstance().addColis(colis);
    }

    /**
     * Méthode pour mettre à jour un colis.
     * 
     * @param request l'objet HttpServletRequest contenant les informations de la requête
     */
    private void updateColis(HttpServletRequest request) {

        int identifiant = Integer.parseInt(request.getParameter("identifiant"));
        String nature = request.getParameter("nature");
        double volume = Double.parseDouble(request.getParameter("volume"));
        String cotes = request.getParameter("cotes");
        String designation = request.getParameter("designation");
        int valeurHT = Integer.parseInt(request.getParameter("valeurHT"));

        ModelStock.getInstance().refreshColisList();

        // Obtenez l'instance de ModelStock et mettez à jour le colis
        ModelStock modelStock = ModelStock.getInstance();
        Colis colis = new Colis(identifiant, nature, volume, cotes, designation, valeurHT);
        modelStock.updateColis(colis);

    }

    /**
     * Méthode pour supprimer un colis.
     * 
     * @param request l'objet HttpServletRequest contenant les informations de la requête
     */
    private void deleteColis(HttpServletRequest request) {
        int identifiant = Integer.parseInt(request.getParameter("identifiant"));
        ModelStock.getInstance().deleteColis(identifiant);

    }

    /**
     * Méthode pour ajouter du matériel à un colis.
     * 
     * @param request  l'objet HttpServletRequest contenant les informations de la requête
     * @param response l'objet HttpServletResponse pour envoyer la réponse
     * @throws ServletException si une erreur de servlet se produit
     * @throws IOException      si une erreur d'entrée/sortie se produit
     */
    private void ajouterMaterielAuColis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int colisId = Integer.parseInt(request.getParameter("colisId"));
            String nom = request.getParameter("nom");
            String fonction = request.getParameter("fonction");

            Materiel materiel = new Materiel(0, nom, fonction);
            DaoManager.getInstance().getDaoStock().ajouterMaterielAuColis(colisId, materiel);
            response.sendRedirect("gestionColis.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Format de numéro invalide pour l'ID du colis");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erreur lors de l'ajout du matériel au colis: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
    
   

