package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //suponemos que el usuario visita por primera vez nuestro sitio
        boolean nuevoUsuario = true;

        //Obtenemos el array de cookies
        Cookie[] cookies = request.getCookies();

        //buscamos su ya existe una cookie creada con anterioridad
        //llamada visitanteRecurrente
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")) {
                    //si ya existe la cookie significa que es un usuario recurrente
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        String message = null;
        if (nuevoUsuario) {
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");//nombre, valor
            response.addCookie(visitanteCookie);
            message = "Gracias por visitar nuestro sitio por primera vez";
        } else {
            message = "Gracias por visitar nuevamente nuestro sitio";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Mensaje: " + message);
        out.close();
    }

}
