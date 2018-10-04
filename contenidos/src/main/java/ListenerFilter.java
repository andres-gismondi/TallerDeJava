import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ListenerFilter",
            urlPatterns = "/*")
public class ListenerFilter implements Filter {

    private FilterConfig context = null;

    public void destroy() {
        this.context.getServletContext().log("Listener Filter destroyed...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.context = config;
        this.context.getServletContext().log("Listener Filter started...");
    }

}
