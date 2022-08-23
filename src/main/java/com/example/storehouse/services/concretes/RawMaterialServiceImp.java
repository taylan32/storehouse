package com.example.storehouse.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.BusinessException;
import com.example.storehouse.exceptions.NotFoundException;
import com.example.storehouse.models.RawMaterial;
import com.example.storehouse.repositories.RawMaterialRepository;
import com.example.storehouse.requests.CreateRawMaterialRequest;
import com.example.storehouse.requests.UpdateRawMatetialRequest;
import com.example.storehouse.responses.RawMaterialResponse;
import com.example.storehouse.services.abstracts.RawMaterialService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;

@Service
public class RawMaterialServiceImp implements RawMaterialService {

	private RawMaterialRepository rawMaterialRepository;

	@Autowired
	public RawMaterialServiceImp(RawMaterialRepository rawMaterialRepository) {
		this.rawMaterialRepository = rawMaterialRepository;
	}

	@Override
	public Result add(CreateRawMaterialRequest rawMaterialRequest) {
		if (this.rawMaterialRepository.existsByName(rawMaterialRequest.getName())) {
			throw new BusinessException(Messages.rawMaterialExists);
		}
		RawMaterial rawMaterial = new RawMaterial();
		rawMaterial.setName(rawMaterialRequest.getName());
		rawMaterial.setAmount(rawMaterialRequest.getAmount());
		this.rawMaterialRepository.save(rawMaterial);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result update(Long id, UpdateRawMatetialRequest rawMatetialRequest) {
		if (!this.rawMaterialRepository.existsById(id)) {
			throw new NotFoundException(Messages.rawMaterialNotFound);
		}
		RawMaterial rawMaterial = this.rawMaterialRepository.getById(id);
		rawMaterial.setAmount(rawMatetialRequest.getAmount());
		this.rawMaterialRepository.save(rawMaterial);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result delete(Long id) {
		if (!this.rawMaterialRepository.existsById(id)) {
			throw new NotFoundException(Messages.rawMaterialNotFound);
		}
		this.rawMaterialRepository.delete(this.rawMaterialRepository.getById(id));
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<RawMaterialResponse>> getAll() {
		List<RawMaterial> materials = this.rawMaterialRepository.findAll();
		return new SuccessDataResult<List<RawMaterialResponse>>(
				materials.stream().map(m -> new RawMaterialResponse(m)).collect(Collectors.toList()), Messages.listed);
	}

	@Override
	public DataResult<RawMaterialResponse> getById(Long id) {
		RawMaterial rawMaterial = this.rawMaterialRepository.getById(id);
		if (rawMaterial == null) {
			throw new NotFoundException(Messages.rawMaterialNotFound);
		}
		return new SuccessDataResult<RawMaterialResponse>(new RawMaterialResponse(rawMaterial), Messages.listed);
	}

}
