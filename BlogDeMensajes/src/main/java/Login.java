import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "java.Login",urlPatterns = {"/Login"})
public class Login extends javax.servlet.http.HttpServlet {

    private User user;
    private List<User> users;

    public void init(ServletConfig config) throws ServletException {
        users = new ArrayList<>();

        User andres = new User();
        andres.setName("Andres");
        andres.setPassword("1111");

        User julian = new User();
        julian.setName("Julian");
        julian.setPassword("2222");

        users.add(andres);
        users.add(julian);

        config.getServletContext().setAttribute("Usuarios",users);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.user = new User();
        this.user.setName(request.getParameter("name"));
        this.user.setPassword(request.getParameter("password"));

        if(this.user.userIsInclusive(users)){
            request.getSession().setAttribute("nombreUsuario",user.getName());
            request.getRequestDispatcher("/Mensajes").forward(request,response);
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h6>No Hay Usuarios</h6>");
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
