package com.farming.service;

import com.farming.models.Blogs;
import com.farming.repository.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{
    @Autowired
    public BlogsRepository blogsRepository;

    @Override
    public Blogs saveBlogs(Blogs blogs) {
        return blogsRepository.save(blogs);
    }

    @Override
    public List<Blogs> allBlogs() {
        return blogsRepository.findAll();
    }

    @Override
    public List<Blogs> getBlogByEmail(String email) {
        return blogsRepository.findByEmail(email);
    }

    @Override
    public void deleteBlogById(Integer id) {
        blogsRepository.deleteById(id);
    }

    @Override
    public Blogs getBlogById(Integer id) {
        return blogsRepository.findById(id);
    }

}
