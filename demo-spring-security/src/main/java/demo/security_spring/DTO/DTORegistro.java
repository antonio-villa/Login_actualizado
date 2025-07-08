package demo.security_spring.DTO;

//@Data
public class DTORegistro {

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DTORegistro(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
