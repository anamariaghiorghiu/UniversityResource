package Database;

import java.io.IOException;
import java.sql.*;

public class AssignResource extends CRUD{

    private String tableName = "resurse_asignate";
    private String resourceName;
    private int id;

    public AssignResource() throws SQLException {
        super();
        super.tableName = tableName;
        id = super.id;
    }

    public void add(String name, String room, String eventName) throws SQLException {
        this.resourceName = name;
        if(!getResourceNumber(resourceName).equals(0)) {
            //System.out.println(getResourceNumber());
            id = getMaxId() + 1;
           // System.out.println("id: " + id + "\nnume: " + name + "\nroom: " + room + "\nevent: " + eventName);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into resurse_asignate (id, nume, nume_sala, nume_eveniment) values (?, ?, ?, ?)")) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, room);
                pstmt.setString(4, eventName);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try (PreparedStatement pstmt = con.prepareStatement(
                    "update resurse_facultate set nr_bucati = nr_bucati - 1 where nume_resurse = (?)")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
                //System.out.println(name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            //System.out.println("Nu mai exista vreun " + name + " disponibil.");
        }
    }

    @Override
    public void deleteRow(String name) throws SQLException {
        super.deleteRow(name);
        int counter = countRows();

        try (PreparedStatement pstmt = con.prepareStatement(
                "update resurse_facultate set nr_bucati = nr_bucati + " + counter + " where nume_resurse = (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
           // System.out.println(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countRows() throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select count(*) from resurse_asignate group by nume")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    @Override
    public void readFromWebsite() throws IOException, SQLException { }

    @Override
    public void deleteUselessRows() throws SQLException { }

    public Integer getResourceNumber(String resource){
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select nr_bucati from resurse_facultate where nume_resurse = '" + resource + "'")) {
            while(rs.next()){
                //System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select nr_bucati from resurse_facultate where nume_resurse = '" + resource + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}