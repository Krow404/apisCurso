package com.krow.ApiKrowFotografia.persistence.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.krow.ApiKrowFotografia.domain.PurchaseItem;
import com.krow.ApiKrowFotografia.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {ProductMapper.class}) //indicar que estas usando la clase aunque sea para ignorarlo
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad",target = "quantity"),
            //@Mapping(source = "total",target = "total"), //cuando sean el mismo nombre no es necesario mapearlo
            @Mapping(source = "estado",target = "active"),
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({ //ignorar propiedades que no quiero
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
