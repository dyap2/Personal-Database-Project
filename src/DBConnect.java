
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;

import javax.xml.transform.Result;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


public class DBConnect {

    String host, userName, userPass, query;
    Connection connect;
    Statement stmnt;
    ResultSet results;

    public DBConnect() {

        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("./secrets.json"));

            Map<?, ?> map = gson.fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

            reader.close();

            host = map.get("host").toString();
            userName = map.get("userName").toString();
            userPass = map.get("userPass").toString();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createRow(String text, String text1, String text2) {
        insertCreate(text, text1, text2);
    }

    private void insertCreate(String name, String category, String platform) {
        // make this return something?
        query = ("INSERT INTO applications (name, platform, category)\n" +
                "VALUES ('" + name + "','" + category + "','" + platform + "')");
        try {
            Connection connect = DriverManager.getConnection(host, userName, userPass);
            Statement stmnt = connect.createStatement(); 
            stmnt.executeUpdate(query);

        } catch (SQLException err) {
            System.out.println(err.toString());
        }
    }

    public void delete(String name) {
        privateDelete(name);
    }

    private void privateDelete(String name){
        // get the id first
        ResultSet idNum = getID(name);
        String id = null;
        try {
            idNum.next();
            id = idNum.getString("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String dbQuery = ("DELETE FROM applications\n" +
                "WHERE id=" + id);
        try {
            Connection connect = DriverManager.getConnection(host, userName, userPass);
            Statement stmnt = connect.createStatement();
            stmnt.execute(dbQuery);

        } catch (SQLException err) {
            System.out.println(err.toString());
        }

    }


    class Item {
        String name;
        String category;
        String platform;

        public Item(String name, String category, String platform) {
            this.name = name;
            this.category = category;
            this.platform = platform;
        }
    }


    //retrieving the tables
    public ArrayList<String> retrieveCategories(ArrayList<String> container) {
        ResultSet results = query("SELECT DISTINCT\n" +
                "    category\n" +
                "FROM\n" +
                "    Resources.applications;");


        try {
            while (results.next()) {
                container.add(results.getString("category"));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        return container;
    }

    //showing contents of specific category
    public ArrayList<String> showCategoryItems(String categoryName) {
        ArrayList<String> items = new ArrayList<>();
        ResultSet results = query("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    applications\n" +
                "WHERE\n" +
                "    category = '" + categoryName + "'");

        try {
            while (results.next()) {
                items.add(results.getString("name"));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return items;
    }

    public Item getDetails(String name) {
        Item retrieved;
        ResultSet results = query("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    Resources.applications\n" +
                "WHERE\n" +
                "    name = '" + name + "'");

        try {
            results.next(); 
            retrieved = new Item(results.getString("name"), results.getString("category"), results.getString("platform"));
            return retrieved;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private ResultSet query(String req) {

        try {

            Connection connect = DriverManager.getConnection(host, userName, userPass);
            Statement stmnt = connect.createStatement(); 
            ResultSet results = stmnt.executeQuery(req);


            return results;
        } catch (SQLException err) {
            System.out.println(err.toString());
            return null;
        }
    }

    public void update(String name, String category, String platform) {
        System.out.println(name + category + platform);
        updateQuery(name, category, platform);

    }


    private void updateQuery(String name, String category, String platform) {

        ResultSet idNum = getID(name);
        String id = null;
        try {
            idNum.next();
            id = idNum.getString("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String dbQuery = ("UPDATE applications\n" +
                "SET platform='" + platform + "'," +
                "category='" + category + "'" +
                "WHERE id=" + id);
        try {
            Connection connect = DriverManager.getConnection(host, userName, userPass);
            Statement stmnt = connect.createStatement(); 
            stmnt.execute(dbQuery);

        } catch (SQLException err) {
            System.out.println(err.toString());
        }
    }

    private ResultSet getID(String name) {
        String dbQuery = "select id from applications where name=\"" + name + "\"";
        try {
            Connection connect = DriverManager.getConnection(host, userName, userPass);
            Statement stmnt = connect.createStatement(); 
            return stmnt.executeQuery(dbQuery);
        } catch (SQLException err) {
            System.out.println(err.toString());
            return null;
        }
    }


}
