package com.MASFinalProject.FinalProject.repository;

import com.MASFinalProject.FinalProject.model.O12Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface O12MemberRepo extends CrudRepository<O12Member,Long> {
    @Query("select  o from O12Member o where  o.login=?1 and o.password=?2")
    Optional<O12Member> getO12MemberByLoginAndPassword(String login, String Password);
}
