package com.dh.prueba.controller;

import com.dh.prueba.EquipoService;
import com.dh.prueba.model.Equipo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipos")
public class equipoController {
    @Autowired
    private EquipoService equipoService;

    @PostMapping
    public ResponseEntity<String> createEquipo(@RequestBody Equipo equipo) {
        // Restringir la modificación del campo id (id no editable)
        equipo.setId(null); // Establecer id como null para evitar que se modifique

        // Calcular el valor actual del equipo
        double valorActual = equipoService.calcularDepreciacion(equipo.getValorCompra(), equipo.getFechaCompra());

        // Actualizar el valor total
        equipo.setTotal(valorActual);

        // Llamar al servicio para crear el equipo con el valor total calculado
        Equipo equipoCreado = equipoService.createEquipo(equipo);

        // Si el equipo se creó correctamente, devuelve una respuesta con el mensaje de registro exitoso
        return ResponseEntity.ok("Registro exitoso!");
    }

    @GetMapping("{all}")
    public List<Equipo> getAllEquipos(){
        return equipoService.getAllEquipos();
    }
    @GetMapping("{id}")
    public Equipo searchEquipoById(@PathVariable("id") Long id){
        return equipoService.getEquipoById(id);
    }

    @DeleteMapping("{id}")
    public void deleteEquipoById(@PathVariable("id") Long id){
        equipoService.deleteEquipo(id);
    }
}
