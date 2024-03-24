package com.farming.service;

import com.farming.models.Blogs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    public Blogs saveBlogs(Blogs blogs);
    public List<Blogs> allBlogs();
    public List<Blogs> getBlogByEmail(String email);
    public void deleteBlogById(Integer id);
    public Blogs getBlogById(Integer id);
}
