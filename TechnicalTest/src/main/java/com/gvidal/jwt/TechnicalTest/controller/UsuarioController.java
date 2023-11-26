package com.gvidal.jwt.TechnicalTest.controller;

import com.gvidal.jwt.TechnicalTest.dto.UsuarioDto;
import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import com.gvidal.jwt.TechnicalTest.entity.Usuario;
import com.gvidal.jwt.TechnicalTest.service.TelefonoService;
import com.gvidal.jwt.TechnicalTest.service.UsuarioService;
import com.gvidal.jwt.TechnicalTest.utils.Mensaje;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*",
        methods= {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE
        })
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TelefonoService telefonoService;

    Log Log = LogFactory.getLog("UsuarioController");
    @GetMapping("/lista/{id}") // probado con dto
    public ResponseEntity<?> getUsuario(@PathVariable int id){
        try {
            Optional<Usuario> usr = usuarioService.listId(id);
            List<Telefono> fonos = telefonoService.listByUsuario(id);

            UsuarioDto userDto = new UsuarioDto(
                    usr.get().getId(),
                    usr.get().getNombre(),
                    usr.get().getEmail(),
                    usr.get().getPassword(),
                    usr.get().getFechaCreacion(),
                    usr.get().getFechaLogin(),
                    usr.get().getToken(),
                    fonos
            );

            if(!usuarioService.listId(id).isPresent()){
                return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
            }else {
                return ResponseEntity.ok().body(userDto);
            }
        }catch (Exception e) {
            Log.info("No existe el registro: "  + id);
            return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lista") // probado con dto
    public ResponseEntity<?> getTodos() {

        List<Usuario> usrs = new ArrayList<>();
        try {
            if(!usuarioService.list().isEmpty()){
                List<Usuario> usersList = usuarioService.list();
                List<Telefono> fonos = new ArrayList<>();
                List<UsuarioDto> listaUsrDto = new ArrayList<>();
                // itero para mapear
                for(Usuario usr : usersList) {
                    UsuarioDto tempDto = new UsuarioDto();
                    // llamo al servicio que trae los telefonos por id
                    fonos = telefonoService.listByUsuario(usr.getId());
                    // mapeo a mano
                    tempDto.setId(usr.getId());
                    tempDto.setNombre(usr.getNombre());
                    tempDto.setCorreo(usr.getEmail());
                    tempDto.setPassword(usr.getPassword());
                    tempDto.setFechaCreacion(usr.getFechaCreacion());
                    tempDto.setFechaLogin(usr.getFechaLogin());
                    tempDto.setToken(usr.getToken());
                    tempDto.setFonos(fonos);
                    listaUsrDto.add(tempDto);
                }
                return new ResponseEntity<List<UsuarioDto>>(listaUsrDto, HttpStatus.OK);

            }else {
                return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            Log.info("Error al consultar datos ... " + e.getMessage());
            return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}") // probado
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id) {
        Mensaje msg = new Mensaje();
        try{
            usuarioService.delete(id);
            Log.info("Se elimino el usuario id : " + id);
            msg.mensaje = "Registro eliminado con exito!!";

            return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
        } catch(Exception e) {
            Log.info("No es posible eliminar el registro : " + id + " error: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}") // probado
    public ResponseEntity<?> updateUsuario(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Mensaje msg = new Mensaje();
        try {
            Usuario usr = new Usuario();
            Log.info("Se ha CREADO un objeto del tipo Diio");
            usr.setId(id);
            usr.setNombre(usuario.getNombre());
            usr.setEmail(usuario.getEmail());
            usr.setPassword(usuario.getPassword());
            usr.setFechaCreacion(usuario.getFechaCreacion());
            usr.setFechaLogin(usuario.getFechaLogin());
            usr.setToken(usuario.getToken());
            usuarioService.save(usr);
            Log.info("Se ha actualizado el registro id : " + id);
            msg.mensaje = "Actualizacion de datos con exito!!";
            return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
        } catch (Exception e) {
            Log.info("No es posible actualizar el registro : " + id + " error: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addUsuario") // probado
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario){
        Mensaje msg = new Mensaje();
        Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Pattern PASS = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$", Pattern.CASE_INSENSITIVE);
        if(PASS.matcher(usuario.getPassword()).matches()) {
            if (EMAIL.matcher(usuario.getEmail()).matches()) {
                try {
                    Usuario emailRepository = usuarioService.findByEmail(usuario.getEmail());
                    if(emailRepository==null) {
                        usuarioService.save(usuario);
                        Log.info("Se ingresa registro: " + usuario.getId());
                        msg.mensaje = "Se ingresa registro: " + usuario.getId();
                        return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
                    }else{

                    if(usuario.getEmail().equals(emailRepository.getEmail())) {
                        msg.mensaje = "El email ya existe en la base de datos";
                        return new ResponseEntity<Mensaje>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
                        }else{
                        usuarioService.save(usuario);
                        Log.info("Se ingresa registro: " + usuario.getId());
                        msg.mensaje = "Se ingresa registro: " + usuario.getId();
                        return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
                    }
                    }
                } catch (Exception e) {
                    Log.info("No es posible ingresar el registro: " + usuario.getId() + " error: " + e.getMessage());
                    msg.mensaje = "No es posible ingresar el registro: " + usuario.getId();
                    return new ResponseEntity<Mensaje>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                msg.mensaje = "El email no posee el formato adecuado";
                return new ResponseEntity<Mensaje>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            msg.mensaje = "El PASSWORD debe ser de un largo de 8 caracteres y debe contener letras y numeros.";
            return new ResponseEntity<Mensaje>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
