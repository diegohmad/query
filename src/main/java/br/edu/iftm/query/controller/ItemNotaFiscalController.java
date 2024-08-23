package br.edu.iftm.query.controller;

import br.edu.iftm.query.model.ItemNotaFiscal;
import br.edu.iftm.query.service.ItemNotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemNotaFiscalController {

    @Autowired
    private ItemNotaFiscalService service;

    @GetMapping("/sem-desconto")
    public List<ItemNotaFiscal> getItensSemDesconto() {
        return service.getItensSemDesconto();
    }

    @GetMapping("/com-desconto")
    public List<ItemNotaFiscal> getItensComDesconto() {
        return service.getItensComDesconto();
    }

    @GetMapping("/ordenados-por-valor")
    public List<ItemNotaFiscal> getAllItensOrderByValorUnitDesc() {
        return service.getAllItensOrderByValorUnitDesc();
    }

    @GetMapping("/produto-mais-vendido")
    public Integer getProdutoMaisVendido() {
        return service.getProdutoMaisVendido();
    }

    @GetMapping("/nfs-mais-de-10-unidades")
    public List<Integer> getNfWithMoreThan10Units() {
        return service.getNfWithMoreThan10Units();
    }

    @GetMapping("/nfs-valor-maior-500")
    public List<Object[]> getNfWithTotalValueGreaterThan500() {
        return service.getNfWithTotalValueGreaterThan500();
    }
}
