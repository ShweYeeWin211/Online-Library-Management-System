package servlet;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddBookServlet extends HttpServlet {
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    Book b = new Book();
    b.setTitle(req.getParameter("title"));
    b.setAuthor(req.getParameter("author"));
    b.setCategory(req.getParameter("category"));
    b.setAvailable("on".equals(req.getParameter("available")));
    boolean ok = BookDAO.addBook(b);
    if (ok) resp.sendRedirect("books.jsp?msg=added");
    else resp.sendRedirect("books.jsp?err=add_failed");
  }
}