package com.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String passKey = request.getParameter("passKey");
        String action = request.getParameter("action");
        String delete = request.getParameter("delete");
        UsersDAO usersDAORef = new UsersDAO();

        if ("Login".equals(action)) {
            // Check if fields are empty
            if (username == null || username.trim().isEmpty() || passKey == null || passKey.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?error=empty");
                return;
            }

            // Verify user exists
            Users user = usersDAORef.loginUser(username, passKey);
            if (user != null) {
                // Set session attribute for logged-in user
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?error=invalid");
            }

        } else if ("Register".equals(action)) {
            // Check if fields are empty
            if (username == null || username.trim().isEmpty() || passKey == null || passKey.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?error=empty");
                return;
            }

            // Check if username already exists
            if (usersDAORef.verifyUser(username)) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp?error=exists");
                return;
            }

            // Insert new user
            usersDAORef.registerUser(username, passKey);

            // Auto-login the new user by setting session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to index.jsp after successful registration
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

        if ("delete".equals(delete)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String usernameInSession = (String) session.getAttribute("username");
                if (usernameInSession != null) {
                    // Delete user from database
                    usersDAORef.deleteUser(usernameInSession);

                    // Log out user
                    Logout.logoutUser(request);

                    // Redirect to login page
                    response.sendRedirect(request.getContextPath() + "/Login.jsp");
                    return;
                }
            }
            // If session or username is null, redirect to login
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        }
    }
}
