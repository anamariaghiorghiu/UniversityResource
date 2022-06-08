package Database;

import com.example.proiect.RoomFX;
import com.example.proiect.TeacherFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class TeacherDB extends CRUD{
    private String tableName = "profesori";
    private int id;
    public TeacherDB() throws SQLException {
        super();
        super.tableName = tableName;
        id = super.id;
    }

    public void readFromWebsite() throws IOException, SQLException {
        Document doc = Jsoup.connect("https://profs.info.uaic.ro/~orar/orar_profesori.html?fbclid=IwAR1_3iqO85z-o2zw9_YcnYV67blJm1nEOlBTrvObwlUHtfwRJ-tQw6Vesks")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36")
                .get();

        Elements content = doc.select("tbody tr li");
        String contentOfSite = "";
        for (Element e : content) {
            contentOfSite = e.select("a").text();
            add(contentOfSite, contentOfSite);
           // System.out.println(contentOfSite);
        }
    }

    public void generateSubjectId() throws SQLException {
        Random rand = new Random();
        for(int i = 1; i <= getMaxId(); i++){
            try (PreparedStatement pstmt = con.prepareStatement(
                    "update profesori set id_materie1 = (?), id_materie2 =(?), id_materie3 = (?) where id = (?)")) {
                pstmt.setInt(1,rand.nextInt(getMaxId()));
                pstmt.setInt(2,rand.nextInt(getMaxId()));
                pstmt.setInt(3,rand.nextInt(getMaxId()));
                pstmt.setInt(4, i);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void add(String name, String degree) throws SQLException {
        id = getMaxId();
        if(findByName(name) == 0){
            id += 1;
//            System.out.println("id: " + id + "\nteacher: " + name);
//            System.out.println("This teacher doesn't exist in the database.\n");
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into profesori (id, nume, grad_didactic) values (?, ?, ?)")) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, degree);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
//            System.out.println("id: " + findByName(name) + "\nteacher: " + name + "\ndegree: " + degree);
//            System.out.println("This teacher already exists in the database.\n");
        }
        deleteUselessRows();
        trimDegree();
        trimNames();
    }

    public void deleteUselessRows() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from profesori where nume like '%(%'")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trimNames() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "update profesori set nume = substr(nume, 1, instr(nume, ',') - 1) where nume like '%,%'")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trimDegree() throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "update profesori set grad_didactic = substr(grad_didactic, instr(grad_didactic, ',') + 2, length(grad_didactic)) where grad_didactic like '%,%'")) {
            pstmt.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<TeacherFX> getAllFields() throws ClassNotFoundException, SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rsSet = stmt.executeQuery(
                     "select * from profesori")) {
            ObservableList<TeacherFX> teachersList = getTeachersObjects(rsSet);
            return teachersList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<TeacherFX> getTeachersObjects(ResultSet rsSet) throws SQLException {
        try {
            ObservableList<TeacherFX> teachersList = FXCollections.observableArrayList();
            while (rsSet.next()) {
                TeacherFX teacher = new TeacherFX();
                teacher.setIdProp(rsSet.getInt("id"));
                teacher.setNameProp(rsSet.getString("nume"));
                teacher.setGradDidacticProp(rsSet.getString("grad_didactic"));
                teacher.setIdMaterie1Prop(rsSet.getInt("id_materie1"));
                teacher.setIdMaterie2Prop(rsSet.getInt("id_materie2"));
                teacher.setIdMaterie3Prop(rsSet.getInt("id_materie3"));
                teachersList.add(teacher);
            }
            return teachersList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
