package com.farming.repository;

import com.farming.models.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Integer> {
    public void deleteById(Integer id);
    public List<Blogs> findByEmail(String email);
    public Blogs findById(Integer id);
}
