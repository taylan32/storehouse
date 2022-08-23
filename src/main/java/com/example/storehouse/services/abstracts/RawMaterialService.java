package com.example.storehouse.services.abstracts;

import java.util.List;

import com.example.storehouse.requests.CreateRawMaterialRequest;
import com.example.storehouse.requests.UpdateRawMatetialRequest;
import com.example.storehouse.responses.RawMaterialResponse;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface RawMaterialService {

	Result add(CreateRawMaterialRequest rawMaterialRequest);

	Result update(Long id, UpdateRawMatetialRequest rawMatetialRequest);

	Result delete(Long id);

	DataResult<List<RawMaterialResponse>> getAll();

	DataResult<RawMaterialResponse> getById(Long id);

}
