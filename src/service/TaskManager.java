package service;

import model.Category;
import model.Task;
import model.TodoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Service de gestion des tâches et de l'interface utilisateur
 */
public class TaskManager {
    private TodoList todoList;
    private Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructeur du gestionnaire de tâches
     */
    public TaskManager() {
        this.todoList = new TodoList();
        this.scanner = new Scanner(System.in);
        initializeDefaultCategories();
    }

    /**
     * Initialise des catégories par défaut
     */
    private void initializeDefaultCategories() {
        Category travail = new Category("Travail", "BLUE");
        Category personnel = new Category("Personnel", "GREEN");
        Category urgent = new Category("Urgent", "RED");
        Category etudes = new Category("Études", "YELLOW");

        todoList.addCategory(travail);
        todoList.addCategory(personnel);
        todoList.addCategory(urgent);
        todoList.addCategory(etudes);
    }

    /**
     * Charge une liste de tâches existante
     *
     * @param loadedList La liste chargée
     */
    public void setTodoList(TodoList loadedList) {
        if (loadedList != null) {
            this.todoList = loadedList;
        }
    }

    /**
     * Récupère la liste de tâches actuelle
     *
     * @return La liste de tâches
     */
    public TodoList getTodoList() {
        return todoList;
    }

    /**
     * Affiche le menu principal et gère les interactions
     */
    public void showMainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== TO-DO LIST APPLICATION =====");
            System.out.println("1. Afficher toutes les tâches");
            System.out.println("2. Afficher les tâches par catégorie");
            System.out.println("3. Afficher les tâches à faire");
            System.out.println("4. Afficher les tâches en retard");
            System.out.println("5. Afficher les tâches à venir (7 jours)");
            System.out.println("6. Ajouter une nouvelle tâche");
            System.out.println("7. Marquer une tâche comme terminée");
            System.out.println("8. Supprimer une tâche");
            System.out.println("9. Rechercher des tâches");
            System.out.println("10. Gérer les catégories");
            System.out.println("11. Sauvegarder la liste");
            System.out.println("12. Charger une liste");
            System.out.println("0. Quitter");
            System.out.print("\nChoisissez une option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayAllTasks();
                    break;
                case 2:
                    displayTasksByCategory();
                    break;
                case 3:
                    displayPendingTasks();
                    break;
                case 4:
                    displayOverdueTasks();
                    break;
                case 5:
                    displayUpcomingTasks();
                    break;
                case 6:
                    addNewTask();
                    break;
                case 7:
                    markTaskAsCompleted();
                    break;
                case 8:
                    deleteTask();
                    break;
                case 9:
                    searchTasks();
                    break;
                case 10:
                    manageCategoriesMenu();
                    break;
                case 11:
                    saveToFile();
                    break;
                case 12:
                    loadFromFile();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    /**
     * Affiche toutes les tâches
     */
    private void displayAllTasks() {
        List<Task> tasks = todoList.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à afficher.");
            return;
        }

        System.out.println("\n===== TOUTES LES TÂCHES =====");
        for (Task task : tasks) {
            System.out.println(task);
        }

        System.out.println("\nNombre total de tâches: " + tasks.size());
        System.out.printf("Taux de complétion: %.1f%%\n", todoList.getCompletionRate());
    }

    /**
     * Affiche les tâches par catégorie
     */
    private void displayTasksByCategory() {
        List<Category> categories = todoList.getCategories();

        if (categories.isEmpty()) {
            System.out.println("Aucune catégorie disponible.");
            return;
        }

        System.out.println("\n===== TÂCHES PAR CATÉGORIE =====");
        // Implementer affichage des tage par categorie
        // Tips utiliser un foreach
    }

    /**
     * Affiche les tâches non terminées
     */
    private void displayPendingTasks() {
        // Afficher les taches non terminer
    }

    /**
     * Affiche les tâches en retard
     */
    private void displayOverdueTasks() {
        List<Task> overdueTasks = todoList.getOverdueTasks();

        if (overdueTasks.isEmpty()) {
            System.out.println("Aucune tâche en retard.");
            return;
        }

        System.out.println("\n===== TÂCHES EN RETARD =====");
        for (Task task : overdueTasks) {
            System.out.println(task);
        }
    }

    /**
     * Affiche les tâches à venir dans les 7 prochains jours
     */
    private void displayUpcomingTasks() {

    }

    /**
     * Ajoute une nouvelle tâche
     */
    private void addNewTask() {
        List<Category> categories = todoList.getCategories();

        if (categories.isEmpty()) {
            System.out.println("Vous devez d'abord créer au moins une catégorie.");
            return;
        }

        System.out.println("\n===== AJOUTER UNE NOUVELLE TÂCHE =====");

        System.out.print("Titre: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Le titre ne peut pas être vide.");
            return;
        }

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        LocalDate dueDate = null;
        System.out.print("Date d'échéance (format: dd/MM/yyyy, laisser vide si aucune): ");
        String dueDateStr = scanner.nextLine().trim();

        if (!dueDateStr.isEmpty()) {
            try {
                dueDate = LocalDate.parse(dueDateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. La tâche sera créée sans date d'échéance.");
            }
        }

        // Afficher les catégories disponibles
        System.out.println("Catégories disponibles:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }

        System.out.print("Choisissez une catégorie (numéro): ");
        int categoryIndex;
        try {
            categoryIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (categoryIndex < 0 || categoryIndex >= categories.size()) {
                System.out.println("Catégorie invalide. Opération annulée.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Opération annulée.");
            return;
        }

        Category selectedCategory = categories.get(categoryIndex);
        Task newTask = new Task(title, description, dueDate, selectedCategory);

        if (todoList.addTask(newTask)) {
            System.out.println("Tâche ajoutée avec succès!");
        } else {
            System.out.println("Erreur lors de l'ajout de la tâche.");
        }
    }

    /**
     * Marque une tâche comme terminée
     */
    private void markTaskAsCompleted() {

    }

    /**
     * Supprime une tâche
     */
    private void deleteTask() {

    }

    /**
     * Recherche des tâches par mot-clé
     */
    private void searchTasks() {

    }

    /**
     * Affiche le menu de gestion des catégories
     */
    private void manageCategoriesMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== GESTION DES CATÉGORIES =====");
            System.out.println("1. Afficher toutes les catégories");
            System.out.println("2. Ajouter une nouvelle catégorie");
            System.out.println("3. Supprimer une catégorie");
            System.out.println("0. Retour au menu principal");
            System.out.print("\nChoisissez une option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayCategories();
                    break;
                case 2:
                    addNewCategory();
                    break;
                case 3:
                    deleteCategory();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    /**
     * Affiche toutes les catégories
     */
    private void displayCategories() {

    }

    /**
     * Ajoute une nouvelle catégorie
     */
    private void addNewCategory() {

    }

    /**
     * Supprime une catégorie
     */
    private void deleteCategory() {

    }

    /**
     * Sauvegarde la liste dans un fichier
     */
    private void saveToFile() {

    }

    /**
     * Charge une liste depuis un fichier
     */
    private void loadFromFile() {

    }
}
