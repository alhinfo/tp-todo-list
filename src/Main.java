import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== BIENVENUE DANS L'APPLICATION TO-DO LIST =====");

        // Création du gestionnaire de tâches
        TaskManager taskManager = new TaskManager();

        // Affichage du menu principal
        taskManager.showMainMenu();
    }
}