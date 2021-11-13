package pl.coderslab.springhibernatemod6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.springhibernatemod6.dao.PersonDao;
import pl.coderslab.springhibernatemod6.entity.Person;
import pl.coderslab.springhibernatemod6.entity.PersonDetails;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @RequestMapping("/test")
    public String testJsp() {
        return "index";
    }

   /* ZAD 1. Formularze

   @RequestMapping("/form")
    public String showForm() {
        return "person/personForm";
    }
    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@RequestParam String login, @RequestParam String password, @RequestParam String email) {
    Person person = new Person();
    person.setLogin(login);
    person.setPassword(password);
    person.setEmail(email);
    personDao.addPerson(person);
    return "Udalo się zapisać osobę";
    }*/


/*ZAD.2*/
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "person/personForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@ModelAttribute("person") Person testPerson){
        personDao.addPerson(testPerson);
        return "Udało się zapisać osobę";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String persist() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Jan");
        personDetails.setLastName("Kowalski");
        personDetails.setStreetnumber(45);
        personDetails.setStreet("Głowna");
        personDetails.setCity("Warszawa");

        Person person = new Person();
        person.setLogin("test123");
        person.setEmail("test123@o2.pl");
        person.setPassword("test123");
        person.setPersonDetails(personDetails);

        personDao.addPerson(person);
        return "Jest sukces!";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public String findById(@PathVariable("id") long id) {
        Person person = personDao.findByID(id);
        return person.toString();
    }

    @GetMapping("/update/{id}")
    @ResponseBody
    public String merge(@PathVariable("id") long id) {
        Person person = personDao.findByID(id);
        person.setPassword("Super ekstra tajne nowe haslo");
        person.getPersonDetails().setFirstName("Staszek");
        personDao.update(person);
        return "Zaktualizowano osobe o id " + id;
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable("id") long id) {
        Person person = personDao.findByID(id);
        personDao.delete(person);
        return "Usunieto osobe";
    }

}
