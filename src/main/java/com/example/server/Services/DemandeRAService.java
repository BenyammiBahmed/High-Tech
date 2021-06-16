package com.example.server.Services;

import com.example.server.Model.DemandeRA;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.DemandeRARespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeRAService {
    @Autowired
    DemandeRARespository demandeRARespository;
    public void treatDemandeRA(boolean isAccepte,String reponse,String idDemande){
        DemandeRA demandeRA=demandeRARespository.findById(idDemande).get();
        if(demandeRA!=null)
        {
            demandeRA.setResponse(reponse);
            demandeRA.setTreat(true);
            demandeRA.setAccepte(isAccepte);
        }
    }
    public List<DemandeRA> listDemandeUser(User user){
        return demandeRARespository.findByUser(user);
    }
    public void removeDemande(String idDemaonde){
        demandeRARespository.deleteById(idDemaonde);
    }
}
