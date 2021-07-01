package com.example.server.Services;

import com.example.server.Model.Command;
import com.example.server.Model.CommandItem;
import com.example.server.Model.DemandeRA;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.CommadRepository;
import com.example.server.RepositoryInterFace.CommandIthemRespository;
import com.example.server.RepositoryInterFace.DemandeRARespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CommandServcie {
    @Autowired
    CommadRepository commadRepository;
    @Autowired
    CommandIthemRespository commandIthemRespository;
    @Autowired
    DemandeRARespository demandeRARespository;
    public List<Command>commandNoDelivred(){
        return commadRepository.CommandNoDelivred();
    }
    public Command getCommandbyId(String id){
        Optional<Command> command =commadRepository.findById(id);
        if (command.isEmpty())
            return null;
        return command.get();
    }
    public Command getCommandbyIthem(String idIthem){
        return commadRepository.findByIthem(idIthem);
    }
    public void DelivredCommand(String id){

        Command c= commadRepository.findById(id).get();
        c.setDelivred(true);
        commadRepository.save(c);
    }
    public void DemandeRA(User user,String ithemId,String raison){
        Date achatDate=commadRepository.findByIthem(ithemId).getDate();
        if(chekDateDifference(achatDate))
        {
            DemandeRA demandeRA=new DemandeRA();
            CommandItem commandItem=commandIthemRespository.findById(ithemId).get();
            demandeRA.setCommandItem(commandItem);
            demandeRA.setDatebuy(achatDate);
            demandeRA.setUser(user);
            demandeRA.setRaison(raison);
            demandeRARespository.save(demandeRA);
        }

    }

    private boolean chekDateDifference(Date date){
       LocalDateTime localTime=LocalDateTime.now();
       Date currentDate=Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant());
       long diffInMillies = Math.abs(date.getTime() - currentDate.getTime());
       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff <= 30;
   }
   public CommandItem getIthem(String id){
        return commandIthemRespository.findById(id).get();
   }
   public void liveredCommand(String id){
        Command command=commadRepository.findById(id).get();
        command.setDelivred(true);
        commadRepository.save(command);
   }
   public List<Command>historiqueUser(User user){
        return commadRepository.historique(user.getIdUser());
   }
}
