package com.example.EnglishApp.words;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LKRepository extends JpaRepository<UserLK, Long> {
    //UserLK findByIdAndNick(Long id, String nick);
    UserLK findByEmail(String email);
    //Boolean findByEmail(String email);
    Page<UserLK> findAllByEmail(String email, PageRequest amount);
}