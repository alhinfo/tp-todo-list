package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe principale pour la gestion de la liste des tâches
 */
public class TodoList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Category, List<Task>> tasksByCategory;
    private List<Category> categories;

    /**
     * Constructeur pour créer une nouvelle liste de tâches vide
     */
    public TodoList() {
        this.tasksByCategory = new HashMap<>();
        this.categories = new ArrayList<>();
    }

    /**
     * Ajoute une nouvelle catégorie à la liste
     *
     * @param category La catégorie à ajouter
     * @return true si la catégorie a été ajoutée, false si elle existait déjà
     */
    public boolean addCategory(Category category) {

    }

    /**
     * Supprime une catégorie et toutes ses tâches associées
     *
     * @param category La catégorie à supprimer
     * @return true si la catégorie a été supprimée, false sinon
     */
    public boolean removeCategory(Category category) {

    }

    /**
     * Récupère toutes les catégories disponibles
     *
     * @return La liste des catégories
     */
    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    /**
     * Trouve une catégorie par son nom
     *
     * @param name Le nom de la catégorie à rechercher
     * @return La catégorie trouvée ou null si aucune correspondance
     */
    public Category findCategoryByName(String name) {

    }

    /**
     * Ajoute une tâche à la liste
     *
     * @param task La tâche à ajouter
     * @return true si la tâche a été ajoutée, false sinon
     */
    public boolean addTask(Task task) {

    }

    /**
     * Supprime une tâche de la liste
     *
     * @param task La tâche à supprimer
     * @return true si la tâche a été supprimée, false sinon
     */
    public boolean removeTask(Task task) {

    }

    /**
     * Récupère toutes les tâches d'une catégorie spécifique
     *
     * @param category La catégorie dont on veut récupérer les tâches
     * @return La liste des tâches de cette catégorie
     */
    public List<Task> getTasksByCategory(Category category) {

    }

    /**
     * Récupère toutes les tâches de la liste
     *
     * @return La liste complète des tâches
     */
    public List<Task> getAllTasks() {

    }

    /**
     * Récupère les tâches à faire (non terminées)
     *
     * @return La liste des tâches non terminées
     */
    public List<Task> getPendingTasks() {

    }

    /**
     * Récupère les tâches terminées
     *
     * @return La liste des tâches terminées
     */
    public List<Task> getCompletedTasks() {

    }

    /**
     * Récupère les tâches en retard (échéance dépassée et non terminées)
     *
     * @return La liste des tâches en retard
     */
    public List<Task> getOverdueTasks() {

    }

    /**
     * Récupère les tâches dont l'échéance est proche (moins de X jours)
     *
     * @param days Le nombre de jours considéré comme "proche"
     * @return La liste des tâches avec échéance proche
     */
    public List<Task> getUpcomingTasks(int days) {

    }

    /**
     * Recherche des tâches par mot-clé dans le titre ou la description
     *
     * @param keyword Le mot-clé à rechercher
     * @return La liste des tâches correspondantes
     */
    public List<Task> searchTasks(String keyword) {

    }

    /**
     * Trouve une tâche par son identifiant
     *
     * @param taskId L'identifiant de la tâche à rechercher
     * @return La tâche trouvée ou null si aucune correspondance
     */
    public Task findTaskById(String taskId) {

    }

    /**
     * Compte le nombre total de tâches
     *
     * @return Le nombre de tâches
     */
    public int getTaskCount() {

    }

    /**
     * Calcul le pourcentage de tâches terminées
     *
     * @return Le pourcentage de tâches terminées
     */
    public double getCompletionRate() {

    }
}