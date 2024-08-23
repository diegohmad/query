package br.edu.iftm.query.service;

import br.edu.iftm.query.exception.ItemNotFoundException;
import br.edu.iftm.query.model.ItemNotaFiscal;
import br.edu.iftm.query.repository.ItemNotaFiscalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemNotaFiscalServiceTest {

    @InjectMocks
    private ItemNotaFiscalService service;

    @Mock
    private ItemNotaFiscalRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetItensSemDescontoWhenItemsExist() {
        ItemNotaFiscal item = new ItemNotaFiscal();
        item.setDesconto(0);
        when(repository.findItemsWithNoDiscount()).thenReturn(List.of(item));
        
        List<ItemNotaFiscal> result = service.getItensSemDesconto();
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getDesconto());
    }

    @Test
    public void testGetItensSemDescontoWhenNoItemsExist() {
        when(repository.findItemsWithNoDiscount()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNotFoundException.class, () -> {
            service.getItensSemDesconto();
        });
    }

    @Test
    public void testGetItensComDescontoWhenItemsExist() {
        ItemNotaFiscal item = new ItemNotaFiscal();
        item.setDesconto(10);
        when(repository.findItemsWithDiscount()).thenReturn(List.of(item));
        
        List<ItemNotaFiscal> result = service.getItensComDesconto();
        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getDesconto());
    }

    @Test
    public void testGetItensComDescontoWhenNoItemsExist() {
        when(repository.findItemsWithDiscount()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNotFoundException.class, () -> {
            service.getItensComDesconto();
        });
    }

    @Test
    public void testGetAllItensOrderByValorUnitDesc() {
        ItemNotaFiscal item = new ItemNotaFiscal();
        item.setValorUnit(100.00);
        when(repository.findAllOrderedByValueDesc()).thenReturn(List.of(item));
        
        List<ItemNotaFiscal> result = service.getAllItensOrderByValorUnitDesc();
        assertEquals(1, result.size());
        assertEquals(100.00, result.get(0).getValorUnit());
    }

    @Test
    public void testGetProdutoMaisVendidoWhenProductExists() {
        when(repository.findTopByOrderByQuantidadeDesc()).thenReturn(1);
        
        Integer result = service.getProdutoMaisVendido();
        assertEquals(1, result);
    }

    @Test
    public void testGetProdutoMaisVendidoWhenNoProductExists() {
        when(repository.findTopByOrderByQuantidadeDesc()).thenReturn(null);
        
        assertThrows(ItemNotFoundException.class, () -> {
            service.getProdutoMaisVendido();
        });
    }

    @Test
    public void testGetNfWithMoreThan10UnitsWhenNfExists() {
        when(repository.findNfWithMoreThan10Units()).thenReturn(List.of(1));
        
        List<Integer> result = service.getNfWithMoreThan10Units();
        assertEquals(1, result.size());
        assertEquals(1, result.get(0));
    }

    @Test
    public void testGetNfWithMoreThan10UnitsWhenNoNfExists() {
        when(repository.findNfWithMoreThan10Units()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNotFoundException.class, () -> {
            service.getNfWithMoreThan10Units();
        });
    }

    @Test
    public void testGetNfWithTotalValueGreaterThan500WhenNoNfExists() {
        when(repository.findNfWithTotalValueGreaterThan500()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNotFoundException.class, () -> {
            service.getNfWithTotalValueGreaterThan500();
        });
    }
}
