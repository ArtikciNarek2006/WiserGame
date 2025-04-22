package main.java.com.table;

import java.sql.ResultSet;
import java.sql.Statement;

public class Category {
    protected static final String TABLE_NAME = "category";
    public int id;
    public String name;
    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Category[] getAllFromDB(Statement stmt) {
        Category[] categories = new Category[0];
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT COUNT(`id`) AS `COUNT` FROM `" + TABLE_NAME + "`");
            rs.next();
            int length = Integer.parseInt(rs.getObject("COUNT").toString());
            categories = new Category[length];

            rs = stmt.executeQuery("SELECT * FROM `" + TABLE_NAME + "`");
            int i = 0;
            while (rs.next()) {
                int id = Integer.parseInt(rs.getObject("id").toString());
                String name = rs.getObject("name").toString();
                categories[i] = new Category(id, name);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }
}
