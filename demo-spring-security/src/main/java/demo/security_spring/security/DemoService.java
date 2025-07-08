package demo.security_spring.security;

import demo.security_spring.Entity.RoleEntity;
import demo.security_spring.Entity.UserEntity;
import demo.security_spring.Repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService implements UserDetailsService {

    @Autowired
    private RepositoryUser repositoryUser;

    public Collection<GrantedAuthority> mapToAutorites(List<RoleEntity>roleEntities){
        return roleEntities.stream().map(role ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repositoryUser.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("usuaro no encontrado"));
        return new User(userEntity.getUsername(),userEntity.getPassword(),mapToAutorites(userEntity.getRoles()));
    }
}
