package com.example.server.Controller;

import com.example.server.Model.*;
import com.example.server.RepositoryInterFace.AddresseRepository;
import com.example.server.RepositoryInterFace.ArticleRepository;
import com.example.server.RepositoryInterFace.UserRepository;
import com.example.server.RepositoryInterFace.WaitListRespository;
import com.example.server.ServerClass.Order;
import com.example.server.ServerClass.Panier;
import com.example.server.ServerClass.UserCard;
import com.example.server.Services.*;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/USER")
public class UserController {
    @Autowired
    LoginService login;
    @Autowired
    PanierService panierService;
    @Autowired
    CompteService compteService;
    @Autowired
    WaitListService waitListService;
    @Autowired
    ArticleService articleService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    DemandeRAService demandeRAService;
    @Autowired
    CommandServcie commandServcie;

    @GetMapping("/AccountClientPage")
    public String account(Model model, HttpSession session) {

        System.out.println("ok");
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "AccountClient";
    }

    @PostMapping("/Update")
    public String update(User user, Addresse addresse, HttpSession session) {
        user.setAddresse(addresse);
        user.setRole(Roles.USER);
       user= compteService.CompteUpdate(user);
       session.setAttribute("user",user);
        return "redirect:/USER/AccountClientPage";
    }

    @PostMapping("/ReserveArticle")
    public String ReserveArticle(String codeModele, int quntityReserved, Model model, HttpSession session) {
        boolean b = panierService.reservedActicle(codeModele, quntityReserved, session);
        String message;
        if (b)
            message = "article is reserved for 15 minutes";

        else message = "not quantity enough";
        model.addAttribute("message", message);
        return "redirect:/";

    }

    @PostMapping("/WaitArticle")
    public String WaitArticle(String codeModele, Model model, int quntity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        waitListService.insertWaitList(codeModele, user, quntity);
        model.addAttribute("message", "");
        return "redirect:/";
    }

    @GetMapping("/Payment")

   public String payment(Model model, HttpSession session,HttpServletRequest request) throws PayPalRESTException {

        Order order=new Order();
        Panier panier= (Panier) session.getAttribute("pannier");
        order.setPrice(panier.total());
        order.setCurrency("USD");
        order.setDescription("Test payment");
        order.setIntent("sale");
        order.setMethod("paypal");
        String url= request.getRequestURL().toString().replace(request.getServletPath(),"");
        Payment payment= paymentService.createPayment
               (order,url);
        for(Links link:payment.getLinks()) {
            if(link.getRel().equals("approval_url")) {
                return "redirect:"+link.getHref();
            }
        }
        return "redirect:/";
    }
    @PostMapping("/ValidPayment")
    public String validPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model, HttpSession session) {
        try {

            Payment payment = paymentService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                panierService.validPanier((Panier) session.getAttribute("pannier"), (User) session.getAttribute("user"));
             model.addAttribute("message", "payment valid");
            session.removeAttribute("pannier");
                return "Thanks";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
//
//
//
    }

    @GetMapping("/PannierPage")
    public String pannierPage(HttpSession session, Model model) {

        Panier panier = (Panier) session.getAttribute("pannier");
        model.addAttribute("pannier", panier);
        return "cart";
    }

    @PostMapping("/LibreArticle/[id]")
    public String LibreArticle(@PathVariable String id, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/login";
        Panier panier = (Panier) session.getAttribute("pannier");
        if (panier != null)
            panierService.supprimeIthem(panier, id);
        return "redirect:/PannierPage";
    }
    @GetMapping("/WaitList")
    public String WaitList(HttpSession session,Model model){
        if (!login.chekUser(session))
            return "redirect:/login";
        User user= (User) session.getAttribute("user");
        model.addAttribute("waitList",waitListService.waitListsUser(user));
        return "PendingArticlesList";
    }
    @PostMapping("/UpDateWaitList")
    public String UpDateWaitList(@RequestParam("id") String id,@RequestParam("quntity") int quntity,HttpSession session){

        waitListService.upDate(id,quntity);
        return "redirect:/WaitList";
    }
    @PostMapping("/RemoveWaitList/{id}")
    public String RemoveWaitList(@PathVariable String id,HttpSession session){

        waitListService.removeWaitList(id);
        return "redirect:/WaitList";
    }
    @GetMapping("/HistoriqueAchat")
    public String historiqueAchat(HttpSession session,Model model){
        if (!login.chekUser(session))
            return "redirect:/login";
        User user= (User) session.getAttribute("user");
        model.addAttribute("historique",commandServcie.historiqueUser(user));
        return "PurchaseHistory";
    }
    @GetMapping("/HistoriqueAchat/{id}")
    public String CommandDetail(@PathVariable String id, HttpSession session,Model model){
        model.addAttribute("historique",commandServcie.getCommandbyId(id));
        return "HistoryCommandeDetail";
    }
    @GetMapping("/returnPage/{id}")
    public String returnPage(@PathVariable String id, HttpSession session,Model model){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String s=dateFormat.format(commandServcie.getCommandbyIthem(id).getDate());
            model.addAttribute("date",s);

        model.addAttribute("Ithem",commandServcie.getIthem(id));
        return "WhyReturn";
    }
    @PostMapping("/FinalReturn")
    public String DemandeRA(HttpSession session, @RequestParam("itemId") String id, @RequestParam("date")String date
    ,@RequestParam("rasion")String rasion,@RequestParam("quantity") int quantity) throws ParseException {
        User user= (User) session.getAttribute("user");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        demandeRAService.save(id,dateFormat.parse(date),rasion,user,quantity);
        return "redirect:/USER/DemandeRA";
    }
    @GetMapping("/ReturnRequestInfPage/{id}")
    public String ReturnRequestInfPage(@PathVariable String id,HttpSession session,Model model){
        User user= (User) session.getAttribute("user");
        if (user==null)
            return "redirect:/login";
        model.addAttribute("demandeRA",demandeRAService.getDemande(id));
        return "Admin/returnRequestInfo";
    }
    @GetMapping("/DemandeRA")
    public String demandeRA(HttpSession session,Model model){
        User user= (User) session.getAttribute("user");
        model.addAttribute("demandes",demandeRAService.listDemandeUser(user));
        return "etatDemandeRetraite";
    }
    @GetMapping("bonRetraite/{id}")
    public String BonCommand(@PathVariable String id,Model model){
        model.addAttribute("demande",demandeRAService.getDemande(id));
        return "bonRetraite";
    }
    @RequestMapping(path = "/RetrunDemande/{id}")
    public ResponseEntity<?> getPDF(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConverterProperties properties = new ConverterProperties();
        MediaDeviceDescription mediaDeviceDescription =
                new MediaDeviceDescription(MediaType.PRINT);
        properties.setMediaDeviceDescription(mediaDeviceDescription);
        URL url = new URL("http://localhost:8080/USER/bonRetraite/"+id);
        File file = new File("bonRetraite.pdf");
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
