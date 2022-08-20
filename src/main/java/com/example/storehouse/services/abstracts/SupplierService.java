package com.example.storehouse.services.abstracts;

import java.util.List;

import com.example.storehouse.requests.CreateSupplierRequest;
import com.example.storehouse.requests.UpdateSupplierRequest;
import com.example.storehouse.responses.SupplierResponse;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface SupplierService {

	Result add(CreateSupplierRequest createSupplierRequest);

	Result update(Long id, UpdateSupplierRequest updateSupplierRequest);

	Result delete(Long id);

	DataResult<List<SupplierResponse>> getAll();

	DataResult<SupplierResponse> getById(Long id);

}
