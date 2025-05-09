package com.evaluacion.hospital.controller;

import com.evaluacion.hospital.entity.Cita;
import com.evaluacion.hospital.repository.ConsultorioRepository;
import com.evaluacion.hospital.repository.DoctorRepository;
import com.evaluacion.hospital.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private ICitaService citaService;

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.obtenerTodas());
        return "citas";
    }

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @GetMapping("/nueva")
    public String nuevaCitaForm(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("doctores", doctorRepository.findAll());
        model.addAttribute("consultorios", consultorioRepository.findAll());
        return "formulario-cita";
    }

    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita, Model model) {
        String resultado = citaService.agendarCita(cita);
        model.addAttribute("mensaje", resultado);
        return "redirect:/citas";
    }

    @GetMapping("/consultar")
    public String consultarCitas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) Long consultorioId,
            Model model) {
        List<Cita> citas = citaService.buscarPorCriterios(fecha, doctorId, consultorioId);
        model.addAttribute("citas", citas);
        return "consulta-citas";
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtenerPorId(id);
        model.addAttribute("cita", cita);
        return "formulario-cita";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return "redirect:/citas/consultar";
    }
}