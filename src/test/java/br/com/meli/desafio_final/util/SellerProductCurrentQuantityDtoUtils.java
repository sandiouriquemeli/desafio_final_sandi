package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class SellerProductCurrentQuantityDtoUtils {

    public static List<SellerProductCurrentQuantityDto> sellerProductCurrentQuantityDtoList(){
        List<SellerProductCurrentQuantityDto> list = new ArrayList<>();
        list.add(new SellerProductCurrentQuantityDtoImp(1L, 10, 1L));
        list.add(new SellerProductCurrentQuantityDtoImp(2L, 10, 1L));
        list.add(new SellerProductCurrentQuantityDtoImp(3L, 10, 2L));
        list.add(new SellerProductCurrentQuantityDtoImp(4L, 10, 2L));
        return list;
    }
}

@AllArgsConstructor
class SellerProductCurrentQuantityDtoImp implements SellerProductCurrentQuantityDto {

    private Long adsense_id;
    private Integer current_quantity;
    private Long product_id;

    @Override
    public Long getAdsense_id() {
        return null;
    }

    @Override
    public Integer getCurrent_quantity() {
        return null;
    }

    @Override
    public Long getProduct_id() {
        return null;
    }
}
