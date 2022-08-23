package com.example.storehouse.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.BusinessException;
import com.example.storehouse.exceptions.NotFoundException;
import com.example.storehouse.models.Supplier;
import com.example.storehouse.repositories.SupplierRepository;
import com.example.storehouse.requests.CreateSupplierRequest;
import com.example.storehouse.requests.UpdateSupplierRequest;
import com.example.storehouse.responses.SupplierResponse;
import com.example.storehouse.services.abstracts.SupplierService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;

@Service
public class SupplierServiceImp implements SupplierService {

	private SupplierRepository supplierRepository;

	@Autowired
	public SupplierServiceImp(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Result add(CreateSupplierRequest createSupplierRequest) {
		if (checkIfSupplierExists(createSupplierRequest.getEmail(), createSupplierRequest.getCompanyName())) {
			throw new BusinessException(Messages.supplierExists);
		}
		if (!checkIfEmailValid(createSupplierRequest.getEmail(), createSupplierRequest.getCompanyName())) {
			throw new BusinessException(Messages.emailInvalid);
		}
		Supplier supplier = new Supplier();
		supplier.setEmail(createSupplierRequest.getEmail());
		supplier.setCompanyName(createSupplierRequest.getCompanyName());
		supplier.setPhoneNumber(createSupplierRequest.getPhoneNumber());
		supplier.setAddress(createSupplierRequest.getAddress());
		this.supplierRepository.save(supplier);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result update(Long id, UpdateSupplierRequest updateSupplierRequest) {
		if (!this.supplierRepository.existsById(id)) {
			throw new NotFoundException(Messages.supplierNotFound);
		}
		Supplier supplier = this.supplierRepository.getById(id);
		supplier.setAddress(updateSupplierRequest.getAddress());
		supplier.setPhoneNumber(updateSupplierRequest.getPhoneNumber());
		this.supplierRepository.save(supplier);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result delete(Long id) {
		if (!this.supplierRepository.existsById(id)) {
			throw new NotFoundException(Messages.supplierNotFound);
		}
		this.supplierRepository.delete(this.supplierRepository.getById(id));
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<SupplierResponse>> getAll() {
		List<Supplier> suppliers = this.supplierRepository.findAll();
		return new SuccessDataResult<List<SupplierResponse>>(
				suppliers.stream().map(supplier -> new SupplierResponse(supplier)).collect(Collectors.toList()),
				Messages.listed);
	}

	@Override
	public DataResult<SupplierResponse> getById(Long id) {
		Supplier supplier = this.supplierRepository.getById(id);
		if (supplier == null) {
			throw new NotFoundException(Messages.supplierNotFound);
		}
		return new SuccessDataResult<SupplierResponse>(new SupplierResponse(supplier), Messages.listed);

	}

	private boolean checkIfSupplierExists(String email, String companyName) {
		if (this.supplierRepository.existsByEmail(email)) {
			return true;
		} else if (this.supplierRepository.existsByCompanyName(companyName)) {
			return true;
		}
		return false;
	}

	private boolean checkIfEmailValid(String email, String companyName) {
		companyName = companyName.toLowerCase().replaceAll(" ", "");
		String[] emailSplit = email.split("@");
		String companyNameFromEmail = emailSplit[1];

		String cName = "";
		for (int i = 0; i < companyNameFromEmail.length(); i++) {
			if (companyNameFromEmail.charAt(i) != '.') {
				cName += companyNameFromEmail.charAt(i);
			} else {
				break;
			}
		}

		if (cName.equals(companyName)) {
			return true;
		}

		return false;
	}

}
