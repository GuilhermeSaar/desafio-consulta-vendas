package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projection.ReportMinProjection;
import com.devsuperior.dsmeta.projection.SummaryMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {


    //SQL  BUSCA CUSTOMIZADA (RELATORIO DE VENDAS)
    // Consulta feita com ajuda do ChatGPT devido a um erro de paginacao com o objeto Page e consultas SQL
    @Query(nativeQuery = true, value = """
        SELECT s.id, s.date, s.amount, se.name 
        FROM tb_sales s 
        JOIN tb_seller se ON s.seller_id = se.id 
        WHERE s.date BETWEEN :startDate AND :endDate
        AND UPPER(se.name) LIKE CONCAT('%', UPPER(:name), '%')
          
    """, // solucao proposta pelo GPT
            countQuery = """
        SELECT COUNT(*) 
        FROM tb_sales s 
        JOIN tb_seller se ON s.seller_id = se.id 
        WHERE s.date BETWEEN :startDate AND :endDate
        AND UPPER(se.name) LIKE CONCAT('%', UPPER(:name), '%')
    """)
    Page<ReportMinProjection> reportSearch(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("name") String name,
            Pageable pageable);



    //SQL  BUSCA CUSTOMIZADA (RELATORIO DE VENDAS)
    @Query(nativeQuery = true, value = "SELECT se.name AS sellerName, SUM(sa.amount) AS total " +
            "FROM tb_sales sa " +
            "INNER JOIN tb_seller se ON sa.seller_id = se.id " +
            "WHERE sa.date BETWEEN :startDate AND :endDate " +
            "GROUP BY se.name ")
    List<SummaryMinProjection> summarySearch(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}






