package Database;

import com.example.proiect.StatsFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.*;

public class StatsDB extends CRUD implements DatabaseInterface {
    private String tableName = "evenimente";

    public StatsDB() throws SQLException {
        super();
        super.tableName = tableName;
    }

    public void readFromWebsite() throws IOException, SQLException {

    }
    @Override
    public void deleteUselessRows() throws SQLException {

    }

    public ObservableList<StatsFX> getAllFields() throws ClassNotFoundException, SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rsSet = stmt.executeQuery(
                     "select * from EVENIMENTE")) {
            ObservableList<StatsFX> statsList = getStatsObjects(rsSet);
            return statsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<StatsFX> getStatsObjects(ResultSet rsSet) throws SQLException {
        try {
            ObservableList<StatsFX> statsList = FXCollections.observableArrayList();
            while (rsSet.next()) {
                StatsFX stats = new StatsFX();
                stats.setIdProp(rsSet.getInt("id"));
                stats.setNameProp(rsSet.getString("nume"));
                stats.setAssignedRoomProp(rsSet.getString("sala_asignata"));
                statsList.add(stats);
            }
            return statsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

