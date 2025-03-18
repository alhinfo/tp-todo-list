package service;

import model.TodoList;

import java.io.*;

public class FileManager {
    private static final String DEFAULT_SAVE_PATH = "todolist.ser";

    public static void saveTodoList(TodoList todoList, String filename)
            throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            out.writeObject(todoList);
            System.out.println("Liste de tâches sauvegardée avec succès dans " + filename);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
            throw e;
        }
    }

    public static TodoList loadTodoList(String filename)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(filename))) {
            TodoList todoList = (TodoList) in.readObject();
            System.out.println("Liste de tâches chargée avec succès depuis " + filename);
            return todoList;
        } catch (FileNotFoundException e) {
            System.out.println("Aucune sauvegarde trouvée. Création d'une nouvelle liste.");
            return new TodoList();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement: " + e.getMessage());
            throw e;
        }
    }

    // Méthodes avec chemins par défaut
    public static void saveTodoList(TodoList todoList) throws IOException {
        saveTodoList(todoList, DEFAULT_SAVE_PATH);
    }

    public static TodoList loadTodoList() throws IOException, ClassNotFoundException {
        return loadTodoList(DEFAULT_SAVE_PATH);
    }

    // Méthode utilitaire pour vérifier si une sauvegarde existe
    public static boolean saveFileExists(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile();
    }

    public static boolean saveFileExists() {
        return saveFileExists(DEFAULT_SAVE_PATH);
    }
}