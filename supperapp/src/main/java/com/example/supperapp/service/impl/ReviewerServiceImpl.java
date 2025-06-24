package com.example.supperapp.service.impl;


import com.example.supperapp.dao.ReviewerDao;
import com.example.supperapp.model.Reviewer;
import com.example.supperapp.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewerServiceImpl implements ReviewerService {


    private final ReviewerDao reviewerDao;


    public ReviewerServiceImpl(ReviewerDao reviewerDao){
        this.reviewerDao = reviewerDao;
    }

    @Override
    public List<Reviewer> getAllReviewer(){
        return reviewerDao.findAll();
    }

//    @Override
//    public Optional<Reviewer> findById(String id){
//        return reviewerDao.findById(id);
//    }
//
//    @Override
//    public void createReviewer(Reviewer reviewer) {
//        reviewerDao.save(reviewer);
//    }
//
//    @Override
//    public void deleteReviewer(String id) {
//        reviewerDao.delete(id);
//    }
//
//    @Override
//    public void updateReviewer(String id, Reviewer reviewer) {
//        reviewerDao.update(id, reviewer);
//    }
//
//    @Override
//    public List<Reviewer> findByMsisdn(String keyword) {
//        return reviewerDao.findByMsisdn(keyword);
//    }
//
//    @Override
//    public List<Reviewer> findByFullName(String keyword) {
//        return  reviewerDao.findByFullName(keyword);
//    }
//
//    @Override
//    public List<Reviewer> getReviewerWithPagination(int offset, int limit) {
//        return reviewerDao.findWithPage(offset,limit);
//    }
//
//    @Override
//    public int getTotalReviewerCount() {
//        return reviewerDao.countReviewer();
//    }


}
