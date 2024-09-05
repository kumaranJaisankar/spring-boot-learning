package com.kumcode.kart.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumcode.kart.Repository.ProductRepo;
import com.kumcode.kart.model.ProductDTLS;



@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public ProductDTLS createProduct(ProductDTLS product){
        return repo.save(product);
    }
    public  List<ProductDTLS> getAllProducts(){
        return repo.findAll();
    }
    public Optional<ProductDTLS> getProductById(UUID id){
        return repo.findById(id.toString());
    }
     
    public ProductDTLS updateProduct(UUID id, ProductDTLS updatedProduct) {
        return repo.findById(id.toString()).map(product -> {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
            product.setCategory(updatedProduct.getCategory());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setImages(updatedProduct.getImages());
            // Update other fields as necessary
            return repo.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public String deleteProduct(UUID id){
        Optional<ProductDTLS> prod=repo.findById(id);
        repo.deleteById(id.toString());
        return "deleted successfully /n"+prod;
    }
    public ProductDTLS updateProduct(String id, ProductDTLS entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }
}
