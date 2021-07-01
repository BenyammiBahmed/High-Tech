package com.example.server.Services;

import com.example.server.Model.CommandItem;
import com.example.server.Model.DemandeRA;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.CommandIthemRespository;
import com.example.server.RepositoryInterFace.DemandeRARespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeRAService {
    @Autowired
    DemandeRARespository demandeRARespository;
    @Autowired
    CommandIthemRespository commandIthemRespository;
    public void save(String id, Date date,String rasion,User user,int quantity){
       Optional <CommandItem>commandItem= commandIthemRespository.findById(id);
       if (!commandItem.isEmpty()){
           DemandeRA demandeRA=new DemandeRA();
           demandeRA.setUser(user);
           demandeRA.setDatebuy(date);
           demandeRA.setRaison(rasion);
           demandeRA.setQuantity(quantity);
           demandeRA.setCommandItem(commandItem.get());
           demandeRARespository.save(demandeRA);
       }

    }
 public DemandeRA getDemande(String s){
        return demandeRARespository.findById(s).get();
 }
    public void treatDemandeRA(boolean isAccepte,String idDemande){
        DemandeRA demandeRA=demandeRARespository.findById(idDemande).get();
        if(demandeRA!=null)
        {

            demandeRA.setTreat(true);
            demandeRA.setAccepte(isAccepte);
            demandeRARespository.save(demandeRA);
        }
    }
    public List<DemandeRA> listDemandeUser(User user){
        return demandeRARespository.findByUser(user);
    }
    public void removeDemande(String idDemaonde){
        demandeRARespository.deleteById(idDemaonde);
    }
    public List<DemandeRA>demandeRANoTreated(){
        return demandeRARespository.demandeNoTreated();
    }
}
