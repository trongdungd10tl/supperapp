package com.example.supperapp.dao;

import com.example.supperapp.model.Reviewer;

import java.util.List;
import java.util.Optional;

public interface ReviewerDao {
    List<Reviewer> findAll();
//    Optional<Reviewer> findById(String id);
//    void save(Reviewer reviewer);
//    void update(String id, Reviewer reviewer);
//    void delete(String id);
//
//    List<Reviewer> findByMsisdn(String keyword);
//    List<Reviewer> findByFullName(String keyword);
//
//    List<Reviewer> findWithPage(int offset, int limit);
//
//    int countReviewer();
}
