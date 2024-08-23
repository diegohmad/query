package br.edu.iftm.query.repository;

import br.edu.iftm.query.model.ItemNotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Long> {

    // a) Retorna todos os itens que foram vendidos sem desconto.
    @Query("SELECT i FROM ItemNotaFiscal i WHERE i.desconto = 0")
    List<ItemNotaFiscal> findItemsWithNoDiscount();

    // b) Retorna todos os itens que foram vendidos com desconto.
    @Query("SELECT i FROM ItemNotaFiscal i WHERE i.desconto > 0")
    List<ItemNotaFiscal> findItemsWithDiscount();

    // c) Retorna todos os itens e ordene o resultado do maior valor para o menor.
    @Query("SELECT i FROM ItemNotaFiscal i ORDER BY i.valorUnit DESC")
    List<ItemNotaFiscal> findAllOrderedByValueDesc();

    // d) Retorna o produto que mais vendeu.
    @Query("SELECT codProd FROM ItemNotaFiscal GROUP BY codProd ORDER BY SUM(quantidade) DESC LIMIT 1")
    Integer findTopByOrderByQuantidadeDesc();

    // e) Consulte as NF que foram vendidas mais de 10 unidades de pelo menos um
    // produto.
    @Query("SELECT DISTINCT i.idNf FROM ItemNotaFiscal i WHERE i.quantidade > 10")
    List<Integer> findNfWithMoreThan10Units();

    // f) Pesquise o valor total das NF, onde esse valor seja maior que 500, e
    // ordene o resultado do maior valor para o menor.
    @Query("SELECT i.idNf, ROUND(SUM(i.valorUnit * i.quantidade * (1 - i.desconto / 100.0)), 2) AS total " +
            "FROM ItemNotaFiscal i " +
            "GROUP BY i.idNf " +
            "HAVING ROUND(SUM(i.valorUnit * i.quantidade * (1 - i.desconto / 100.0)), 2) > 500 " +
            "ORDER BY total DESC")
    List<Object[]> findNfWithTotalValueGreaterThan500();

}
