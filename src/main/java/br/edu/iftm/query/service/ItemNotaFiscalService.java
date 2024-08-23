package br.edu.iftm.query.service;

import br.edu.iftm.query.exception.ItemNotFoundException;
import br.edu.iftm.query.model.ItemNotaFiscal;
import br.edu.iftm.query.repository.ItemNotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemNotaFiscalService {

    @Autowired
    private ItemNotaFiscalRepository repository;

    public List<ItemNotaFiscal> getItensSemDesconto() {
        List<ItemNotaFiscal> items = repository.findItemsWithNoDiscount();
        if (items.isEmpty()) {
            throw new ItemNotFoundException("No items found with no discount.");
        }
        return items;
    }

    public List<ItemNotaFiscal> getItensComDesconto() {
        List<ItemNotaFiscal> items = repository.findItemsWithDiscount();
        if (items.isEmpty()) {
            throw new ItemNotFoundException("No items found with discount.");
        }
        return items;
    }

    public List<ItemNotaFiscal> getAllItensOrderByValorUnitDesc() {
        return repository.findAllOrderedByValueDesc();
    }

    public Integer getProdutoMaisVendido() {
        Integer produto = repository.findTopByOrderByQuantidadeDesc();
        if (produto == null) {
            throw new ItemNotFoundException("No product found with the highest sales.");
        }
        return produto;
    }

    public List<Integer> getNfWithMoreThan10Units() {
        List<Integer> nfIds = repository.findNfWithMoreThan10Units();
        if (nfIds.isEmpty()) {
            throw new ItemNotFoundException("No invoices found with more than 10 units of any product.");
        }
        return nfIds;
    }

    public List<Object[]> getNfWithTotalValueGreaterThan500() {
        List<Object[]> nfTotals = repository.findNfWithTotalValueGreaterThan500();
        if (nfTotals.isEmpty()) {
            throw new ItemNotFoundException("No invoices found with a total value greater than 500.");
        }
        return nfTotals;
    }
}
