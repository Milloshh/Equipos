package com.dh.prueba.test;
import com.dh.prueba.EquipoService;
import com.dh.prueba.model.Equipo;
import com.dh.prueba.repository.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEquipo() {
        Equipo equipo = new Equipo();
        equipo.setNombre("Equipo de prueba");

        when(equipoRepository.save(equipo)).thenReturn(equipo);

        Equipo equipoCreado = equipoService.createEquipo(equipo);

        assertEquals("Equipo de prueba", equipoCreado.getNombre());
        verify(equipoRepository, times(1)).save(equipo);
    }

    @Test
    public void testGetEquipoById() {
        Equipo equipo = new Equipo();
        equipo.setId(1L);
        equipo.setNombre("Equipo de prueba");

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        Equipo equipoEncontrado = equipoService.getEquipoById(1L);

        assertEquals("Equipo de prueba", equipoEncontrado.getNombre());
        verify(equipoRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo());
        equipos.add(new Equipo());

        when(equipoRepository.findAll()).thenReturn(equipos);

        List<Equipo> equiposEncontrados = equipoService.getAllEquipos();

        assertEquals(2, equiposEncontrados.size());
        verify(equipoRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteEquipo() {
        equipoService.deleteEquipo(1L);

        verify(equipoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCalcularDepreciacion() {
        double valorCompra = 200000;
        LocalDate fechaCompra = LocalDate.of(2023, 1, 1);

        double valorEsperado = 192000; // Valor actual con depreciación
        double margenDeError = 0.001; // Margen de error aceptable

        double valorActual = equipoService.calcularDepreciacion(valorCompra, fechaCompra);

        assertEquals(valorEsperado, valorActual, margenDeError);
    }
    }