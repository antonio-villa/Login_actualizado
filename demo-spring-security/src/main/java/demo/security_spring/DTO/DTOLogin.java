package demo.security_spring.DTO;

import java.util.Objects;

//@ToString @EqualsAndHashCode @Getter  @Setter  @RequiredArgsConstructor
public class DTOLogin {

    private String username;
    private String password;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOLogin dtoLogin = (DTOLogin) o;
        return Objects.equals(username, dtoLogin.username) && Objects.equals(password, dtoLogin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

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

    public DTOLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
