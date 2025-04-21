package com.collegetutorial.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.c10web.controller.dao.StudentDAO;
import com.c10web.model.Student;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Pages/login.jsp?error=empty_fields");
            return;
        }

        try {
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getStudentByUsername(username);

            if (student != null && student.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", username);
                response.sendRedirect(request.getContextPath() + "/Pages/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/Pages/login.jsp?error=invalid_credentials");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/Pages/login.jsp?error=database_error");
        }
    }
}
