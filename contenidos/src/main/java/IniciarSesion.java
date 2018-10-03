
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "java.IniciarSesion",urlPatterns = {"/Login"})
public class IniciarSesion extends HttpServlet {

    private User user;

    public void init(ServletConfig config){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));

        List<User> users = (List<User>) request.getServletContext().getAttribute("users");

        /*response.setContentType("text/html");
        PrintWriter out = response.getWriter();*/

        User camila = new User();
        camila.setName("camila");
        camila.setPassword("3333");

        users.add(camila);
        request.getServletContext().setAttribute("users",users);

        HttpSession s = request.getSession();
        camila.setId(s.getId());
        s.setAttribute("usuarios",request.getServletContext().getAttribute("users"));
        s.setAttribute("camila",camila);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Show.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
