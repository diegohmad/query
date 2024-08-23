package br.edu.iftm.query.controller;

import br.edu.iftm.query.model.ItemNotaFiscal;
import br.edu.iftm.query.service.ItemNotaFiscalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@Tag(name = "ItemNotaFiscal", description = "API para consulta de itens de nota fiscal")
public class ItemNotaFiscalController {

    @Autowired
    private ItemNotaFiscalService service;

    @GetMapping("/sem-desconto")
    @Operation(summary = "Retorna todos os itens sem desconto", description = "Retorna todos os itens de nota fiscal que não possuem desconto", tags = {"ItemNotaFiscal"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public List<ItemNotaFiscal> getItensSemDesconto() {
        return service.getItensSemDesconto();
    }

    @GetMapping("/com-desconto")
    @Operation(summary = "Retorna todos os itens com desconto", description = "Retorna todos os itens de nota fiscal que possuem desconto", tags = {"ItemNotaFiscal"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public List<ItemNotaFiscal> getItensComDesconto() {
        return service.getItensComDesconto();
    }

    @GetMapping("/ordenados-por-valor")
    @Operation(summary = "Retorna todos os itens ordenados por valor unitário", description = "Retorna todos os itens de nota fiscal ordenados por valor unitário", tags = {"ItemNotaFiscal"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public List<ItemNotaFiscal> getAllItensOrderByValorUnitDesc() {
        return service.getAllItensOrderByValorUnitDesc();
    }

    @GetMapping("/produto-mais-vendido")
    @Operation(summary = "Retorna o produto mais vendido", description = "Retorna o código do produto mais vendido", tags = {"ItemNotaFiscal"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Produto retornado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    public Integer getProdutoMaisVendido() {
        return service.getProdutoMaisVendido();
    }

    @GetMapping("/nfs-mais-de-10-unidades")
    @Operation(summary = "Retorna as notas fiscais com mais de 10 unidades", description = "Retorna os códigos das notas fiscais que possuem mais de 10 unidades", tags = {"ItemNotaFiscal"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Notas fiscais retornadas com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma nota fiscal encontrada")
    })
    public List<Integer> getNfWithMoreThan10Units() {
        return service.getNfWithMoreThan10Units();
    }

    @GetMapping("/nfs-valor-maior-500")
    @Operation(summary = "Retorna as notas fiscais com valor total maior que 500", description = "Retorna os códigos das notas fiscais que possuem valor total maior que 500", tags = {"ItemNotaFiscal"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Notas fiscais retornadas com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma nota fiscal encontrada")
    })
    public List<Object[]> getNfWithTotalValueGreaterThan500() {
        return service.getNfWithTotalValueGreaterThan500();
    }
}
