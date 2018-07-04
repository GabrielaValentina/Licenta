package com.example.demo.Repository;

import com.example.demo.Domain.MessageChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface MessageChatRepository extends JpaRepository<MessageChat, Integer>{
    @Query(value = "SELECT * FROM MESSAGE ORDER BY sent_at DESC", nativeQuery = true)
    List<MessageChat> findAll();

}
