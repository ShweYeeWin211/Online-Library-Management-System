package servlet;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    User u = new User();
    u.setName(req.getParameter("name"));
    u.setEmail(req.getParameter("email"));
    u.setPassword(req.getParameter("password")); // demo only
    boolean ok = UserDAO.register(u);
    if (ok) resp.sendRedirect("login.jsp?msg=registered");
    else {
      req.setAttribute("error", "Registration failed. Try another email.");
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
  }
}