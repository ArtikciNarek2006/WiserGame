package main.java.com.table;

import java.sql.ResultSet;
import java.sql.Statement;

public class CategoryRated{

    protected static final String TABLE_NAME = "category";
    public int id;
    public String name;


    public int score; // new
    public CategoryRated(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }


    // copy from Category and change type Category to CategoryRated;
    public static CategoryRated[] getAllFromDB(Statement stmt) {
        CategoryRated[] categories = new CategoryRated[0];
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT COUNT(`id`) AS `COUNT` FROM `" + TABLE_NAME + "`");
            rs.next();
            int length = Integer.parseInt(rs.getObject("COUNT").toString());
            categories = new CategoryRated[length];

            rs = stmt.executeQuery("SELECT * FROM `" + TABLE_NAME + "`");
            int i = 0;
            while (rs.next()) {
                int id = Integer.parseInt(rs.getObject("id").toString());
                String name = rs.getObject("name").toString();
                categories[i] = new CategoryRated(id, name);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }
}