package com.example.EnglishApp;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LKRepository extends CrudRepository<UserLK, Long>, PagingAndSortingRepository<UserLK, Long> {
    UserLK findByIdAndOwner(Long id, String owner);
    Page<UserLK> findByOwner(String owner, PageRequest amount);
    Boolean existsByIdAndOwner(Long id, String owner);
}