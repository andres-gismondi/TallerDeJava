
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "LoginFilter",
            urlPatterns = {"/Login"})
public class LoginFilter implements Filter {

    final static Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
    private FilterConfig context = null;

    public void init(FilterConfig config) throws ServletException {
        this.context = config;
        this.context.getServletContext().log("Login Filter started...");
    }


    public void destroy() {
        this.context.getServletContext().log("Login Filter destroyed...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        context.getServletContext().log("ASDASDSAD");
        String name = request.getParameter("name");
        if(!name.equals("andres")){
            this.context.getServletContext().log("Sin Autenticacion");
            response.sendRedirect("Login.jsp");

        }else{
            context.getServletContext().log("Asdasd");
            chain.doFilter(req, resp);
        }

        this.context.getServletContext().log("Login Filter comming back...");
    }



}
