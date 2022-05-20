package br.com.flare.controller;

import br.com.flare.model.Category;
import br.com.flare.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NotificationControllerIntegrationTest {

    /*
         ESSE TESTE UTILIZA O BANCO DE DADOS DE VERDADE,
         DEIXE O BANCO EXECUTANDO COM DADOS JÁ INSERIDOS
     */

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
        categoryRepository.saveAll(List.of(new Category("ECOS"), new Category("CEM"),
                new Category("Ciência da Computação"),
                new Category("Engenharia"),
                new Category("Arquitetura"),
                new Category("Direito")));
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    public void deveRetornarTodasAsCategoriasOrdenadas() throws Exception {

        List<String> categorias = Stream.of("ECOS", "CEM", "Ciência da Computação",
                        "Engenharia", "Arquitetura", "Direito").sorted().collect(Collectors.toList());

        List<Category> categoriesNameAsc = categoryRepository.findAllByOrderByNameAsc();
        List<String> categories = new ArrayList<>();

        categoriesNameAsc.forEach(category -> categories.add(category.getName()));

        assertEquals(categorias, categories);

    }
}
