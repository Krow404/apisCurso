package com.krow.ApiKrowFotografia.domain.repository;

import com.krow.ApiKrowFotografia.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(int clientId);
    Purchase save(Purchase purchase);
}
