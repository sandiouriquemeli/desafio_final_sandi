package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.dto.SellerProductCurrentQuantityDto;
import br.com.meli.desafio_final.dto.SellerProductsDueDateDto;
import br.com.meli.desafio_final.dto.SellerProductsSoldOutDto;
import br.com.meli.desafio_final.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    /**
     * Query para pesquisar os produtos com suas respectivas quantidades correntes
     * @param seller_id
     * @return lista de produtos com suas respectivas quantidades em estoque
     */

    @Query(value = "SELECT\n" +
            " adsense_id, \n" +
            " current_quantity, \n" +
            " frescos.adsense.product_id\n" +
            " FROM batch\n" +
            " JOIN frescos.adsense as adsense\n" +
            " WHERE frescos.batch.adsense_id = frescos.adsense.id\n" +
            " AND frescos.adsense.seller_id = ?1\n" +
            " ORDER BY current_quantity ASC", nativeQuery = true)
    List<SellerProductCurrentQuantityDto> getCurrentQuantityBYSellerOrderbyQuantityAsc(Long seller_id);

    /**
     * Query para pesquisar as vendas finalizadas do vendedor
     * @param seller_id
     * @return lista de vendas finalizadas
     */

    @Query(value = "SELECT\n" +
            " frescos.item.adsense_id, \n" +
            " sum(frescos.item.quantity) AS quantity_total \n" +
            " FROM frescos.item\n" +
            " JOIN frescos.purchase_order\n" +
            " JOIN frescos.adsense\n" +
            " WHERE frescos.item.purchase_order_id = frescos.purchase_order.id\n" +
            " AND frescos.purchase_order.status = 'FINISHED' \n" +
            " AND frescos.adsense.id = frescos.item.adsense_id \n"+
            " AND frescos.adsense.seller_id = ?1\n" +
            " GROUP BY item.adsense_id", nativeQuery = true)
    List<SellerProductsSoldOutDto> getlistProductsSoldOutBySeller(Long seller_id);

    /**
     * Query para pesquisar os produtos do vendedor com suas respectivas validade
     * @param seller_id
     * @return lista de produtos com suas as validades
     */
    @Query(value = "SELECT\n" +
            " frescos.batch.adsense_id, \n" +
            " frescos.batch.due_date,\n" +
            "(frescos.batch.due_date - CURRENT_DATE ()) AS days_for_due \n"+
            " FROM frescos.batch\n" +
            " JOIN frescos.adsense\n" +
            " WHERE frescos.batch.adsense_id = frescos.adsense.id \n" +
            " AND frescos.adsense.seller_id = ?1\n" +
            " ORDER BY frescos.batch.due_date ASC", nativeQuery = true)
    List<SellerProductsDueDateDto> getlistProductsDueDate(Long seller_id);

}