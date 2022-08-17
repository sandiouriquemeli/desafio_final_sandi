package br.com.meli.desafio_final.dto;

import java.util.Date;

public interface SellerProductsDueDateDto {
    Long getAdsense_id();
    Date getDue_date();
    Integer getDays_for_due();
}
