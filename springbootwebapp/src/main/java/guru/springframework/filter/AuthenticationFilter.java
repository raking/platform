package guru.springframework.filter;
/*
 * http://appsdeveloperblog.com/user-authentication-spring-boot-spring-security-jwt/
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.domain.LoginRequestModel;
import guru.springframework.domain.UserDto;
import guru.springframework.services.UsersService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UsersService usersService;
    private final String TOKEN_SECRET="h4of9eh48vmg02nfu30v27yen295hfj65";
    public AuthenticationFilter(AuthenticationManager authenticationManager,
            UsersService usersService) {
        this.usersService = usersService;
        super.setAuthenticationManager(authenticationManager);
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
  
            LoginRequestModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginRequestModel.class);
            
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        
        // Get User Details from Database 
        String userName = ((User) auth.getPrincipal()).getUsername();  
        //UserDto userDto = usersService.getUserByEmail(userName);    
        
        // Generate GWT
		/*
		 * String token = Jwts.builder() .setSubject(userDto.getUserId())
		 * .setExpiration(new Date(System.currentTimeMillis() +
		 * Long.parseLong("3600000"))) .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET
		 * ) .compact();
		 * 
		 * res.addHeader("Token", token);
		 */
        //res.addHeader("UserID", userDto.getUserId());
    }  
}