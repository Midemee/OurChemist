package ng.ourChemist.services;

import ng.ourChemist.data.models.Drug;
import ng.ourChemist.data.repositories.DispensedDrugsRepository;
import ng.ourChemist.data.repositories.DispensedDrugsRepositoryImpl;
import ng.ourChemist.data.repositories.DrugRepository;
import ng.ourChemist.data.repositories.DrugRepositoryImpl;
import ng.ourChemist.dtos.requests.AddDrugRequest;
import ng.ourChemist.dtos.requests.DeleteDrugRequest;
import ng.ourChemist.dtos.requests.UpdateDrugRequest;
import ng.ourChemist.dtos.responses.AddDrugResponse;
import ng.ourChemist.dtos.responses.DeleteDrugResponse;
import ng.ourChemist.dtos.responses.UpdateDrugResponse;
import ng.ourChemist.utils.Mapper;

import java.util.ArrayList;

public class ChemistServiceImpl implements ChemistService{
    private final DrugRepository drugRepository = new DrugRepositoryImpl();
    private final DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();


    @Override
    public AddDrugResponse addDrug(AddDrugRequest request){
        if (request == null) {
            throw new IllegalArgumentException("Add drug request cannot be null");
        }
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Drug name is required");
        }
        if (request.getBrand() == null || request.getBrand().isEmpty()) {
            throw new IllegalArgumentException("Drug brand is required");
        }
        if (request.getPrice() <= 0) {
            throw new IllegalArgumentException("Drug price must be greater than zero");
        }

        Drug drug = drugRepository.save(Mapper.mapToDrug(request));
        AddDrugResponse response = new AddDrugResponse();
        response.setId(drug.getId());
        response.setName(drug.getName());
        response.setBrand(drug.getBrand());
        response.setPrice(drug.getPrice());
        response.setMessage("Drug added successfully");
        return response;
    }


    @Override
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Delete drug request cannot be null");
        }
        Drug drug = drugRepository.findById(request.getId());
        if (drug == null) {
            throw new IllegalArgumentException("Drug with id " + request.getId() + " not found");
        }
        drugRepository.delete(drug);
        DeleteDrugResponse response = new DeleteDrugResponse();
        response.setId(request.getId());
        response.setMessage("Drug deleted successfully");
        return response;
    }

    @Override
    public UpdateDrugResponse updateDrug(UpdateDrugRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Update request cannot be null");
        }

        Drug savedDrug = drugRepository.findById(request.getId());
        if (savedDrug == null) {
            throw new IllegalArgumentException("Drug with id " + request.getId() + " not found");
        }

        if (request.getName() != null && !request.getName().isEmpty()) {
            savedDrug.setName(request.getName());
        }
        if (request.getBrand() != null && !request.getBrand().isEmpty()) {
            savedDrug.setBrand(request.getBrand());
        }
        if (request.getPrice() > 0) {
            savedDrug.setPrice(request.getPrice());
        }

        UpdateDrugResponse response = new UpdateDrugResponse();
        response.setId(savedDrug.getId());
        response.setName(savedDrug.getName());
        response.setBrand(savedDrug.getBrand());
        response.setPrice(savedDrug.getPrice());
        response.setMessage("Drug updated successfully");
        return response;
    }
}
