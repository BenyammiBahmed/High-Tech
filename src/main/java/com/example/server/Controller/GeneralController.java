package com.example.server.Controller;

import com.example.server.Model.*;
import com.example.server.RepositoryInterFace.ImageRespository;
import com.example.server.RepositoryInterFace.UserRepository;
import com.example.server.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Optional;


@Controller
public class GeneralController {

    @Autowired
    LoginService loginService;
    @Autowired
    CompteService registrate;
    @Autowired
    ImageRespository imageRespository;
    @Autowired
    SearchService searchService;
    @Autowired
    ArticleService articleService;
    @Autowired
    DemandeRAService demandeRAService;
   @Autowired
   ConfigirationService configirationService;
   @Autowired
    UserRepository  repository;
   @Autowired
   CryptoService cryptoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("result", searchService.newArticles());


        return "index";
    }
    @GetMapping("/Logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/LoginPage")
    public String loginPage(@RequestParam(required = false) String message, Model model) {

        return "Login";
    }

    @GetMapping("/RegistratePage")
    public String RegistratePage() {
        return "Registrate";
    }

    @PostMapping("/Registrate")
    public String Registrat(User user, Addresse addresse, Model model, HttpSession session) {
        user.setRole(Roles.USER);
        System.out.println("user");
        user.setAddresse(addresse);
        user = registrate.Registrate(user);
        if (user == null || user.getIdUser() == null) {
            model.addAttribute("MessageError", "email hsa already exite");
            return "redirect:/RegistratePage";
        } else
            return "redirect:/";
    }

    @GetMapping("/Login")
    public String login(@RequestParam("email") String email, Model model, @RequestParam("password") String password, HttpSession session) {
        User user = loginService.login(email, password);
        System.out.println(user==null);
        if (user == null) {
            model.addAttribute("MessageError", "Email or password is incorrect");
            return "Login";
        } else {
            session.setAttribute("user", user);
            if (user.getRole().equals(Roles.ADMIN))
                return "redirect:/" + user.getRole().toString();
            else return "redirect:/";
        }
    }

    @GetMapping("/search")
    public String searchArticle(String search, Model model) {
        model.addAttribute("search", search);
        model.addAttribute("result", searchService.search(search));
        return "index";
    }

    @GetMapping("/{type}")
    public String searchType(@PathVariable String type, Model model) {

        model.addAttribute("result", searchService.searchType(type));
        return "index";
    }

    @GetMapping("/Article/{id}")
    public String articleId(@PathVariable String id, Model model) {
        System.out.println(id);
        model.addAttribute("article", searchService.articleById(id));
        return "Product_details";

    }

    @GetMapping("/ReturnRequestInfPage/{id}")
    public String ReturnRequestInfPage(@PathVariable String id,HttpSession session,Model model){
        User user= (User) session.getAttribute("user");
        if (user==null)
            return "redirect:/LoginPage";
        model.addAttribute("demandeRA",demandeRAService.getDemande(id));

            return "Admin/returnRequestInfo";


    }
     @GetMapping("/DemandeRA")
    public String demandeRA(HttpSession session,Model model){
        User user= (User) session.getAttribute("user");
        if (user==null)
            return "redirect:/LoginPage";
        model.addAttribute("demandeRA",demandeRAService.demandeRANoTreated());
        if (user.getRole().equals(Roles.ADMIN))
            return "Admin/ReturnRequests";
        else
            return "";

    }
    @GetMapping("/ConfigChooseCPU")
    public String configration(Model model){
        model.addAttribute("listCPU",searchService.searchType("CPU"));
        return "ConfigChooseCPU";
    }
    @GetMapping("/ConfigChooseMB/{id}")
    public String configrationPage2(@PathVariable String id, Model model,HttpSession session){
        session.setAttribute("CPU",searchService.articleById(id));
        model.addAttribute("listMB",configirationService.matherboard(id));
        return "ConfigChooseMB";
    }
    @GetMapping("/ConfigChooseRAM/{id}")
    public String configrationRAM(@PathVariable String id, Model model,HttpSession session){
        session.setAttribute("MB",searchService.articleById(id));
        model.addAttribute("listRAM",configirationService.ram(id));
        return "ConfigChooseRAM";
    }
    @GetMapping("/ConfigChooseFinal/{id}")
    public String configrationfinal(@PathVariable String id, Model model,HttpSession session){
        session.setAttribute("RAM",searchService.articleById(id));
        model.addAttribute("listRAM",configirationService.ram(id));
        return "ConfigChooseMBFinal";
    }
    @RequestMapping(value = "/getPhoto/{id}", method = RequestMethod.GET)
    public @ResponseBody
    void getPhoto(@PathVariable String id, HttpServletResponse response) {
        System.out.println("ok");
        try {
            Optional<Image> opImage = imageRespository.findById(id);
            Image image = opImage.get();
            byte[] imagenEnBytes = image.getImage().getData();
            response.setHeader("Accept-ranges", "bytes");
            response.setContentType("image/jpeg");
            response.setContentLength(imagenEnBytes.length);
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Description", "File Transfer");
            response.setHeader("Content-Transfer-Encoding", "binary");

            OutputStream out = response.getOutputStream();
            out.write(imagenEnBytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block

        }
    }

}
