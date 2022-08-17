package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import br.com.meli.desafio_final.model.entity.Seller;

import java.util.List;

public interface ISellerService {

    Seller create(Seller seller);
    List<Seller> findAllSeller();
    Seller updateSeller(Seller seller);
    void delete(Long id);
    List<SellerProductCurrentQuantityDto> getCurrentQuantityBYSellerOrderbyQuantityAsc(Long seller_id);
    List<SellerProductsSoldOutDto> getlistProductsSoldOutBySeller(Long seller_id);
    List<SellerProductsDueDateDto> getlistProductsDueDate(Long seller_id);

}
