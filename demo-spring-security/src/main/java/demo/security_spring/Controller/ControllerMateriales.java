package demo.security_spring.Controller;

import demo.security_spring.Entity.MaterialesEntity;
import demo.security_spring.Repository.RepositoryMateriales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/application")
@RestController
public class ControllerMateriales {

    @Autowired
    private RepositoryMateriales material;

    //
    @PostMapping("/create")
public ResponseEntity<?>createProduct(@RequestBody MaterialesEntity entity){
    return ResponseEntity.ok(material.save(entity));
}
@GetMapping("/find")
public List<MaterialesEntity>getMateriales(){
        return material.findAll();

}

}
