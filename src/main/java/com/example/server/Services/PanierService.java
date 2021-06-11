package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.RepositoryInterFace.ArticleRepository;
import com.example.server.ServerClass.Panier;
import com.example.server.ServerClass.PanierIthem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PanierService {
    @Autowired
    ArticleRepository articleRepository;
    public boolean reservedActicle(Article article, int quntity, HttpSession session){
        article=articleRepository.findByCodeModle(article.getCodeModele());
        if(article.getQuantity()<quntity)
            return false;
        else
        {
            article.setQuantity(article.getQuantity()-quntity);
            articleRepository.save(article);
            Panier panier =getPanierSession(session);
            PanierIthem panierIthem=new PanierIthem(article,quntity,session);
            panier.getPanierIthems().add(panierIthem);
            return true;
        }
    }
    private Panier getPanierSession(HttpSession session){
        Panier panier= (Panier) session.getAttribute("panier");
        if(panier==null)
        {
            panier=new Panier();
            session.setAttribute("panier",panier);
            return panier;

        }
        else
            return panier;
    }
}
