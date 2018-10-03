import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/Login"})
public class LoginFilter implements Filter {

    private ServletContext context;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        System.out.println(url);
        if(session == null){
            System.out.println("Sin autenticacion");
            this.context.log("Sin autenticacion");
        }else{
            System.out.println("Con autenticacion");
            this.context.log("Con autenticacion");
            chain.doFilter(req, resp);
        }



    }

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("App inicializada");
    }

}
