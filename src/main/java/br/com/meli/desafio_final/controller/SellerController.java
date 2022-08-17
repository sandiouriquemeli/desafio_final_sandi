package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import br.com.meli.desafio_final.service.implementation.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.meli.desafio_final.model.entity.Seller;

import java.util.List;


@RestController
@RequestMapping("/api/v6/seller")
public class SellerController {


    @Autowired
    private SellerService sellerService;

    /**
     * Método GET para buscar vendedores cadastrados
     * @return Lista de vendedores
     */
    @GetMapping()
    public ResponseEntity<List<Seller>> getAllSeller(){
        return ResponseEntity.ok(sellerService.findAllSeller());
    }


    /**
     * Método POST para cadastrar vendedore
     * @return vendedor cadastrado
     */
    @PostMapping()
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller){
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.create(seller));
    }
    /**
     * Método PUT para atualizar vendedore cadastrado
     * @return vendedor cadastrado
     */
    @PutMapping()
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller){
        return ResponseEntity.ok(sellerService.updateSeller(seller));
    }

    /**
     * Método PUT para deletar vendedore cadastrado
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id){
        sellerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método GET para pesquisar os vendedores que possuem produtos cadastrados
     * @return lista de produtos com a quantidade corrente
     */
    @GetMapping("/quantity/{seller_id}")
    public ResponseEntity<List<SellerProductCurrentQuantityDto>> getProductCurrentQuantityBySeller(@PathVariable Long seller_id){
        return ResponseEntity.ok(sellerService.getCurrentQuantityBYSellerOrderbyQuantityAsc(seller_id));
    }
    /**
     * Método GET para pesquisar os vendedores que possuem produtos vendidos
     * @return lista de produtos vendidos
     */
    @GetMapping("/purchaseOrder/finished/{seller_id}")
    public ResponseEntity<List<SellerProductsSoldOutDto>> getSellerProductsSoldOut(@PathVariable Long seller_id){
        return ResponseEntity.ok(sellerService.getlistProductsSoldOutBySeller(seller_id));
    }

    /**
     * Método GET para pesquisar os vendedores que possuem produtos cadastrados no armazem com suas validades
     * @return lista de produtos com a validade
     */
    @GetMapping("/dueDateProducts/{seller_id}")
    public ResponseEntity<List<SellerProductsDueDateDto>> getSellerProductsDueDate(@PathVariable Long seller_id){
        return ResponseEntity.ok(sellerService.getlistProductsDueDate(seller_id));
    }
}
