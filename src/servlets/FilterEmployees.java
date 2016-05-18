package servlets;

import main.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FilterEmployees", urlPatterns = "/filter")
public class FilterEmployees extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        if (name.isEmpty() && surname.isEmpty()){
            response.sendRedirect("/employees");
        } else {
            try {
                request.setAttribute("employees", Main.filterEmployees(name, surname));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("WEB-INF/employees.jsp").forward(request, response);
        }
    }
}
