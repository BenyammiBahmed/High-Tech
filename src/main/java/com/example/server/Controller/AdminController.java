package com.example.server.Controller;

import com.example.server.Model.*;
import com.example.server.Services.*;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/ADMIN")
public class AdminController {
    @Autowired
    LoginService login;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommandServcie commandServcie;
    @Autowired
    WaitListService waitListService;
    @Autowired
    DemandeRAService demandeRAService;
    @Autowired
    CompteService compteService;

    @GetMapping("")
    public String AdminIndex(HttpSession session, Model model) {

            model.addAttribute("commands",commandServcie.commandNoDelivred());

            return "Admin/listeCommandeDashboard";

    }

    @GetMapping("/Account")
    public String account(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "Admin/AccountAdmin";
    }

    @GetMapping("/ListArticle")
    public String ListArticle(Model model, HttpSession session) {

        System.out.println(articleService.articlesNotBlocked().size());
        model.addAttribute("articles", articleService.articlesNotBlocked());
        return "Admin/Articles";
    }

    @PostMapping("/UpDateArticle")
    public String upDateArticle(HttpSession session, @RequestParam String codeModele, @RequestParam double price,
                                @RequestParam int quantity) {

        articleService.updateArticle(codeModele, price, quantity);
        return "redirect:/ADMIN/ListArticle";
    }

    @GetMapping("/RemoveArticle/{id}")
    public String RemoveArticle(@PathVariable String id, HttpSession session) {
        if (!login.chekAdmin(session))
            return "redirect:/login";
        articleService.deletArticle(id);
        return "redirect:/ADMIN/ListArticle";
    }

    @PostMapping("/AddArticle")
    public String addArticle(HttpSession session, Article article, @RequestParam Map<String, ?> properity, MultipartFile image) {
        properity.remove("image");
        articleService.saveArticle(article, properity, image);
        return "redirect:/ADMIN/ListArticle";
    }

    @GetMapping("/ListCommand")
    public String ListCommand(Model model, HttpSession session) {
        model.addAttribute("commands",commandServcie.commandNoDelivred());
        return "Admin/listeCommandeDashboard";
    }
    @GetMapping("/DetailCommand/{id}")
    public String DetailCommand(@PathVariable String id, Model model, HttpSession session) {
        model.addAttribute("command", commandServcie.getCommandbyId(id));

            return "Admin/detailleCommande";
    }
    @GetMapping("/WaitList")
    public String WaitList(HttpSession session, Model model){
        model.addAttribute("waitList",waitListService.articleWaitList());
        return "Admin/CustomerRequests";
    }
    @GetMapping("/DemandeRA")
    public String demandeRA(HttpSession session,Model model){
        List<DemandeRA> demandeRAList=demandeRAService.demandeRANoTreated();
        System.out.println(demandeRAList.size());
        model.addAttribute("demandeRA",demandeRAService.demandeRANoTreated());
        return "Admin/ReturnRequests";
    }
    @GetMapping("/DemandeRA/{id}")
    public String demande(@PathVariable String id, HttpSession session,Model model){
        model.addAttribute("demandeRA",demandeRAService.getDemande(id));
        return "Admin/returnRequestInfo";
    }
    @GetMapping("/TreatDemande/{accepte}")
    public String treatDemande(@PathVariable boolean accepte, @RequestParam("id")String id,HttpSession session){
        demandeRAService.treatDemandeRA(accepte,id);
        return "redirect:/DemandeRA";

    }
    @GetMapping("/AjouterArticle")
    public String AjouterArticle(HttpSession session){
        return "Admin/AddItem";
    }
    @GetMapping("/AjouterArticle/{type}")
    public String addArticle(HttpSession session,@PathVariable String type){
        return "Admin/Add"+type;
    }
    @PostMapping("/Update")
    public String update(User user, Addresse addresse, HttpSession session) {
        user.setAddresse(addresse);
        user.setRole(Roles.ADMIN);
        user= compteService.CompteUpdate(user);
        session.setAttribute("user",user);
        return "redirect:/ADMIN/Account";
    }
    @GetMapping("/BonCommand/{id}")
    public String BonCommand(HttpSession session,@PathVariable String id,Model model){

        model.addAttribute("commande",commandServcie.getCommandbyId(id));
        return "Admin/bonCommande";
    }
    @RequestMapping(path = "/livredCommand/{id}")
    public ResponseEntity<?> getPDF(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
      commandServcie.liveredCommand(id);
        ConverterProperties properties = new ConverterProperties();
        MediaDeviceDescription mediaDeviceDescription =
                new MediaDeviceDescription(MediaType.PRINT);
        properties.setMediaDeviceDescription(mediaDeviceDescription);
        URL url = new URL("http://localhost:8080/ADMIN/BonCommand/"+id);
        File file = new File("commad.pdf");
        HtmlConverter.convertToPdf(url.openStream(), new FileOutputStream(file), properties);
        byte[] bytes = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

    }
}
