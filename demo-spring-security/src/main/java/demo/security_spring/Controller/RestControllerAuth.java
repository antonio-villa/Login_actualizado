package demo.security_spring.Controller;

import demo.security_spring.DTO.DTOLogin;
import demo.security_spring.DTO.DTORegistro;
import demo.security_spring.DTO.DTORespuesta;
import demo.security_spring.Entity.RoleEntity;
import demo.security_spring.Entity.UserEntity;
import demo.security_spring.Repository.RepositoryRole;
import demo.security_spring.Repository.RepositoryUser;
import demo.security_spring.security.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class RestControllerAuth {
//crear la base de datos para que funcione
//  @Autowired
  private AuthenticationManager authenticationManager;
//  @Autowired
  private PasswordEncoder passwordEncoder;
//  @Autowired
  private RepositoryUser repositoryUser;
//  @Autowired
  private RepositoryRole repositoryRole;
//  @Autowired
  private JWTToken GenerateToken;

    @Autowired

    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RepositoryUser repositoryUser, RepositoryRole repositoryRole, JWTToken generateToken) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.repositoryUser = repositoryUser;
        this.repositoryRole = repositoryRole;
        GenerateToken = generateToken;
    }


    @PostMapping("/registro")
    public ResponseEntity<String> RegistrarUsuario(@RequestBody DTORegistro dtoRegistro){
//        var variable= dtoRegistro.getPassword() + dtoRegistro.getUsername();
//       return new ResponseEntity<>(""+variable,HttpStatus.OK);
        if (repositoryUser.existsByUsername(dtoRegistro.getUsername())){
            return new ResponseEntity<>("El usuario ya existe MAMON", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(dtoRegistro.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        RoleEntity role = repositoryRole.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        var variable = user.getUsername()+user.getPassword() + user.getRoles();
        //return new ResponseEntity<>(variable+"  ",HttpStatus.OK);
        repositoryUser.save(user);
        return new ResponseEntity<>("Registro exitoso",HttpStatus.OK);
    }


    @PostMapping("/registroADMIN")
    public ResponseEntity<String> RegistrarAdmin(@RequestBody DTORegistro dtoRegistro){
//        var variable= dtoRegistro.getPassword() + dtoRegistro.getUsername();
//       return new ResponseEntity<>(""+variable,HttpStatus.OK);
        if (repositoryUser.existsByUsername(dtoRegistro.getUsername())){
            return new ResponseEntity<>("El usuario ya existe MAMON", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(dtoRegistro.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        RoleEntity role = repositoryRole.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(role));
        var variable = user.getUsername()+user.getPassword() + user.getRoles();
        //return new ResponseEntity<>(variable+"  ",HttpStatus.OK);
        repositoryUser.save(user);
        return new ResponseEntity<>("Registro exitoso",HttpStatus.OK);
    }

     @PostMapping("/login")
    public ResponseEntity<DTORespuesta> LoginUser(@RequestBody DTOLogin dtologin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
              dtologin.getUsername(),dtologin.getPassword()
        ));
        //return new ResponseEntity<>(authentication+"",HttpStatus.OK);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = GenerateToken.GenerateToken(authentication);
        return new ResponseEntity<>(new DTORespuesta(token),HttpStatus.OK);

    }
}
