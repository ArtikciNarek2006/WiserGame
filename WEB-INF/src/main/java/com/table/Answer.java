package main.java.com.table;

import java.sql.ResultSet;
import java.sql.Statement;

public class Answer {
    protected static final String TABLE_NAME = "answer";
    public int id, audio_volume;
    public String text, media_type = null, media_file = null;

    public Answer(int id, String text, String media_type, String media_file, int audio_volume){
        this.id = id;
        this.text = text;
        this.media_type = media_type;
        this.media_file = media_file;
        this.audio_volume = audio_volume;
    }

    public static Answer[] getAllFromDB(Statement stmt, int answer_id) {
        Answer[] answers = new Answer[0];
        ResultSet rs;
        try {
            if(answer_id < 0)
                rs = stmt.executeQuery("SELECT COUNT(`id`) AS `COUNT` FROM `" + TABLE_NAME + "`");
            else
                rs = stmt.executeQuery("SELECT COUNT(`id`) AS `COUNT` FROM `" + TABLE_NAME + "` WHERE `id` = " + answer_id);
            rs.next();
            int length = Integer.parseInt(rs.getObject("COUNT").toString());
            answers = new Answer[length];

            rs.close();
            rs = stmt.executeQuery((answer_id < 0) ? ("SELECT * FROM `" + TABLE_NAME + "`") : ("SELECT * FROM `" + TABLE_NAME + "` WHERE `id` = " + answer_id));

            int i = 0;
            while (rs.next()) {
                int id = Integer.parseInt(rs.getObject("id").toString());
                String text = rs.getObject("text").toString();

                Object media_type_obj = rs.getObject("media_type");
                String media_type = rs.wasNull() ? null : media_type_obj.toString();

                Object media_file_obj = rs.getObject("media_file");
                String media_file = rs.wasNull() ? null : media_file_obj.toString();

                int audio_volume =  Integer.parseInt(rs.getObject("audio_volume").toString());

                answers[i] = new Answer(id, text, media_type, media_file, audio_volume);
                i++;
            }

            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return answers;
    }

    public static Answer[] getAllFromDB(Statement stmt){
        return getAllFromDB(stmt, -1);
    }
}
