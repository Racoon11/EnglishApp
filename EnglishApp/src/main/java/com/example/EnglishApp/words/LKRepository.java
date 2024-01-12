package com.example.EnglishApp.words;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface LKRepository extends CrudRepository<UserLK, Long> {
    //UserLK findByIdAndNick(Long id, String nick);
    UserLK findById(String id);
    UserLK findByIdAndEmail(Long id, String email);
    //Boolean findByEmail(String email);
    List<UserLK> findByEmail(String email);
    boolean existsByIdAndEmail(Long id, String email);
}