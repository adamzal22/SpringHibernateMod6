package pl.coderslab.springhibernatemod6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.springhibernatemod6.classes.Student;
import pl.coderslab.springhibernatemod6.dao.PersonDao;
import pl.coderslab.springhibernatemod6.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @ModelAttribute("programmingSkills")
    public List<String> programmingSkills() {
        return Arrays.asList("Java","Python","C++","SQL",".net");
    }
    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("bieganie","pływanie","skakanie","turlanie","skradanie");
    }
    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Polska","Francja","Niemcy","Belgia");
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/studentForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@ModelAttribute("student") Student student){
        return student.toString();
    }


}
