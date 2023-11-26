package com.gvidal.jwt.TechnicalTest.controller;

import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import com.gvidal.jwt.TechnicalTest.entity.Usuario;
import com.gvidal.jwt.TechnicalTest.service.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",
        methods= {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE
        })
public class TelefonoController {
    @Autowired
    private TelefonoService telefonoService;

    @GetMapping("/fonos/{id}")
    public ResponseEntity<?> getTelefonos(@PathVariable int id){
        try {
            List<Telefono> fonos = telefonoService.listByUsuario(id);

            if(telefonoService.listByUsuario(id).isEmpty()){
                return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
            }else {
                return ResponseEntity.ok().body(fonos);
            }
        }catch (Exception e) {
            return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
        }
    }
}
