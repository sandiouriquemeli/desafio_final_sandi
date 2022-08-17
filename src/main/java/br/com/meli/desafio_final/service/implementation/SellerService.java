package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.NotAcceptable;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Seller;
import br.com.meli.desafio_final.repository.SellerRepository;
import br.com.meli.desafio_final.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService implements ISellerService {

    @Autowired
    SellerRepository sellerRepository;

    /**
     * Método para criar um vendedor
     * @param seller
     * @return seller criado
     */
    @Override
    public Seller create(Seller seller) {
        if(seller.getId() == null){
                throw new BadRequest("O vendedor não pode ter Id definido");
        }
        return sellerRepository.save(seller);
    }


    /**
     * Método para encontrar todos os vendedores cadastrados
     * @param
     * @return uma lista de vededores
     */
    @Override
    public List<Seller> findAllSeller() {
        List<Seller> sellers = sellerRepository.findAll();
        if (sellers.size() == 0) throw new NotFound("Lista de vendedor não encontrada");
        return sellers;
    }

    /**
     * Atualiza um vendedor existente
     * @param seller
     * @return vendedor
     */
    @Override
    public Seller updateSeller(Seller seller) {
        if(seller.getId() == 0) {
            throw new NotAcceptable("O id do vendedor deve ser informado");
        }
        Optional<Seller> sellerFound = sellerRepository.findById(seller.getId());
        if(sellerFound.isEmpty()){
            throw new NotFound("O vendedor informado não foi encontrado");
        }
        return sellerRepository.save(seller);
    }

    /**
     * Método que deleta um vendedor existente
     * @param id
     */
    @Override
    public void delete(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if(seller.isEmpty()){
            throw  new NotFound("O vendedor não encontrado");
        }
        sellerRepository.deleteById(id);
    }

    /**
     * Método que recebe um id, salva o resultado da pesquisa do método getCurrentQuantityBYSellerOrderbyQuantityAsc
     * em uma variável e verifica se ela é a lista está vazia. Se for vazia lança uma throw exception.
     * @param seller_id
     * @return uma lista de SellerProductCurrentQuantityDto
     */
    @Override
    public List<SellerProductCurrentQuantityDto> getCurrentQuantityBYSellerOrderbyQuantityAsc(Long seller_id) {
        List<SellerProductCurrentQuantityDto> sellerProductCurrentQuantityDtoList = sellerRepository.getCurrentQuantityBYSellerOrderbyQuantityAsc(seller_id);
        if(sellerProductCurrentQuantityDtoList.size() == 0){
            throw new NotFound("O vendedor não possui anúncio cadastrado");
        }
        return sellerProductCurrentQuantityDtoList;
    }

    /**
     * Método que recebe um id, salva o resultado da pesquisa do método getlisProductsSoldOutBySeller
     * em uma variável e verifica se ela é a lista está vazia. Se for vazia lança uma throw exception.
     * @param seller_id
     * @return uma lista de SellerProductsSoldOutDto
     */
    @Override
    public List<SellerProductsSoldOutDto> getlistProductsSoldOutBySeller(Long seller_id) {
        List<SellerProductsSoldOutDto> sellerProductsSoldOutDtos = sellerRepository.getlistProductsSoldOutBySeller(seller_id);
        if(sellerProductsSoldOutDtos.size() == 0){
            throw new NotFound("O vendedor não possui vendas");
        }
        return sellerProductsSoldOutDtos;
    }

    /**
     * Método que recebe um id, salva o resultado da pesquisa do método getlistProductsDueDate
     * em uma variável e verifica se ela é a lista está vazia. Se for vazia lança uma throw exception.
     * @param seller_id
     * @return uma lista de SellerProductsDueDateDto
     */
    @Override
    public List<SellerProductsDueDateDto> getlistProductsDueDate(Long seller_id) {
        List<SellerProductsDueDateDto> sellerProductsDueDateList = sellerRepository.getlistProductsDueDate(seller_id);
        if(sellerProductsDueDateList.size() == 0){
            throw new NotFound("O vendedor não possui produtos cadastrados");
        }
        return sellerProductsDueDateList;
    }

}
