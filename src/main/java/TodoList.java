import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/wordoccurrences";
        String username = "root";
        String password = "lockbox$2473";

        return DriverManager.getConnection(url, username, password);
    }

    public void addTodoItem(TodoItem item) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO todo_items (title, description, status) VALUES (?, ?, ?)");
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getStatus());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateTodoItem(TodoItem item) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(
                    "UPDATE todo_items SET title = ?, description = ?, status = ? WHERE id = ?");
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getStatus());
            statement.setInt(4, item.getId());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteTodoItem(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM todo_items WHERE id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<TodoItem> getAllTodoItems() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<TodoItem> todoItems = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM todo_items");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");

                TodoItem item = new TodoItem(id, title, description, status);
                todoItems.add(item);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return todoItems;
    }
}
