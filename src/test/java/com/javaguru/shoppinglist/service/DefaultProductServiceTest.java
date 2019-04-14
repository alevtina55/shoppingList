package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converters.ProductConverter;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ProductConverter converter;

    @Mock
    private ProductValidationService validationService;

    @InjectMocks
    private DefaultProductService victim;

    @Captor
    private ArgumentCaptor<ProductDTO> productDTOCaptor;

    @Test
    public void shouldFindProduct() {
        when(repository.findById(100L)).thenReturn(Optional.ofNullable(product()));
        when(converter.convert(product())).thenReturn(productDTO());


        ProductDTO result = victim.findProductById(100L);

        assertThat(result).isEqualTo(productDTO());
    }

    @Test
    public void shouldCreateProductSuccessfully() {
        Product product = product();
        ProductDTO productDTO = productDTO();
        when(repository.insert(product)).thenReturn(product.getId());
        when(converter.convert(productDTO)).thenReturn(product);

        Long result = victim.create(productDTO);
        verify(validationService).validate(productDTOCaptor.capture());
        ProductDTO captorResult = productDTOCaptor.getValue();

        assertThat(captorResult).isEqualTo(productDTO);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldCalculateActualPrice() {
        BigDecimal result = victim.calculateActualPrice(new BigDecimal(40.00),
                new BigDecimal(50.00));
        assertThat(new BigDecimal(20)).isEqualByComparingTo(result);
    }

    private Product product() {
        Product product = new Product();
        product.setId(100L);

        return product;
    }

    private ProductDTO productDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(100L);
        productDTO.setName("name");
        productDTO.setPrice(new BigDecimal(20));
        productDTO.setDiscount(BigDecimal.ZERO);

        return productDTO;
    }
}