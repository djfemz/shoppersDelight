package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppProductService implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(request, Product.class);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, AddProductResponse.class);
    }
}
