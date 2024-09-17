package com.example.service;

import com.example.dto.DiscountResponseDTO;
import com.example.dto.ProductRequestDTO;
import com.example.entity.Product;
import com.example.entity.ProductMongo;
import com.example.repository.ProductMongoRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ProductService {

 /*   @Autowired
    private RestClient restClient;*/
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMongoRepository productMongoRepository;




    public ProductRequestDTO create(ProductRequestDTO productDTO) {
//        Discount discount = restTemplate.getForObject("http://localhost:8080/api/v1/coupons/{couponName}", Discount.class, product.getCouponCode());

        //RestTemplate Sample
        DiscountResponseDTO discount = restClient.get()
                .uri("/coupons/{couponName}", productDTO.getCouponCode())
                .header("Content-Type", "application/json")
                .retrieve()
                .body(DiscountResponseDTO.class);

        BigDecimal coupon = new BigDecimal("100").subtract(discount.getCoupon());
        BigDecimal newPrice = coupon.divide(new BigDecimal("100")).multiply(productDTO.getPrice());
        productDTO.setPrice(newPrice);

        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product savedProduct =  productRepository.save(product);

        //mongoDB Insert
        ProductMongo productMongo
                = new ProductMongo(product.getName(), product.getDescription(), product.getPrice().toString());
        productMongoRepository.insert(productMongo);
        return productDTO;
    }
}
