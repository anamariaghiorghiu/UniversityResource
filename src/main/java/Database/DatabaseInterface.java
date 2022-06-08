package Database;

import java.io.IOException;
import java.sql.SQLException;

public interface DatabaseInterface {
    public void readFromWebsite() throws IOException, SQLException;
    public void readData(String ... columnName) throws SQLException;
    public void add(String name) throws SQLException;
    public String findById(int id) throws SQLException;
    public int findByName(String name) throws SQLException;
    public int countRows() throws SQLException;
    public void deleteRow(String name) throws SQLException;
    public void deleteAll() throws SQLException;
    public void deleteUselessRows() throws SQLException;
    public void update(String columnName, String initialValue, String newValue) throws SQLException;
}
