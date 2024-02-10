package com.krow.ApiKrowFotografia.web.controller;

import com.krow.ApiKrowFotografia.domain.Product;
import com.krow.ApiKrowFotografia.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    @ApiOperation("get all supermarket products") //para la documentacion de swagger
    @ApiResponse(code = 200 , message = "OK")//Docuemtnacion de Swagger
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>( productService.getAll(), HttpStatus.OK); //response entity
    }


    @GetMapping("/{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404,message = "PRoduct not found")
    })
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer productId){
        return ResponseEntity.of(productService.getProduct(productId)); //para obtener un tipo optional
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") Integer categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        //Esta linea es algo igual
        //return productService.of(productService.getByCategory(categoryId)); //para obtener un tipo optional
    }

    public Optional<List<Product>> getScarseProducts(int quantity){
        return productService.getScarseProducts(quantity);
    }
    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product){

        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer productId){
       if(productService.delete(productId)){
           return new ResponseEntity(HttpStatus.OK);
       }else{
           return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
    }


}
