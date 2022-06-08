package Database;
import com.example.proiect.SubjectFX;
import com.example.proiect.TeacherFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;

public class SubjectDB extends CRUD implements DatabaseInterface {

    private String tableName = "cursuri";
    public SubjectDB() throws SQLException {
        super();
        super.tableName = tableName;
    }

    public void readFromWebsite() throws IOException, SQLException {
        Document doc = Jsoup.connect("https://profs.info.uaic.ro/~orar/orar_discipline.html?fbclid=IwAR1_3iqO85z-o2zw9_YcnYV67blJm1nEOlBTrvObwlUHtfwRJ-tQw6Vesks")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36")
                .get();

        Elements content = doc.select("tbody tr");
        String contentOfSite = "";
        for (Element e : content) {
            contentOfSite = e.select("a").text();
            super.add(contentOfSite);
        }
    }

    @Override
    public void deleteUselessRows() throws SQLException {

    }


    public ObservableList<SubjectFX> getAllFields() throws ClassNotFoundException, SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rsSet = stmt.executeQuery(
                     "select * from cursuri")) {
            ObservableList<SubjectFX> subjectsList = getSubjectObjects(rsSet);
            return subjectsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObservableList<SubjectFX> getSubjectObjects(ResultSet rsSet) throws SQLException {
        try {
            ObservableList<SubjectFX> subjectsList = FXCollections.observableArrayList();
            while (rsSet.next()) {
                SubjectFX subject = new SubjectFX();
                subject.setIdProp(rsSet.getInt("id"));
                subject.setNameProp(rsSet.getString("nume"));
                subject.setTipProp(rsSet.getString("tip"));
                subjectsList.add(subject);
            }
            return subjectsList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}