import service.TaskManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("===== BIENVENUE DANS L'APPLICATION TO-DO LIST =====");

        // Création du gestionnaire de tâches
        TaskManager taskManager = new TaskManager();

        // Affichage du menu principal
        taskManager.showMainMenu();
    }
}