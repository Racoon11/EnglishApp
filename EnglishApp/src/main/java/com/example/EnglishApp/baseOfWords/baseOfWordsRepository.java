package com.example.EnglishApp.baseOfWords;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;
import java.util.Optional;

public interface baseOfWordsRepository extends CrudRepository<baseOfWords, Long> {
    //UserLK findByIdAndNick(Long id, String nick);
    Optional<baseOfWords> findById(Long id);
    baseOfWords findByWordEng(String wordEng);
    List<baseOfWords> findFirst10ByWordEngStartingWith(String eng);
}