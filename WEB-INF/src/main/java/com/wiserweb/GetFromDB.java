package main.java.com.wiserweb;

import main.java.com.table.Answer;
import main.java.com.table.Category;
import main.java.com.table.Question;
import main.java.com.util.JSON;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;

/*
* /getfromdb?tablename=category                     - return All Categories
* /getfromdb?tablename=question                     - return All Questions
* /getfromdb?tablename=question&category_id=4       - return All Questions with category_id = 4;
* /getfromdb?tablename=answer                       - return All Answers
* /getfromdb?tablename=answer&answer_id=1           - return All Answers with answer_id = 1
* */

@WebServlet("/getfromdb")
public class GetFromDB extends HttpServlet {
    Connection con;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            con = DatabaseConnection.initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        String tableName = req.getParameter("tablename");
        if (Objects.equals(tableName, "category")) {
            getCategory(req, resp, pw);
        } else if (Objects.equals(tableName, "question")) {
            getQuestion(req, resp, pw);
        } else if (Objects.equals(tableName, "answer")) {
            getAnswer(req, resp, pw);
        }
    }

    protected void getCategory(HttpServletRequest req, HttpServletResponse resp, PrintWriter pw) throws ServletException, IOException {
        try {
            Statement stmt = con.createStatement();
            Category[] categories = Category.getAllFromDB(stmt);
            pw.print(JSON.stringifyArray(categories));
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void getQuestion(HttpServletRequest req, HttpServletResponse resp, PrintWriter pw) throws ServletException, IOException {
        try {
            Statement stmt = con.createStatement();
            Question[] questions;
            String category_id = req.getParameter("category_id");
            if (category_id == null || category_id.isEmpty())
                questions = Question.getAllFromDB(stmt);
            else
                questions = Question.getAllFromDB(stmt, Integer.parseInt(category_id));
            pw.print(JSON.stringifyArray(questions));
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getAnswer(HttpServletRequest req, HttpServletResponse resp, PrintWriter pw) throws ServletException, IOException {
        try {
            Statement stmt = con.createStatement();
            Answer[] answers;
            String answer_id = req.getParameter("answer_id");
            if (answer_id == null || answer_id.isEmpty())
                answers = Answer.getAllFromDB(stmt);
            else
                answers = Answer.getAllFromDB(stmt, Integer.parseInt(answer_id));
            pw.print(JSON.stringifyArray(answers));
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void destroy() {
        super.destroy();
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
