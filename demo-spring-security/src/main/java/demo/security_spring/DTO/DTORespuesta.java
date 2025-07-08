package demo.security_spring.DTO;

import java.util.Objects;

//@Data
public class DTORespuesta {

    private String token;
    private String token_type="Bearer ";

    public DTORespuesta(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTORespuesta that = (DTORespuesta) o;
        return Objects.equals(token, that.token) && Objects.equals(token_type, that.token_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, token_type);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
