package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.Model.Command;
import com.example.server.Model.CommandItem;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.ArticleRepository;
import com.example.server.RepositoryInterFace.CommadRepository;
import com.example.server.RepositoryInterFace.CommandIthemRespository;
import com.example.server.ServerClass.Panier;
import com.example.server.ServerClass.PanierIthem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.util.ArrayList;

@Service
public class PanierService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommadRepository commadRepository;
    @Autowired
    CommandIthemRespository commandIthemRespository;

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
        Panier panier= (Panier) session.getAttribute("pannier");
        if(panier==null)
        {
            panier=new Panier();
            session.setAttribute("pannier",panier);
            return panier;

        }
        else
            return panier;
    }
    public Command validPanier(Panier panier, User user){

        Command command=new Command();
        panier.CancelPainer();
        command.setUser(user);
        ArrayList<CommandItem> commandItems=new ArrayList<>();
        for (PanierIthem ithem: panier.getPanierIthems()){
            CommandItem commandItem=new CommandItem();
            commandItem.setArticle(ithem.getArticle());
            commandItem.setPrice(ithem.getArticle().getPrice());
            commandItem.setQuntity(ithem.getQuntity());
           commandItem= commandIthemRespository.save(commandItem);
            commandItems.add(commandItem);
        }
        command.setItemList(commandItems);
       return commadRepository.save(command);

    }
    public Panier supprimeIthem(Panier panier,String idArticle){
        for (PanierIthem ithem:panier.getPanierIthems())
        {
            if (ithem.getArticle().getCodeModele().equals(idArticle))
            {
                panier.getPanierIthems().remove(ithem);
                ithem.getTimer().cancel();
                ithem.libreArticle();

            }
        }
        return panier;
    }
}
