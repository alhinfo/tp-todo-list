package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe représentant une catégorie de tâches
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String color;

    /**
     * Constructeur pour créer une nouvelle catégorie
     *
     * @param name Le nom de la catégorie
     * @param color Le code couleur associé à la catégorie (pour affichage)
     */
    public Category(String name, String color) {
        this.id = generateId();
        this.name = name;
        this.color = color;
    }

    /**
     * Génère un identifiant unique pour la catégorie
     *
     * @return Un identifiant unique sous forme de chaîne
     */
    private String generateId() {
        return "CAT-" + System.currentTimeMillis();
    }

    // Getters et Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}