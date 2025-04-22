package main.java.com.table;

import java.sql.ResultSet;
import java.sql.Statement;

public class Question {
    protected static final String TABLE_NAME = "question";
    public int id, category_id, media_volume;
    public Integer answer_id = null;
    public String text, media_type = null, media_file = null;

    public Question(int id, String text, String media_type, String media_file, int media_volume, int category_id, Integer answer_id){
        this.id = id;
        this.text = text;
        this.media_type = media_type;
        this.media_file = media_file;
        this.media_volume = media_volume;
        this.category_id = category_id;
        this.answer_id = answer_id;
    }

    public static Question[] getAllFromDB(Statement stmt, int category_id) {
        Question[] questions = new Question[0];
        ResultSet rs;
        try {
            if(category_id < 0)
                rs = stmt.executeQuery("SELECT COUNT(`id`) AS `COUNT` FROM `" + TABLE_NAME + "`");
            else
                rs = stmt.executeQuery("SELECT COUNT(`id`) AS `COUNT` FROM `" + TABLE_NAME + "` WHERE `category_id` = " + category_id);
            rs.next();
            int length = Integer.parseInt(rs.getObject("COUNT").toString());
            questions = new Question[length];

            rs.close();
            rs = stmt.executeQuery((category_id < 0) ? ("SELECT * FROM `" + TABLE_NAME + "`") :  ("SELECT * FROM `" + TABLE_NAME + "` WHERE `category_id` = " + category_id));
            
            int i = 0;
            while (rs.next()) {
                int id = Integer.parseInt(rs.getObject("id").toString());
                String text = rs.getObject("text").toString();


                Object media_type_obj = rs.getObject("media_type");
                String media_type = rs.wasNull() ? null : media_type_obj.toString();

                Object media_file_obj = rs.getObject("media_file");
                String media_file = rs.wasNull() ? null : media_file_obj.toString();

                int media_vol = Integer.parseInt(rs.getObject("media_volume").toString());

                int question_category_id = Integer.parseInt(rs.getObject("category_id").toString());

                Object answer_id_obj = rs.getObject("answer_id");
                Integer answer_id = rs.wasNull() ? null : Integer.parseInt(answer_id_obj.toString());


                questions[i] = new Question(id, text, media_type, media_file, media_vol, question_category_id, answer_id);
                i++;
            }

            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return questions;
    }

    public static Question[] getAllFromDB(Statement stmt){
        return getAllFromDB(stmt, -1);
    }
}
