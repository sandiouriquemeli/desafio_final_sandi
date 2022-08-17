package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class SellerProductsSoldOutDtoUtils {
    public static List<SellerProductsSoldOutDto> sellerProductsSoldOutDtoList(){
        List<SellerProductsSoldOutDto> list = new ArrayList<>();
        list.add(new SellerProductsSoldOutDtoImp(1L, 10L));
        list.add(new SellerProductsSoldOutDtoImp(2L, 10L));
        list.add(new SellerProductsSoldOutDtoImp(3L, 10L));
        list.add(new SellerProductsSoldOutDtoImp(4L, 10L));
        return list;
    }

}


@AllArgsConstructor
class SellerProductsSoldOutDtoImp implements SellerProductsSoldOutDto {

    private Long qdsense_id;

    private Long quantity_total;

    @Override
    public Long getAdsense_id() {
        return null;
    }

    @Override
    public Integer getQuantity_total() {
        return null;
    }
}