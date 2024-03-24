package com.farming.service;
import com.farming.models.Product;
import com.farming.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farming.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductServiceImplementation  implements ProductService {

	@Autowired
    public ProductRepository productRepository;

	@Autowired
	public OrderService orderService;
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> allProducts(String email) {
		return productRepository.findByFarmerEmail(email);
	}
	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	@Override
	public void deleteProduct(Integer pid) {
		productRepository.delete(pid);
		orderService.deleteByPID(pid);
	}

	@Override
	public void lessQuantity(Integer pid,Integer quantity) {
		Product product=productRepository.findOne(pid);
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product);
	}
	@Override
	public void addQuantity(Integer pid,Integer quantity) {
		Product product=productRepository.findOne(pid);
		product.setQuantity(product.getQuantity()+quantity);
		productRepository.save(product);
	}
}