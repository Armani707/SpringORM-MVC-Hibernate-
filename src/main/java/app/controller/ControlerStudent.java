package app.controller;

import app.model.Student;
import app.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@Controller
public class ControlerStudent {


    ServiceLayer serviceLayer;


    @Autowired
    public ControlerStudent(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/findall")
    public String allStudents(Model model) {
        List <Student> list = serviceLayer.findAllStudents();
        model.addAttribute("students", list);
        return "findallstudents";
    }

    @GetMapping("/create")
    public String createPerson(Model model) {
        model.addAttribute("student", new Student());
        return "storage";
    }

    @GetMapping("/savePerson")  //ModelAttribute берет данные из storage student
    public String saveStudent(@ModelAttribute ("student") Student student) {
        serviceLayer.saveOrUpdate(student);
        return "redirect:/api/findall";
    }

    @GetMapping("/findbyid/{id}")
    public String findById(@PathVariable ("id") int id, Model model) {
        Optional<Student> byId = serviceLayer.findById(id);
        model.addAttribute("studentfind", byId);
        return "findbyid";
    }

    @GetMapping("/deletebyid")
    public String deleteById(@RequestParam("id") int id) {
        serviceLayer.deleteById(id);
        return "redirect:/api/findall";
    }

    @GetMapping("/updatebyid")
    public String updateById(@RequestParam("id") int id, Model model) {
        Student student = serviceLayer.updateById(id);
        model.addAttribute("student", student);
        return "storage";
    }
}
