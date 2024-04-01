package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.request.StoreRegistrationRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.StoreRegistrationResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateProductResponse;
import africa.semicolon.shoppersDelight.exceptions.StoreNotFoundException;
import africa.semicolon.shoppersDelight.models.Store;
import africa.semicolon.shoppersDelight.repositories.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppersDelightStoreService implements StoreService{
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductService productService;
    ModelMapper mapper = new ModelMapper();

    @Override
    public AddProductResponse addProduct(AddProductRequest request) throws Exception {
        Optional <Store> store = storeRepository.findById(request.getId());
        if(store.isPresent()){
            return  productService.addProduct(request,store.get());
        }
        throw new StoreNotFoundException("Store doesn't exist");
    }


    @Override
    public StoreRegistrationResponse register(StoreRegistrationRequest request) {
        Store store = mapper.map(request,Store.class);
        store = storeRepository.save(store);
        StoreRegistrationResponse response = new StoreRegistrationResponse();
        response.setId(store.getId());
        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest request) throws Exception {
        if (storeRepository.findById(request.StoreId).isPresent()){
            return productService.updateProduct(request);
        }
        throw new Exception("Store does not exist");
    }

}
