package com.krow.ApiKrowFotografia.persistence;

import com.krow.ApiKrowFotografia.domain.Purchase;
import com.krow.ApiKrowFotografia.domain.repository.PurchaseRepository;
import com.krow.ApiKrowFotografia.persistence.crud.CompraCrudRepository;
import com.krow.ApiKrowFotografia.persistence.entity.Compra;
import com.krow.ApiKrowFotografia.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //indicarle a spring que es un bean de spring y un repositorio que se va a comunicar con la BD
public class CompraRepository  implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;
    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(int clientId) {

        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
