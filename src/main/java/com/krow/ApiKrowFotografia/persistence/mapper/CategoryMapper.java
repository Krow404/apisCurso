package com.krow.ApiKrowFotografia.persistence.mapper;

import com.krow.ApiKrowFotografia.domain.Category;
import com.krow.ApiKrowFotografia.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") //es un componente de tipo spring
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria",target = "categoryId"),
            @Mapping(source = "descripcion",target = "category"),
            @Mapping(source = "estado",target = "active"),
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration//conversion de mapeo inverso
    @Mapping(target = "productos",ignore = true)//para ignorar la propiedad productos
    Categoria toCategoria(Category category);
}
