package com.krow.ApiKrowFotografia.persistence.mapper;

import com.krow.ApiKrowFotografia.domain.Category;
import com.krow.ApiKrowFotografia.domain.Product;
import com.krow.ApiKrowFotografia.persistence.entity.Categoria;
import com.krow.ApiKrowFotografia.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}) //cuando use categoria ya sabe que a usar CategoryMapper
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto",target = "productId"),
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "idCategoria",target = "categoryId"),
            @Mapping(source = "precioVenta",target = "price"),
            @Mapping(source = "cantidadStock",target = "stock"),
            @Mapping(source = "estado",target = "active"),
            @Mapping(source = "categoria",target = "category"),
    })
    Product toProduct(Producto producto);

    @InheritInverseConfiguration//conversion de mapeo inverso
    @Mapping(target = "codigoBarras",ignore = true)//para ignorar la propiedad codigoBarras
    Producto toProducto(Product product);

    List<Product> toProducts(List<Producto> productos);//lista de productos ya sabe que tiene que hacer el mapeo
}
