package com.dh.prueba;

import com.dh.prueba.model.Equipo;
import com.dh.prueba.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    public Equipo createEquipo(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    public Equipo getEquipoById(Long id){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        return optionalEquipo.get();
    }

    public List<Equipo> getAllEquipos(){
        return equipoRepository.findAll();
    }

    public void deleteEquipo(Long id){
        equipoRepository.deleteById(id);
    }

    // Método para calcular la depreciación del equipo
    public double calcularDepreciacion(double valorCompra, LocalDate fechaCompra) {
        double porcentajeDepreciacionAnual = 0.04; // 4%
        int anosTranscurridos = LocalDate.now().getYear() - fechaCompra.getYear();

        // Calcular la depreciación
        double depreciacion = valorCompra * porcentajeDepreciacionAnual * anosTranscurridos;

        // Actualizar el valor del equipo
        double valorActual = valorCompra - depreciacion;

        return valorActual;
    }
}
