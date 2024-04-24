package com.group8.diy4rent.Security.Jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Esta clase se encarga de comprobar si el token existe, si no existe, se envia un error 401
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {


    // Metodo implementado de AuthenticationEntryPoint
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // Verificar si la solicitud es para la página de inicio
        String requestURI = request.getRequestURI();
        // if (requestURI.equals("/")) {
        //     // Permitir el acceso a la página de inicio sin token
        //     response.setStatus(HttpServletResponse.SC_OK);
        //     return;
        // }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No esta autorizado");
    }
}
