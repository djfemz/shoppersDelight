package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.request.StoreRegistrationRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.StoreRegistrationResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateProductResponse;

public interface StoreService {

    AddProductResponse addProduct(AddProductRequest request) throws Exception;


    StoreRegistrationResponse register(StoreRegistrationRequest request);

    UpdateProductResponse updateProduct(UpdateProductRequest request) throws Exception;
}
