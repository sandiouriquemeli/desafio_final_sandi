package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Seller;
import br.com.meli.desafio_final.repository.SellerRepository;
import br.com.meli.desafio_final.util.SellerProductCurrentQuantityDtoUtils;
import br.com.meli.desafio_final.util.SellerProductsDueDateDtoUtils;
import br.com.meli.desafio_final.util.SellerProductsSoldOutDtoUtils;
import br.com.meli.desafio_final.util.SellerUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SellerServiceTest {

    @InjectMocks
    SellerService sellerService;

    @Mock
    SellerRepository sellerRepository;

    @Test
    @DisplayName("Listar todos os vendedores quando uma existem um ou mais.")
    public void find_All_Sellers_When_Existis(){
        BDDMockito.when(sellerRepository.findAll())
                .thenReturn(List.of(SellerUtils.newSeller1ToSave()));

        List<Seller> sellerList = sellerService.findAllSeller();

        assertThat(sellerList).isNotNull();
        assertThat(sellerList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Dispara um NotFound quando vendedor não há vendedores encontrados")
    public void find_All_Sellers_When_Sellers_Not_Exists(){
        assertThrows(NotFound.class, () -> {
            sellerService.findAllSeller();
        });
    }

    @Test
    @DisplayName("Atualiza o nome do vendedor")
    public void update_Seller_When_Exists(){
        Seller seller = SellerUtils.newSeller1ToSave();
        BDDMockito.when(sellerRepository.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(seller);
        BDDMockito.when(sellerRepository.findById(anyLong()))
                .thenReturn(Optional.of(seller));
        seller.setName("Eros");
        Seller updateSeller = sellerService.updateSeller(seller);
        assertThat(updateSeller).isNotNull();
        assertThat(updateSeller.getName()).isEqualTo(seller.getName());
    }

    @Test
    @DisplayName("Dispara um NotFound quando vendedor não há vendedores existe")
    public void throwErrors_Update_Seller_When_Not_Exists() {
        Seller seller = SellerUtils.newSeller1ToSave();
        Exception testException = null;
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            sellerService.updateSeller(seller);
        }catch (Exception exception){
            testException = exception;
        }
        assertThat(testException.getMessage()).isEqualTo("O vendedor informado não foi encontrado");
    }

    @Test
    @DisplayName("Dispara um NotAcceptable quando vendedor está com o id igual a zero")
    public void throwErrors_Update_Seller_When_Id_Equals_zero() {
        Seller seller = SellerUtils.newSeller4ToSave();
        Exception testException = null;
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            sellerService.updateSeller(seller);
        }catch (Exception exception){
            testException = exception;
        }
        assertThat(testException.getMessage()).isEqualTo("O id do vendedor deve ser informado");
    }

    @Test
    @DisplayName("Cria um novo vendedor")
    public void create_savePerson_when_Valid_NewPerson() {
        BDDMockito.when(sellerRepository.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(SellerUtils.newSeller2ToSave());

        Seller seller = SellerUtils.newSeller2ToSave();
        Seller newSeller = sellerService.create(seller);

        assertThat(newSeller).isNotNull();
        assertThat(newSeller.getId()).isPositive();
        assertThat(newSeller.getName()).isEqualTo(newSeller.getName());
    }

    @Test
    @DisplayName("Deleta um vendedor existente")
    public void delete_When_Seller_Exists(){
        BDDMockito.willDoNothing().given(sellerRepository).deleteById(anyLong());
        long idSeller = SellerUtils.newSeller2ToSave().getId();
        sellerRepository.deleteById(idSeller);
        verify(sellerRepository, only()).deleteById(idSeller);
    }

    @Test
    @DisplayName("Apresenta uma erro quando passado vendedor não existe")
    public void delete_TrowException_When_Id_Not_Existis(){
        final long idFalse = 10L;
        assertThrows(NotFound.class, () -> {
            sellerService.delete(idFalse);
        });
        verify(sellerRepository, never()).deleteById(idFalse);
    }

    @Test
    @DisplayName("Verifica se retorna uma lista de produtos de acordo com o id do seller")
    public void get_Currenty_Qantity_Product_By_Adsense_When_Exists(){
        long sellerId = 1l;
        BDDMockito.when(sellerRepository.getCurrentQuantityBYSellerOrderbyQuantityAsc(sellerId))
                .thenReturn(SellerProductCurrentQuantityDtoUtils.sellerProductCurrentQuantityDtoList());
        List<SellerProductCurrentQuantityDto> listSellerProductQuantity = sellerService.getCurrentQuantityBYSellerOrderbyQuantityAsc(sellerId);
        assertThat(listSellerProductQuantity).isNotNull();
    }

    @Test
    public void throw_Currenty_Qantity_Product_By_Adsense_When_Not_Exists(){
        long sellerId = 1l;
        Exception exceptionResponse = null;
        BDDMockito.when(sellerRepository.getCurrentQuantityBYSellerOrderbyQuantityAsc(sellerId))
                .thenReturn(new ArrayList<>());
        try{
            sellerService.getCurrentQuantityBYSellerOrderbyQuantityAsc(sellerId);
        }catch (Exception exception){
            exceptionResponse = exception;
        }
        assertThat(exceptionResponse.getMessage()).isEqualTo("O vendedor não possui anúncio cadastrado");
    }


    @Test
    @DisplayName("Verifica se retorna uma lista de produtos vendidos de acordo com o id do seller")
    public void get_Product_Sold_Out_Dto(){
        long sellerId = 1l;
        BDDMockito.when(sellerRepository.getlistProductsSoldOutBySeller(sellerId))
                .thenReturn(SellerProductsSoldOutDtoUtils.sellerProductsSoldOutDtoList());
        List<SellerProductsSoldOutDto> listSeller = sellerService.getlistProductsSoldOutBySeller(sellerId);
        assertThat(listSeller).isNotNull();
    }

    @Test
    public void throw_listProducts_SoldOut_BySeller_When_Not_Exists(){
        long sellerId = 1l;
        Exception exceptionResponse = null;
        BDDMockito.when(sellerRepository.getlistProductsSoldOutBySeller(sellerId))
                .thenReturn(new ArrayList<>());
        try{
            sellerService.getlistProductsSoldOutBySeller(sellerId);
        }catch (Exception exception){
            exceptionResponse = exception;
        }
        assertThat(exceptionResponse.getMessage()).isEqualTo("O vendedor não possui vendas");
    }

    @Test
    @DisplayName("Verifica se retorna uma lista de produtos com a data de vencimento de acordo com o id do seller")
    public void get_listProductsDueDate(){
        long sellerId = 1l;
        BDDMockito.when(sellerRepository.getlistProductsDueDate(sellerId))
                .thenReturn(SellerProductsDueDateDtoUtils.sellerProductsDueDateDtoList());
        List<SellerProductsDueDateDto> listSeller = sellerService.getlistProductsDueDate(sellerId);
        assertThat(listSeller).isNotNull();
    }

    @Test
    public void throw_Products_DueDate_When_Not_Exists(){
        long sellerId = 1l;
        Exception exceptionResponse = null;
        BDDMockito.when(sellerRepository.getlistProductsDueDate(sellerId))
                .thenReturn(new ArrayList<>());
        try{
            sellerService.getlistProductsDueDate(sellerId);
        }catch (Exception exception){
            exceptionResponse = exception;
        }
        assertThat(exceptionResponse.getMessage()).isEqualTo("O vendedor não possui produtos cadastrados");
    }

}
