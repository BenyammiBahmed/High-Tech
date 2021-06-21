package com.example.server.Controller;

import com.example.server.Model.Article;
import com.example.server.Services.ArticleService;
import com.example.server.Services.LoginService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.util.Map;


@Controller
@RequestMapping("/ADMIN")
public class AdminController {
    @Autowired
    LoginService loginService;
    @Autowired
    ArticleService articleService;

    @GetMapping("")
    public String AdminIndex(HttpSession session, Model model) {
        if (loginService.chekAdmin(session))
            return "Admin/index";
        else
            return "Login";

    }
    @PostMapping("addArticle")
    public String addArticle(Article article , @RequestParam Map<String,?> properity, MultipartFile image){
        properity.remove("image");
        articleService.saveArticle(article,properity,image);
        return "ok";
    }

    @RequestMapping(path = "/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ConverterProperties properties = new ConverterProperties();
        MediaDeviceDescription mediaDeviceDescription =
                new MediaDeviceDescription(MediaType.PRINT);
        properties.setMediaDeviceDescription(mediaDeviceDescription);
        URL url=new URL("http://localhost:8080/");
        File file=new File("commad.pdf");
    HtmlConverter.convertToPdf(url.openStream(), new FileOutputStream(file), properties);

        /* extract output as bytes */
        byte[] bytes= Files.readAllBytes(file.toPath());


        /* Send the response as downloadable PDF */

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

    }
}
