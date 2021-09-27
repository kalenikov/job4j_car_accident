package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RulesService;
import ru.job4j.accident.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final AccidentService as;
    private final TypeService ts;
    private final RulesService rs;

    public AccidentControl(AccidentService accidentService, TypeService typeService, RulesService rulesService) {
        this.as = accidentService;
        this.ts = typeService;
        this.rs = rulesService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", ts.findAll());
        model.addAttribute("rules", rs.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(ts.get(accident.getType().getId()));
        String[] ruleIds = req.getParameterValues("ruleIds");
        accident.setRules(Arrays.stream(ruleIds).map(id -> rs.get(Integer.parseInt(id))).collect(Collectors.toSet()));
        as.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", as.get(id));
        model.addAttribute("types", ts.findAll());
        model.addAttribute("rules", rs.findAll());
        return "accident/edit";
    }
}