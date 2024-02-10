package com.krow.ApiKrowFotografia.web.controller;

import com.krow.ApiKrowFotografia.domain.Purchase;
import com.krow.ApiKrowFotografia.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/Client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") int clientId){
        return ResponseEntity.of(purchaseService.getByClient(clientId)); //para obtener un tipo optional
        /*return purchaseService.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases,HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }
}
