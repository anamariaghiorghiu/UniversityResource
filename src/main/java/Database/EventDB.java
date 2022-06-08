package Database;
import com.example.proiect.EventFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.*;

public class EventDB extends CRUD implements DatabaseInterface {
    private String tableName = "evenimente";
    private int id;

    public EventDB() throws SQLException {
        super();
        id = super.id;
        super.tableName = tableName;
    }

    public void add(String name, int capacity, String teacherName, String subject) throws SQLException {
        id = countRows();
        if(findBySubject(subject) == null){
            id += 1;
            System.out.println("id: " + id + "\neventName: " + name + "\ncapacity: " + capacity + "\nteacher name: " + teacherName + "\nsubject: " + subject);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into evenimente (id, nume, capacitate, nume_profesor, nume_curs) values (?, ?, ?, ?, ?)")) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, capacity);
                pstmt.setString(4, teacherName);
                pstmt.setString(5, subject);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String findBySubject(String subject){
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select nume from evenimente where nume_curs='" + subject + "'")) {
            return rs.next() ? rs.getString(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readFromWebsite() throws IOException, SQLException { }

    @Override
    public void deleteUselessRows() throws SQLException { }


    public ObservableList<EventFX> getAllFields() throws ClassNotFoundException, SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rsSet = stmt.executeQuery(
                     "select * from evenimente")) {
            ObservableList<EventFX> eventsList = getEventsObjects(rsSet);
            return eventsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<EventFX> getEventsObjects(ResultSet rsSet) throws SQLException {
        try {
            ObservableList<EventFX> eventsList = FXCollections.observableArrayList();
            while (rsSet.next()) {
                EventFX event = new EventFX();
                event.setIdProp(rsSet.getInt("id"));
                event.setNameProp(rsSet.getString("nume"));
                event.setCapacityProp(rsSet.getInt("capacitate"));
                event.setNameTeacherProp(rsSet.getString("nume_profesor"));
                event.setNameSubjectProp(rsSet.getString("nume_curs"));
                eventsList.add(event);
            }
            return eventsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}