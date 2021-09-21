package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.TypeService;

@Controller
public class AccidentControl {
    private final AccidentService as;
    private final TypeService ts;

    public AccidentControl(AccidentService accidentService, TypeService typeService) {
        this.as = accidentService;
        this.ts = typeService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", ts.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accident.setType(ts.get(accident.getType().getId()));
        as.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", as.get(id));
        return "accident/edit";
    }
}