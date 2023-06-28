import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Test connection and CRUD operations
            TodoList todoList = new TodoList();

            // Add a new item
            TodoItem newItem = new TodoItem(1, "Buy groceries", "Milk, eggs, bread", "Pending");
            todoList.addTodoItem(newItem);
            System.out.println("Added new item.");

            // Retrieve all items
            List<TodoItem> items = todoList.getAllTodoItems();
            System.out.println("Retrieved all items:");
            for (TodoItem item : items) {
                System.out.println(item.getTitle() + " - " + item.getDescription() + " - " + item.getStatus());
            }

            // Update an item
            TodoItem itemToUpdate = items.get(0);
            itemToUpdate.setStatus("Completed");
            todoList.updateTodoItem(itemToUpdate);
            System.out.println("Updated item: " + itemToUpdate.getTitle());

            // Delete an item
            TodoItem itemToDelete = items.get(1);
            todoList.deleteTodoItem(itemToDelete.getId());
            System.out.println("Deleted item: " + itemToDelete.getTitle());

            // Retrieve all items again
            items = todoList.getAllTodoItems();
            System.out.println("Retrieved all items after update and delete:");
            for (TodoItem item : items) {
                System.out.println(item.getTitle() + " - " + item.getDescription() + " - " + item.getStatus());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
