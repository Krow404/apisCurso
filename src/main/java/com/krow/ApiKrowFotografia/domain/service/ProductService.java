package com.krow.ApiKrowFotografia.domain.service;

import com.krow.ApiKrowFotografia.domain.Product;
import com.krow.ApiKrowFotografia.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  ProductService {
    @Autowired //inicializar la clase que esta implementada
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(Integer productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(Integer categoryId){
        return productRepository.getByCategory(categoryId);
    }
    public Optional<List<Product>> getScarseProducts(int quantity){
        return productRepository.getScarseProducts(quantity);
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public boolean delete(Integer productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);

        /* FORMA MAS LARGA DE OBTENER EL BOOLEAN
        if(getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        }else{
            return false;
        }*/
    }
}
