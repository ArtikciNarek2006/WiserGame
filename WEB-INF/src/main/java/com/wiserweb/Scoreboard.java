package main.java.com.wiserweb;

import main.java.com.table.CategoryRated;
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

/*
* /scoreboard                               - return scoreboard json;
* /scoreboard?category_id=2&new_value=6     - set score of category with id 2 to 6;
* */


@WebServlet("/scoreboard")
public class Scoreboard extends HttpServlet {

    public CategoryRated[] categories;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            Statement stmt = con.createStatement();
            categories = CategoryRated.getAllFromDB(stmt);
            stmt.close();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printScoreboard(HttpServletRequest req, HttpServletResponse resp, PrintWriter pw) {
        pw.print(JSON.stringifyArray(categories));
    }

    public void updateScore(HttpServletRequest req, HttpServletResponse resp, PrintWriter pw) {
        // resp is array [status, log_error];
        String category_id = req.getParameter("category_id");
        String new_score = req.getParameter("new_score");

        if (new_score == null || new_score.isEmpty()) {
            pw.print("[\"error\", \"Invalid new_score\"]");
            System.out.println("Error LOG: SCOREBOARD: doGet: invalid new_score value");
        } else {
            for (CategoryRated category : categories) {
                if (category.id == Integer.parseInt(category_id)) {
                    category.setScore(Integer.parseInt(new_score));
                    break;
                }
            }
            pw.print("[\"success\"]");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String category_id = req.getParameter("category_id");
        PrintWriter pw = resp.getWriter();
        if (category_id == null || category_id.isEmpty())
            printScoreboard(req, resp, pw);
        else
            updateScore(req, resp, pw);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
