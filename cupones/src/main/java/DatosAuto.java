import model.Cotizacion;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "java.DatosAuto",urlPatterns = {"/DatosAuto"})
public class DatosAuto extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setMarca(request.getParameter("marca"));
        cotizacion.setModelo(request.getParameter("modelo"));
        cotizacion.setAñoVehiculo(request.getParameter("anioVehiculo"));
        cotizacion.setUsoEnKilometros(request.getParameter("usoKilometro"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h6>"+cotizacion.getMarca()+"</h6>");
        out.println("<h6>"+cotizacion.getModelo()+"</h6>");
        out.println("<h6>"+cotizacion.getAñoVehiculo()+"</h6>");
        out.println("<h6>"+cotizacion.getUsoEnKilometros()+"</h6>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
