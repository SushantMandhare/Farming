package com.farming.controller;

import com.farming.models.Blogs;
import com.farming.models.Product;
import com.farming.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
	@Autowired
	public BlogService blogService;

	@Value("${multipart.location}")
	public String pathlocation;

	@GetMapping("/blogs")
	public ResponseEntity<List<Blogs>> homeControllerHandler()
	{
		return ResponseEntity.ok().body(blogService.allBlogs());
	}
	@GetMapping("/blogs/{email}")
	public ResponseEntity<List<Blogs>> blogsbyemail(@PathVariable String email)
	{
		return ResponseEntity.ok().body(blogService.getBlogByEmail(email));
	}
	@GetMapping("/blogdetails/{id}")
	public ResponseEntity<Blogs> blogsbyemail(@PathVariable Integer id)
	{
		return ResponseEntity.ok().body(blogService.getBlogById(id));
	}
	@GetMapping("/blog/{id}")
	public ResponseEntity<String> deleteblogsbyemail(@PathVariable Integer id)
	{
		blogService.deleteBlogById(id);
		return ResponseEntity.ok().body("Deleted successfully..");
	}
	@RequestMapping(value = "/blogs", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> saveBlog(@RequestPart String blog,@RequestPart("image") List<MultipartFile> image,
										   @RequestPart("video") List<MultipartFile> video){

		Blogs blogs=new Blogs();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			blogs=objectMapper.readValue(blog,Blogs.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Path imagePath = Paths.get(pathlocation, image.get(0).getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(imagePath)) {
			os.write(image.get(0).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		blogs.setImagePath(pathlocation+"\\"+image.get(0).getOriginalFilename());

		Path videoPath = Paths.get(pathlocation, video.get(0).getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(videoPath)) {
			os.write(video.get(0).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		blogs.setVideoPath(pathlocation+"\\"+video.get(0).getOriginalFilename());
		blogService.saveBlogs(blogs);
		return ResponseEntity.ok().body("Blog added successfully");
	}
}
