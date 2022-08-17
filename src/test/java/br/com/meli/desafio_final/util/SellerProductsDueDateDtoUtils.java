package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerProductsDueDateDtoUtils {
    public static List<SellerProductsDueDateDto> sellerProductsDueDateDtoList(){
        LocalDate date = LocalDate.now();
        List<SellerProductsDueDateDto> list = new ArrayList<>();
        list.add(new SellerProductsDueDateDtoImp(1L, date, 10));
        list.add(new SellerProductsDueDateDtoImp(2L, date, 10));
        list.add(new SellerProductsDueDateDtoImp(3L, date, 10));
        return list;
    }
}

@AllArgsConstructor
class SellerProductsDueDateDtoImp implements SellerProductsDueDateDto {

    private Long adsense_id;
    private LocalDate due_date;
    private Integer days_for_due;


    @Override
    public Long getAdsense_id() {
        return null;
    }

    @Override
    public Date getDue_date() {
        return null;
    }

    @Override
    public Integer getDays_for_due() {
        return null;
    }
}
