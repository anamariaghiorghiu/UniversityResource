package Database;

import com.example.proiect.RoomFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class RoomDB extends CRUD implements DatabaseInterface {
    private String tableName = "sali";

    public RoomDB() throws SQLException {
        super();
        super.tableName = tableName;
    }

    public void readFromWebsite() throws IOException, SQLException {
        Document doc = Jsoup.connect("https://profs.info.uaic.ro/~orar/orar_resurse.html?fbclid=IwAR1_3iqO85z-o2zw9_YcnYV67blJm1nEOlBTrvObwlUHtfwRJ-tQw6Vesks")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36")
                .get();

        Elements content = doc.select("ul li ul li");
        String contentOfSite = "";
        for (Element e : content) {
            contentOfSite = e.select("a").text();
            add(contentOfSite);
//            System.out.println(contentOfSite);
        }
        deleteUselessRows();
    }

    public void deleteUselessRows() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from sali where nume not like '%C%' or nume like '%OL%'")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateRoomCapacity() throws SQLException {
        Random rand = new Random();
        for(int i = 1; i <= getMaxId(); i++){
            try (PreparedStatement pstmt = con.prepareStatement(
                    "update sali set capacitate = (?) where id = (?)")) {
                pstmt.setInt(1,rand.nextInt(200) + 50);
                pstmt.setInt(2, i);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ObservableList<RoomFX> getAllFields() throws ClassNotFoundException, SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rsSet = stmt.executeQuery(
                     "select * from sali")) {
            ObservableList<RoomFX> roomsList = getRoomObjects(rsSet);
            return roomsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<RoomFX> getRoomObjects(ResultSet rsSet) throws SQLException {
        try {
            ObservableList<RoomFX> roomsList = FXCollections.observableArrayList();
            while (rsSet.next()) {
                RoomFX room = new RoomFX();
                room.setIdProp(rsSet.getInt("id"));
                room.setNameProp(rsSet.getString("nume"));
                room.setCapacityProp(rsSet.getInt("capacitate"));
                roomsList.add(room);
            }
            return roomsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderByCapacity() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "CREATE VIEW orderedRooms AS SELECT id, nume, capacitate FROM sali order by capacitate asc")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropView() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "drop view orderedRooms")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

