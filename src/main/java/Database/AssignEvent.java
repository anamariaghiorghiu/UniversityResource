package Database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignEvent extends CRUD {

    public AssignEvent() throws SQLException {
        super();
        String tableName = "evenimente";
        super.tableName = tableName;
        int id = super.id;
    }

    public void assignEvent() throws SQLException {
        List<Integer> roomCapacity = new ArrayList<>();
        List<Integer> eventCapacity = new ArrayList<>();
        List<String> eventName = new ArrayList<>();
        List<String> roomName = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select capacitate, nume from orderedrooms")) {
            while (rs.next()) {
                roomCapacity.add(rs.getInt(1));
                roomName.add(rs.getString(2));
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select capacitate, nume from evenimente")) {
            while (rs.next()) {
                eventCapacity.add(rs.getInt(1));
                eventName.add(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < eventCapacity.size(); i++) {
            for (int j = 0; j < roomCapacity.size(); j++) {
                if (eventCapacity.get(i) <= roomCapacity.get(j)) {
//                    add("ocupata");
//                    update("ocupata", "0", "1", roomName.get(i));
                    updateRoomToEvent(eventName.get(i), roomName.get(j));
                    break;
                }
            }
        }
    }

    public void update(String columnName, String initialValue, String newValue, String roomName) throws SQLException {
        if (findByName(initialValue) != 0) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "update " + tableName + " set " + columnName + " = (?) where " + columnName + " = (?) and nume = (?)")) {
                pstmt.setString(1, newValue);
                pstmt.setString(2, initialValue);
                pstmt.setString(3, roomName);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    private void updateRoomToEvent(String eventName, String roomName) throws SQLException {
        String query = "update evenimente set sala_asignata ='" + roomName + " ' where nume ='" + eventName + "'";
//        System.out.println(query);
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void add(String name) throws SQLException {
        id = getMaxId();
        if (findByName(name) == 0) {
            id += 1;
//
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into " + tableName + " (id, nume) values (?, ?) where ocupata is null")) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

    }

    @Override
    public void readFromWebsite() throws IOException, SQLException {

    }

    @Override
    public void deleteUselessRows() throws SQLException {

    }
}