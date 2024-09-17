package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(value = "Product")
public class ProductMongo {

    @Id
    private String id;
    private String name;
    private String description;
    private String price;

    public ProductMongo(String name, String description, String price) {
        this.description = description;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "ProductMongo[id=%s, name='%s', description='%s', price=%s]",
                id, name, description, price);
    }
}
