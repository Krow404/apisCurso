package com.krow.ApiKrowFotografia.persistence.crud;

import com.krow.ApiKrowFotografia.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra,Integer> {
    Optional<List<Compra>> findByIdCliente(int clientId);

}
