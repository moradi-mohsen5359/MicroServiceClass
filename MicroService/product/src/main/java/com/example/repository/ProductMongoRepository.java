package com.example.repository;

import com.example.entity.Product;
import com.example.entity.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductMongo, String> {
}
