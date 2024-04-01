package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.*;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.ProductNotFoundException;
import africa.semicolon.shoppersDelight.models.Category;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.models.Store;
import africa.semicolon.shoppersDelight.repositories.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.math.BigInteger.ONE;
import static java.util.Arrays.stream;


@Slf4j
@Service
@AllArgsConstructor
public class ShoppersProductService implements ProductService{
    private final ProductRepository productRepository;
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final ModelMapper mapper = new ModelMapper();


    @Override
    public AddProductResponse addProduct(AddProductRequest request, Store store) throws Exception {
        Category category = categoryCheck(request.getCategory());
        Product product = mapper.map(request, Product.class);
        product.setStore(store);
        product.setProductCategory(category);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, AddProductResponse.class);
    }

    @Override
    public ProductResponse getProductBy(Long id) {
        return mapper.map(findById(id), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getProducts(int page, int size) {
        Page<Product> productPage = productRepository.findAll(createPageRequest(page, size));
        log.info("page--->{}", productPage);
        return productPage.getContent()
                .stream()
                .map(product -> mapper.map(product,ProductResponse.class))
                .toList();
    }

    private static Pageable createPageRequest(int page, int size) {
        if (page < DEFAULT_PAGE_NUMBER) page = DEFAULT_PAGE_NUMBER;
        if (size < ONE.intValue()) size = DEFAULT_PAGE_SIZE;
        page = page - ONE.intValue();
        return PageRequest.of(page, size);
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(
                        "Product not found"
                )));
    }


    private Category categoryCheck(String category) throws Exception {
        for(Category cate : Category.values()){
            if (cate.name().equalsIgnoreCase(category)){
                return cate;
            }
        }
        throw new Exception("Category Not Found");
    }


    public UpdateProductResponse updateProduct(UpdateProductRequest request) throws Exception {
        Product product = findProductById(request.getProductId());


        List<JsonPatchOperation> jsonPatchOperations = new ArrayList<>();

        buildPatchOperations(request, jsonPatchOperations);
        product = applyPatch(jsonPatchOperations, product);

        product = productRepository.save(product);
        UpdateProductResponse response = buildUpdateProductResponse();
        response.setId(product.getId());
       return response;
    }

    private UpdateProductResponse buildUpdateProductResponse() {
            UpdateProductResponse response = new UpdateProductResponse();
            response.setMessage("Product updated successfully");
            return response;

    }


    private void buildPatchOperations(UpdateProductRequest request, List<JsonPatchOperation> jsonPatchOperations) {
        Class<?> c = request.getClass();
        Field[] fields = c.getDeclaredFields();
        stream(fields)
                .filter(field->isValidUpdate(field, request))
                .forEach(field->addOperation(request, field, jsonPatchOperations));
    }

    private boolean isValidUpdate(Field field, UpdateProductRequest request) {
        System.out.println(field);
        field.setAccessible(true);
        try {
            return field.get(request) != null &&
                    !(field.getName().equalsIgnoreCase("storeId")
        ||field.getName().equalsIgnoreCase("productId"));

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void addOperation(UpdateProductRequest request, Field field, List<JsonPatchOperation> jsonPatchOperations) {
        try {
            JsonPointer path = new JsonPointer("/"+ field.getName());
            JsonNode value = new TextNode(field.get(request).toString());
            ReplaceOperation replaceOperation = new ReplaceOperation(path, value);
            jsonPatchOperations.add(replaceOperation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Product applyPatch(List<JsonPatchOperation> jsonPatchOperations, Product product) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonPatch jsonPatch = new JsonPatch(jsonPatchOperations);
            JsonNode customerNode = mapper.convertValue(product, JsonNode.class);
            JsonNode updatedNode = jsonPatch.apply(customerNode);
            product = mapper.convertValue(updatedNode, Product.class);

        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return product;
    }

    private Product findProductById(Long id) throws CustomerNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Product with id %d not found", id)));
    }
}
