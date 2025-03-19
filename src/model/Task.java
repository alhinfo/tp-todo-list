package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe représentant une tâche dans l'application To-Do List.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;
    private Category category;
    private LocalDate creationDate;

    /**
     * Constructeur pour créer une nouvelle tâche
     *
     * @param title Le titre de la tâche
     * @param description La description détaillée de la tâche
     * @param dueDate La date d'échéance de la tâche
     * @param category La catégorie à laquelle appartient la tâche
     */
    public Task(String title, String description, LocalDate dueDate, Category category) {
        this.id = generateId();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.completed = false;
        this.creationDate = LocalDate.now();
    }

    /**
     * Génère un identifiant unique pour la tâche basé sur le timestamp
     *
     * @return Un identifiant unique sous forme de chaîne
     */
    private String generateId() {
        return "TASK-" + System.currentTimeMillis();
    }

    /**
     * Marque la tâche comme terminée
     */
    public void markAsCompleted() {
        this.completed = true;
    }

    /**
     * Marque la tâche comme non terminée
     */
    public void markAsIncomplete() {
        this.completed = false;
    }

    /**
     * Vérifie si la tâche est en retard par rapport à la date d'échéance
     *
     * @return true si la tâche est en retard, false sinon
     */
    public boolean isOverdue() {
        return !completed && dueDate != null && dueDate.isBefore(LocalDate.now());
    }

    // Getters et Setters

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dueDateStr = dueDate != null ? dueDate.format(formatter) : "Non définie";
        String status = completed ? "Terminée" : (isOverdue() ? "En retard" : "À faire");

        return String.format("[%s] %s - %s - Échéance: %s - Catégorie: %s - %s",
                id, title, description, dueDateStr, category.getName(), status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}