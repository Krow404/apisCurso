package com.krow.ApiKrowFotografia.persistence;

import com.krow.ApiKrowFotografia.domain.Product;
import com.krow.ApiKrowFotografia.domain.repository.ProductRepository;
import com.krow.ApiKrowFotografia.persistence.crud.ProductoCrudRepository;
import com.krow.ApiKrowFotografia.persistence.entity.Producto;
import com.krow.ApiKrowFotografia.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ProductRepository queda enfocado al dominio en lugar de una tabla frontal
@Repository //le indicamos a la clase que es la encargada de interactuar con la BD
public class ProductoRepository implements ProductRepository { //
    @Autowired //solo componentes de Spring para que no quden en null
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(Integer categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockIsLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods)); //mapear un opcional
    }

    @Override
    public Optional<Product> getProduct(Integer productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(Integer productId) {
        productoCrudRepository.deleteById(productId);
    }

}
