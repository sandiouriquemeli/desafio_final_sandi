package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import br.com.meli.desafio_final.model.entity.Seller;
import br.com.meli.desafio_final.service.implementation.SellerService;
import br.com.meli.desafio_final.util.SellerProductCurrentQuantityDtoUtils;
import br.com.meli.desafio_final.util.SellerProductsDueDateDtoUtils;
import br.com.meli.desafio_final.util.SellerProductsSoldOutDtoUtils;
import br.com.meli.desafio_final.util.SellerUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.regex.Matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SellerControllerTest {

    @InjectMocks
    private SellerController  sellerController;

    @Mock
    private SellerService sellerService;
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verifica se ouve retorno da lista de vendedores")
    void find_All_When_Exists_Sellers(){
        BDDMockito.when(sellerService.findAllSeller())
                .thenReturn(SellerUtils.sellerList());
        Seller seller = SellerUtils.newSeller1ToSave();
        ResponseEntity<List<Seller>> response = sellerController.getAllSeller();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Verifica se cadastra um novo vendedor")
    void create_Seller_When_Body_Is_Valid(){
        Seller newSeller = SellerUtils.newSeller1ToSave();
        ResponseEntity<Seller> sellerResponse = sellerController.createSeller(newSeller);

        assertThat(sellerResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Atualiza o cadastra um vendedor existente")
    void update_Seller_When_Exists(){
        Seller newSeller = SellerUtils.newSeller2ToSave();
        ResponseEntity<Seller> sellerResponse = sellerController.updateSeller(newSeller);

        assertThat(sellerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Deleta o cadastra um vendedor existente")
    void delete_Seller_When_Exists(){
        Long seller_Id = SellerUtils.newSeller2ToSave().getId();
        ResponseEntity<Void> sellerResponse = sellerController.deleteSeller(seller_Id);

        assertThat(sellerResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Retrona uma lista de produtos com a quantidade corrente filtrados pelo ID do vendedor")
    void getSellerProductCurrentQuantityDto_When_Exists(){
        Long seller_Id = SellerUtils.newSeller2ToSave().getId();
        List<SellerProductCurrentQuantityDto> sellerList = SellerProductCurrentQuantityDtoUtils.sellerProductCurrentQuantityDtoList();
        BDDMockito.when(sellerService.getCurrentQuantityBYSellerOrderbyQuantityAsc(anyLong()))
                .thenReturn(sellerList);
        ResponseEntity<List<SellerProductCurrentQuantityDto>> sellerResponse = sellerController.getProductCurrentQuantityBySeller(seller_Id);
        assertThat(sellerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Retrona uma lista de produtos vendidos filtrados pelo ID do vendedor")
    void getSellerProducts_Sold_Out_When_Exists(){
        Long seller_Id = SellerUtils.newSeller2ToSave().getId();
        List<SellerProductsSoldOutDto> sellerList = SellerProductsSoldOutDtoUtils.sellerProductsSoldOutDtoList();
        BDDMockito.when(sellerService.getlistProductsSoldOutBySeller(anyLong()))
                .thenReturn(sellerList);
        ResponseEntity<List<SellerProductsSoldOutDto>> sellerResponse = sellerController.getSellerProductsSoldOut(seller_Id);
        assertThat(sellerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Retrona uma lista de produtos armazenados com a data de validade filtrados pelo ID do vendedor")
    void getSellerProductsDueDate_When_Exists(){
        Long seller_Id = SellerUtils.newSeller2ToSave().getId();
        List<SellerProductsDueDateDto> sellerList = SellerProductsDueDateDtoUtils.sellerProductsDueDateDtoList();
        BDDMockito.when(sellerService.getlistProductsDueDate(anyLong()))
                .thenReturn(sellerList);
        ResponseEntity<List<SellerProductsDueDateDto>> sellerResponse = sellerController.getSellerProductsDueDate(seller_Id);
        assertThat(sellerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
