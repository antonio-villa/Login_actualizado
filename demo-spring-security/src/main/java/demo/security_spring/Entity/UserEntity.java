package demo.security_spring.Entity;
//provar poniendo las entity a la clase

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
/*
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor*/
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns =
    @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"))
    private List<RoleEntity> Roles=new ArrayList<>();
//estos son los getters y setters creados a mano
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoleEntity> getRoles() {
        return Roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        Roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//el contructor con argumentos
    public UserEntity(String username, Long id, String password, List<RoleEntity> roles) {
        this.username = username;
        this.id = id;
        this.password = password;
        Roles = roles;
    }
//el contructor vacio

    public UserEntity(){}
}
