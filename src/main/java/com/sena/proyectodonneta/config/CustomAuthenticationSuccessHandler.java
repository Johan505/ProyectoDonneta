package com.sena.proyectodonneta.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

//Clase para customizar las rutas exitosas

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

     //Ruta usuario
     SimpleUrlAuthenticationSuccessHandler domiciliarioSuccessHandler =
     new SimpleUrlAuthenticationSuccessHandler("/domiciliario");

    //Ruta usuario
    SimpleUrlAuthenticationSuccessHandler userSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/user");

    // Ruta administrador
    SimpleUrlAuthenticationSuccessHandler adminSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/admin");

    //Metodo que lo verifica
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ROLE_USER")) {
                // if the user NOT is an ADMIN delegate to the USERSuccessHandler
                this.userSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }

            if (authorityName.equals("ROLE_DOMICILIARIO")) {
                // if the user NOT is an ADMIN delegate to the USERSuccessHandler
                this.domiciliarioSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }
        }
        // if the user is an admin delegate to the ADMINSuccessHandler
        this.adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }

    
}
