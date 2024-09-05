package com.kumcode.kart.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumcode.kart.model.ProductDTLS;

public interface ProductRepo extends JpaRepository<ProductDTLS,Serializable> {
    
}

