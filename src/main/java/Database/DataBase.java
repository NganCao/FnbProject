package Database;

import java.sql.*;
import java.util.UUID;

public class DataBase {
    public Connection con;
    public Statement stmt;

    public Statement getStatement() throws ClassNotFoundException, SQLException{
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String connection = "jdbc:sqlserver://192.168.112.13;databaseName=stag-gofnb-db";
            String userName = "stag-login";
            String password = "zSjI@aOU@Fue";
            Class.forName(driver);
            con = DriverManager.getConnection(connection, userName, password);
            stmt = con.createStatement();
            return stmt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public void insertData(String query) throws ClassNotFoundException, SQLException{
        Statement sta = getStatement();
        sta.executeUpdate(query);
    }

    public ResultSet getData(String query) throws ClassNotFoundException, SQLException{
        ResultSet data = getStatement().executeQuery(query);
        return data;
    }

    public void updateData(String query) throws ClassNotFoundException, SQLException{
        getStatement().executeUpdate(query);
    }

    public <T> T getFieldValue(ResultSet resultSet, String columnName, Class<T> dataType) throws SQLException {
        T value = null;
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        if (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String currentColumnName = metaData.getColumnName(i);
                if (columnName.equalsIgnoreCase(currentColumnName)) {
                    if (dataType == UUID.class) {
                        value = dataType.cast(UUID.fromString(resultSet.getString(i)));
                    } else if (dataType == Boolean.class) {
                        value = dataType.cast(resultSet.getBoolean(i));
                    } else {
                        // Add more data type handling as needed
                    }
                    break;  // Assuming column is unique, exit the loop once found
                }
            }
        }

        return value;
    }
}
