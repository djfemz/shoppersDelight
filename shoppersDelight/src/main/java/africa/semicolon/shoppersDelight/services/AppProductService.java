package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.ProductResponse;
import africa.semicolon.shoppersDelight.exceptions.ProductNotFoundException;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppProductService implements ProductService{
    private final ProductRepository productRepository;
    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        Product product = mapper.map(request, Product.class);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, AddProductResponse.class);
    }

    @Override
    public ProductResponse getProductBy(Long id) {
        return mapper.map(findById(id), ProductResponse.class);
    }

    private  Product findById(Long id){
        return productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException(
                        "Product not found"
                ));
    }
}
