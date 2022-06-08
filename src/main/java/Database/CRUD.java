package Database;

import java.sql.*;

public abstract class CRUD implements DatabaseInterface {
    static Connection con;
    String tableName;
    int id;

    public CRUD() throws SQLException {
        con = DatabaseConnection.getConnection();
    }
    public void readData(String... columnName) throws SQLException {
        for(int i = 0; i < columnName.length; i++) {
            if (columnName[i].equals("all")) {
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(
                             "select * from " + tableName)) {
                    while (rs.next()) {
                        for(int j = 1; j <= 3; j++){
                            //System.out.print(rs.getString(j) + " | ");
                        }
                       // System.out.print("\n");
                    }
                }
            } else {
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(
                             "select " + columnName[i] + " from " + tableName)) {
                    while (rs.next()) {
                        //System.out.println(rs.getString(1));
                    }
                }
            }
        }
    }

    public void add(String name) throws SQLException {
        id = getMaxId();
        if(findByName(name) == 0){
            id += 1;
//            System.out.println("id: " + id + "\nname: " + name);
//            System.out.println(name + " doesn't exist in the database.\n");
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into " + tableName + " (id, nume) values (?, ?)")) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        } else {
//            System.out.println("id: " + findByName(name) + "\nname: " + name);
//            System.out.println(name + " already exists in the database.\n");
        }
    }

    public void update(String columnName, String initialValue, String newValue) throws SQLException {
        if(findByName(initialValue) != 0){
            try (PreparedStatement pstmt = con.prepareStatement(
                    "update " + tableName + " set " + columnName + " = (?) where " + columnName + " = (?)")) {
                pstmt.setString(1, newValue);
                pstmt.setString(2, initialValue);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

        if(initialValue.equals("null")){
            try (PreparedStatement pstmt = con.prepareStatement(
                    "update " + tableName + " set " + columnName + " = (?) where " + columnName + " = (?)")) {
                pstmt.setString(1, newValue);
                pstmt.setString(2, initialValue);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    public void deleteRow(String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from " + tableName+" where nume = (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from " + tableName + " where 1 = 1")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findById(int id) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select nume from " + tableName + " where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public int findByName(String name) {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from " + tableName + " where nume='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        } catch (SQLException e1) {
            return 0;
        } catch (NullPointerException e2){
            return 0;
        }
    }

    public int countRows() throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select count(*) from " + tableName)) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public int countRooms() throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select count(*) from sali")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public int getMaxId(){
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select max(id) from " + tableName)) {
            return rs.next() ? rs.getInt(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}