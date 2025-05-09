package com.evaluacion.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.evaluacion.hospital.entity.Cita;
import com.evaluacion.hospital.service.CitaService;

import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.obtenerTodas());
        return "citas";
    }

    @GetMapping("/nueva")
    public String nuevaCitaForm(Model model) {
        model.addAttribute("cita", new Cita());
        return "formulario-cita";
    }

    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita, Model model) {
        String resultado = citaService.agendarCita(cita);
        model.addAttribute("mensaje", resultado);
        return "redirect:/citas";
    }
}