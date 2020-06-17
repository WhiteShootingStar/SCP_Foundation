package com.MASFinalProject.FinalProject;

import com.MASFinalProject.FinalProject.model.O12Member;
import com.MASFinalProject.FinalProject.model.SCP;
import com.MASFinalProject.FinalProject.model.SCPType;
import com.MASFinalProject.FinalProject.model.Scientist;
import com.MASFinalProject.FinalProject.repository.O12MemberRepo;
import com.MASFinalProject.FinalProject.repository.SCPRepo;
import com.MASFinalProject.FinalProject.repository.ScientistRepo;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataCreator {
    @Autowired
    private SCPRepo scpRepo;
    @Autowired
    private ScientistRepo scientistRepo;
    @Autowired
    private O12MemberRepo o12MemberRepo;



    public void generateData(){
        O12Member member1 = new O12Member("Pog","Champ","kek","shrek");
        O12Member member2 = new O12Member("Char","Aznable","Pudge","radiance");
        O12Member member3 = new O12Member("Haman","Karn","qwerty","qwerty");

        Scientist scientist1 = new Scientist("S1","Uwu","Dr","Medicine");
        Scientist scientist2 = new Scientist("S2","Ayayaya","Dr","Anime");
        Scientist scientist3 = new Scientist("Giorno","Giovanno","Proff","Physics");
        Scientist scientist4 = new Scientist("Sanya","Sidl","Proff","Go");

        SCP scp1 = new SCP(106, SCPType.KETER,"Very scary");
        SCP scp2 = new SCP(173, SCPType.EUCLID,"Not that scary");
        SCP scp3 = new SCP(682, SCPType.KETER,"Very scary and immortal");
        SCP scp4 = new SCP(505, SCPType.SAFE,"Don't smoke it");
        SCP scp5 = new SCP(76, SCPType.EUCLID,"Don't approach");
        SCP scp6 = new SCP(286, SCPType.KETER,"It imitates human voice");
        SCP scp7 = new SCP(912, SCPType.SAFE,"Don't put living objects in it");

        scientist1.addToObserved(scp1);
        scientist1.addToObserved(scp2);
        scientist1.addToObserved(scp3);
        scientist1.addToConducted(scp4);
        scientist2.addToConducted(scp1);
        scientist2.addToConducted(scp2);
        scientist2.addToConducted(scp3);
        scientist2.addToObserved(scp4);
        scientist3.addToConducted(scp5);
        scientist3.addToConducted(scp1);
        scientist4.addToObserved(scp2);
        scientist4.addToConducted(scp4);
        scientist4.addToConducted(scp6);
        scientist4.addToObserved(scp7);

        o12MemberRepo.save(member1);
        o12MemberRepo.save(member2);
        o12MemberRepo.save(member3);

        scientistRepo.save(scientist1);
        scientistRepo.save(scientist2);
        scientistRepo.save(scientist3);
        scientistRepo.save(scientist4);

    }
}
