package Database;
import com.example.proiect.ResourceFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.*;

public class ResourceDB extends CRUD implements DatabaseInterface {
    private String tableName = "resurse_facultate";
    private int id;

    public ResourceDB() throws SQLException {
        super();
        super.tableName = tableName;
        id = super.id;
    }

    public void add(String name, int cantity) throws SQLException {
        id = getMaxId();
        if(findByName(name) == 0){
            id += 1;
//            System.out.println("id: " + id + "\nresource: " + name);
//            System.out.println("This resource doesn't exist in the database.\n");
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into resurse_facultate (id, nume_resurse, nr_bucati) values (?, ?, ?)")) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, cantity);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
//            System.out.println("id: " + findByName(name) + "\nresource: " + name + "\ncantity: " + cantity);
//            System.out.println("This resource already exists in the database.\n");
        }
    }

    @Override
    public void readFromWebsite() throws IOException, SQLException { }

    @Override
    public void deleteUselessRows() throws SQLException { }


    public ObservableList<ResourceFX> getAllFields() throws ClassNotFoundException, SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rsSet = stmt.executeQuery(
                     "select * from resurse_asignate")) {
            ObservableList<ResourceFX> resourcesList = getResourcesObjects(rsSet);
            return resourcesList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObservableList<ResourceFX> getResourcesObjects(ResultSet rsSet) throws SQLException {
        try {
            ObservableList<ResourceFX> resourcesList = FXCollections.observableArrayList();
            while (rsSet.next()) {
                ResourceFX resource = new ResourceFX();
                resource.setIdProp(rsSet.getInt("id"));
                resource.setNameProp(rsSet.getString("nume"));
                resource.setNameRoomProp(rsSet.getString("nume_sala"));
                resource.setNameEventProp(rsSet.getString("nume_eveniment"));
                resourcesList.add(resource);
            }
            return resourcesList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}