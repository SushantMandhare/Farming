package com.farming.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.models.Product;
import com.farming.models.User;
import com.farming.repository.FarmerRepository;
import com.farming.repository.ProductRepository;
import com.farming.service.ProductService;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
public class ProductController {
	@Autowired
	public ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private FarmerRepository farmerRepository;
    @Value("${multipart.location}")
    public String pathlocation;

    @RequestMapping(value = "/products", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> saveProduct(@RequestPart String product,@RequestPart("file") List<MultipartFile> file) {
        Product product1=new Product();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            product1=objectMapper.readValue(product,Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path filepath = Paths.get(pathlocation, file.get(0).getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.get(0).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        product1.setImagePath(pathlocation+"\\"+file.get(0).getOriginalFilename());
        productService.saveProduct(product1);

        return ResponseEntity.ok().body("Product added successfully");
    }
    @GetMapping("/products/{email}")
    public ResponseEntity<List<Product>> allProduct(@PathVariable String email) {
        return ResponseEntity.ok().body(productService.allProducts(email));
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> allProduct() {
        return ResponseEntity.ok().body(productService.allProducts());
    }
    @GetMapping("/product/{pid}")
    public ResponseEntity<String> saveProduct(@PathVariable Integer pid) {
        productService.deleteProduct(pid);
        return ResponseEntity.ok().body("Product Delete sucessfully");
    }

}