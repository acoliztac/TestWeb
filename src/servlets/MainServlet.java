package servlets;

import main.Main;

import java.io.IOException;
import java.sql.SQLException;


@javax.servlet.annotation.WebServlet(name = "MainServlet", urlPatterns = "/employees")
public class MainServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            request.setAttribute("employees", Main.getEmployees());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/employees.jsp").forward(request, response);
    }
}
