
package web;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        boolean nuevoUsuario = true; //usuario nuevo que s eva creando
        // de la peticion que hace el usuario al navegador, cogemos las cookies
        Cookie[] cookies = request.getCookies();
        
        //Comprobamos si ya existe una cookie creada con anterioridad llamada VisitanteRecurrente
        if (cookies != null){
            for (Cookie c: cookies) {
                if(c.getName().equals("visitanteTecurrente") && c.getValue().equals("si")){
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        String mensaje = null;
        
        if(nuevoUsuario){
           Cookie visitanteRecurrente = new Cookie("visitanteRecurrente", "si"); //creo la cookie para el nuevo usuario
           response.addCookie(visitanteRecurrente); //añado la cookie
           mensaje = "Gracias por visitar nuestro sitio web por primera vez";
        }else{
            mensaje = "Gracias por visitarnos de nuevo";
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Mensaje: " + mensaje);
        out.close();
    }
    
}
