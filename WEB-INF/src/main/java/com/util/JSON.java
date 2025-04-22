package main.java.com.util;

import main.java.com.table.Category;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

// not full working class tested for this project requirements; do not use int other projects;
// tested for ["public int", "public String", "protected static final String"];
public class JSON {
    public static String stringify(Object obj) {
        StringBuilder answer = new StringBuilder("{");
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && field.canAccess(obj)) {
                    answer.append('"').append(field.getName()).append("\":");

                    Object value = field.get(obj);

                    if (field.getType() == String.class)
                        answer.append('"').append(String.valueOf(value)).append("\",");
                    else
                        answer.append(String.valueOf(value)).append(",");
                }
            }
        } catch (Exception ignore) {
            return "{}";
        }

        if (answer.length() > 1)
            return (answer.substring(0, answer.length() - 1)) + "}";
        else
            return "{}";
    }

    public static String stringifyArray(Object[] arr) {
        StringBuilder answer = new StringBuilder("[");
        for (Object o : arr) {
            answer.append(JSON.stringify(o)).append(",");
        }
        if (answer.length() > 1)
            return answer.substring(0, answer.length() - 1) + "]";
        else
            return "[]";
    }

    public static void main(String[] args) throws IllegalAccessException {
        Category cat = new Category(5, "valod");
        Category cat2 = new Category(5, "grno");
        Category[] cats = new Category[2];
        cats[0] = cat;
        cats[1] = cat2;
        System.out.println(JSON.stringify(cat));
        System.out.println(JSON.stringify(cat2));
        System.out.println(JSON.stringifyArray(cats));
    }
}

//import org.json.JSONObject;
//import main.java.com.table.CategoryRated;
//import main.java.com.table.Answer;
//import main.java.com.table.Question;
//public class JSON {
//    public static String stringify(Object obj) {
//        if (obj.getClass() == Answer.class) {
//            return new JSONObject(obj, "id", "text", "media_type", "media_file", "audio_volume").toString();
//        } else if (obj.getClass() == Category.class) {
//            return new JSONObject(obj, "id", "name").toString();
//        } else if (obj.getClass() == CategoryRated.class) {
//            return new JSONObject(obj, "id", "name", "score").toString();
//        } else if (obj.getClass() == Question.class) {
//            return new JSONObject(obj, "id", "text", "media_type", "media_file", "audio_volume", "category_id", "answer_id").toString();
//        }
//        return "{}";
//    }
//
//    public static String stringifyArray(Object[] arr) {
//        StringBuilder answer = new StringBuilder("[");
//        for (Object o : arr) {
//            answer.append(JSON.stringify(o)).append(",\n");
//        }
//        if (answer.length() > 1)
//            return answer.substring(0, answer.length() - 2) + "]";
//        else
//            return "[]";
//    }
//
//    public static void main(String[] args) throws IllegalAccessException {
//        Category cat = new Category(5, "valod");
//        Category cat2 = new Category(5, "grno");
//        Category[] cats = new Category[2];
//        cats[0] = cat;
//        cats[1] = cat2;
//        System.out.println("stringname:" + cat.getClass().toString());
//        System.out.println(JSON.stringify(cat));
//        System.out.println(JSON.stringify(cat2));
//        System.out.println(JSON.stringifyArray(cats));
//    }
//}