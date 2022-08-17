package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerUtils {

    public static Seller newSeller1ToSave() {
        return Seller.builder()
                .id(1L)
                .name("Joao")
                .build();
    }

    public static Seller newSeller2ToSave() {
        return Seller.builder()
                .id(2L)
                .name("Nicolas")
                .build();
    }

    public static Seller newSeller3ToSave() {
        return Seller.builder()
                .id(null)
                .name("Eros")
                .build();
    }

    public static Seller newSeller4ToSave() {
        return Seller.builder()
                .id(0L)
                .name("Nicolas")
                .build();
    }

    public static List<Seller> sellerList(){
        List<Seller> sellerList = new ArrayList<>();
        sellerList.add(newSeller1ToSave());
        sellerList.add(newSeller2ToSave());
        return sellerList;
    }



}
