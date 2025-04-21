package com.collegetutorial.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.c10web.controller.dao.StudentDAO;
import com.c10web.model.Student;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		    String firstname = request.getParameter("firstname");
	        String lastname = request.getParameter("lastname");
	        String username = request.getParameter("username");
	        if (!firstname.matches("^[a-zA-Z]+$") || !lastname.matches("^[a-zA-Z]+$")) {
	            response.sendRedirect(request.getContextPath() + "/Pages/register.jsp?error=name_format");
	        }

	        if (username == null || username.trim().isEmpty() || username.trim().length() < 7) {
	            response.sendRedirect(request.getContextPath() + "/Pages/register.jsp?error=name");  
	        }
	        String birthday = request.getParameter("birthday");
	        if (birthday != null && !birthday.isEmpty()) {
	            LocalDate today = LocalDate.now();
	            LocalDate birthDate = LocalDate.parse(birthday);

	            if (birthDate.isAfter(today)) {
	                response.sendRedirect(request.getContextPath() + "/Pages/register.jsp?error=birthday_invalid");
	      
	            }
	        }

	        String gender = request.getParameter("gender");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        if (!phone.startsWith("+")) {
	            phone = "+" + phone;
	        }
	        if (phone.length() != 14 || !phone.substring(1).matches("[0-9]+")) {
	            response.sendRedirect(request.getContextPath() + "/Pages/register.jsp?error=phone_format");
	           
	        }

	        String subject = request.getParameter("subject");
	        String password = request.getParameter("password");
	        String confirmPassword = request.getParameter("confirm_password");
	        if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{7,}$")) {
	            response.sendRedirect(request.getContextPath() + "/Pages/register.jsp?error=password_weak");
	        }

	        // Confirm password check
	        if (!password.equals(confirmPassword)) {
	            response.sendRedirect(request.getContextPath() + "/Pages/register.jsp?error=password_mismatch");
	            
	        }
	        Student student = new Student(username, password);
	        boolean isStudentAdded = false;
	       
	        try {
	        StudentDAO studentDAO= new StudentDAO();
	        isStudentAdded = studentDAO.addStudent(student);
	        if(isStudentAdded == true) {
	        	out.println("Student added successfully");
	        }else {
	        	out.println("Failed to add student");
	        }
	        }

	        out.println("<html><body>");
	        out.println("<h2>Registration Details</h2>");
	        out.println("<p><b>First Name:</b> " + firstname + "</p>");
	        out.println("<p><b>Last Name:</b> " + lastname + "</p>");
	        out.println("<p><b>Username:</b> " + username + "</p>");
	        out.println("<p><b>Birthday:</b> " + birthday + "</p>");
	        out.println("<p><b>Gender:</b> " + gender + "</p>");
	        out.println("<p><b>Email:</b> " + email + "</p>");
	        out.println("<p><b>Phone Number:</b> " + phone + "</p>");
	        out.println("<p><b>Subject:</b> " + subject + "</p>");
	        out.println("<p><b>Password:</b> " + password + "</p>");
	        out.println("<p><b>Confirm Password:</b> " + confirmPassword + "</p>");
	        out.println("</body></html>");
		
	

}
}
