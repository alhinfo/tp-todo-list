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

        for (Category category : categories) {
            List<Task> tasks = todoList.getTasksByCategory(category);
            System.out.println("\nCatégorie: " + category.getName() + " [" + tasks.size() + " tâches]");

            if (tasks.isEmpty()) {
                System.out.println("  Aucune tâche dans cette catégorie.");
            } else {
                for (Task task : tasks) {
                    System.out.println("  - " + task);
                }
            }
        }
    }

    /**
     * Affiche les tâches non terminées
     */
    private void displayPendingTasks() {
        List<Task> pendingTasks = todoList.getPendingTasks();

        if (pendingTasks.isEmpty()) {
            System.out.println("Aucune tâche en attente.");
            return;
        }

        System.out.println("\n===== TÂCHES À FAIRE =====");
        for (Task task : pendingTasks) {
            System.out.println(task);
        }
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
        List<Task> upcomingTasks = todoList.getUpcomingTasks(7);

        if (upcomingTasks.isEmpty()) {
            System.out.println("Aucune tâche à venir dans les 7 prochains jours.");
            return;
        }

        System.out.println("\n===== TÂCHES À VENIR (7 JOURS) =====");
        for (Task task : upcomingTasks) {
            System.out.println(task);
        }
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
        List<Task> pendingTasks = todoList.getPendingTasks();

        if (pendingTasks.isEmpty()) {
            System.out.println("Aucune tâche en attente.");
            return;
        }

        System.out.println("\n===== MARQUER UNE TÂCHE COMME TERMINÉE =====");
        System.out.println("Tâches en attente:");

        for (int i = 0; i < pendingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + pendingTasks.get(i));
        }

        System.out.print("Choisissez une tâche à marquer comme terminée (numéro): ");
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (taskIndex < 0 || taskIndex >= pendingTasks.size()) {
                System.out.println("Tâche invalide. Opération annulée.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Opération annulée.");
            return;
        }

        Task selectedTask = pendingTasks.get(taskIndex);
        selectedTask.markAsCompleted();
        System.out.println("Tâche marquée comme terminée!");
    }

    /**
     * Supprime une tâche
     */
    private void deleteTask() {
        List<Task> allTasks = todoList.getAllTasks();

        if (allTasks.isEmpty()) {
            System.out.println("Aucune tâche à supprimer.");
            return;
        }

        System.out.println("\n===== SUPPRIMER UNE TÂCHE =====");
        System.out.println("Tâches disponibles:");

        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println((i + 1) + ". " + allTasks.get(i));
        }

        System.out.print("Choisissez une tâche à supprimer (numéro): ");
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (taskIndex < 0 || taskIndex >= allTasks.size()) {
                System.out.println("Tâche invalide. Opération annulée.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Opération annulée.");
            return;
        }

        Task selectedTask = allTasks.get(taskIndex);

        System.out.print("Êtes-vous sûr de vouloir supprimer cette tâche? (O/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("O")) {
            if (todoList.removeTask(selectedTask)) {
                System.out.println("Tâche supprimée avec succès!");
            } else {
                System.out.println("Erreur lors de la suppression de la tâche.");
            }
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    /**
     * Recherche des tâches par mot-clé
     */
    private void searchTasks() {
        System.out.println("\n===== RECHERCHER DES TÂCHES =====");
        System.out.print("Entrez un mot-clé: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Mot-clé vide. Recherche annulée.");
            return;
        }

        List<Task> results = todoList.searchTasks(keyword);

        if (results.isEmpty()) {
            System.out.println("Aucun résultat trouvé pour '" + keyword + "'.");
        } else {
            System.out.println("\nRésultats de recherche pour '" + keyword + "':");
            for (Task task : results) {
                System.out.println(task);
            }
            System.out.println("\nNombre de résultats: " + results.size());
        }
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
        List<Category> categories = todoList.getCategories();

        if (categories.isEmpty()) {
            System.out.println("Aucune catégorie disponible.");
            return;
        }

        System.out.println("\n===== CATÉGORIES =====");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            int taskCount = todoList.getTasksByCategory(category).size();
            System.out.println((i + 1) + ". " + category.getName() +
                    " (Couleur: " + category.getColor() + ", " +
                    taskCount + " tâches)");
        }
    }

    /**
     * Ajoute une nouvelle catégorie
     */
    private void addNewCategory() {
        System.out.println("\n===== AJOUTER UNE NOUVELLE CATÉGORIE =====");

        System.out.print("Nom: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Le nom ne peut pas être vide.");
            return;
        }

        if (todoList.findCategoryByName(name) != null) {
            System.out.println("Une catégorie avec ce nom existe déjà.");
            return;
        }

        System.out.print("Couleur (ex: RED, BLUE, GREEN, etc.): ");
        String color = scanner.nextLine().trim();
        if (color.isEmpty()) {
            color = "BLACK"; // Couleur par défaut
        }

        Category newCategory = new Category(name, color.toUpperCase());

        if (todoList.addCategory(newCategory)) {
            System.out.println("Catégorie ajoutée avec succès!");
        } else {
            System.out.println("Erreur lors de l'ajout de la catégorie.");
        }
    }

    /**
     * Supprime une catégorie
     */
    private void deleteCategory() {
        List<Category> categories = todoList.getCategories();

        if (categories.isEmpty()) {
            System.out.println("Aucune catégorie à supprimer.");
            return;
        }

        System.out.println("\n===== SUPPRIMER UNE CATÉGORIE =====");
        System.out.println("Catégories disponibles:");

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            int taskCount = todoList.getTasksByCategory(category).size();
            System.out.println((i + 1) + ". " + category.getName() + " (" + taskCount + " tâches)");
        }

        System.out.print("Choisissez une catégorie à supprimer (numéro): ");
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
        int taskCount = todoList.getTasksByCategory(selectedCategory).size();

        if (taskCount > 0) {
            System.out.println("ATTENTION: Cette catégorie contient " + taskCount + " tâche(s).");
            System.out.println("La suppression entraînera la suppression de toutes ces tâches.");
        }

        System.out.print("Êtes-vous sûr de vouloir supprimer cette catégorie? (O/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("O")) {
            if (todoList.removeCategory(selectedCategory)) {
                System.out.println("Catégorie supprimée avec succès!");
            } else {
                System.out.println("Erreur lors de la suppression de la catégorie.");
            }
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    /**
     * Sauvegarde la liste dans un fichier
     */
    private void saveToFile() {
        System.out.println("\n===== SAUVEGARDER LA LISTE =====");
        System.out.print("Nom du fichier: ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            filename = "todolist.dat"; // Nom par défaut
        }

        if (!filename.endsWith(".dat")) {
            filename += ".dat";
        }

        try {
            FileManager.saveTodoList(todoList, filename);
            System.out.println("Liste sauvegardée avec succès dans '" + filename + "'!");
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    /**
     * Charge une liste depuis un fichier
     */
    private void loadFromFile() {
        System.out.println("\n===== CHARGER UNE LISTE =====");
        System.out.print("Nom du fichier: ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            filename = "todolist.dat"; // Nom par défaut
        }

        if (!filename.endsWith(".dat")) {
            filename += ".dat";
        }

        try {
            TodoList loadedList = FileManager.loadTodoList(filename);
            if (loadedList != null) {
                this.todoList = loadedList;
                System.out.println("Liste chargée avec succès depuis '" + filename + "'!");
            } else {
                System.out.println("Erreur: Le fichier ne contient pas de liste valide.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement: " + e.getMessage());
        }
    }
}
