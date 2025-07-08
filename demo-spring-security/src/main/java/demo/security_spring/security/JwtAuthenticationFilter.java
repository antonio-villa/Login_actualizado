package demo.security_spring.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired private DemoService demoService;
    @Autowired private JWTToken jwtToken;

    public String ObtenerToken(HttpServletRequest request){
        String barerToken=request.getHeader("Authorization");

        if (StringUtils.hasText(barerToken)&& barerToken.startsWith("Bearer ")){
            return barerToken.substring(7,barerToken.length());
        }
        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String DoToken=ObtenerToken(request);

        if (StringUtils.hasText(DoToken)&& jwtToken.validarToken(DoToken)){
            String username=jwtToken.ExtraerUsername(DoToken);
            UserDetails userDetails = demoService.loadUserByUsername(username);
            List<String> demoRole =userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            if (demoRole.contains("USER")|| demoRole.contains("ADMIN")){
                UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
