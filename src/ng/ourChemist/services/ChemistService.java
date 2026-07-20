package ng.ourChemist.services;
import ng.ourChemist.dtos.requests.AddDrugRequest;
import ng.ourChemist.dtos.requests.DeleteDrugRequest;
import ng.ourChemist.dtos.requests.DispenseDrugsRequest;
import ng.ourChemist.dtos.requests.UpdateDrugRequest;
import ng.ourChemist.dtos.responses.AddDrugResponse;
import ng.ourChemist.dtos.responses.DeleteDrugResponse;
import ng.ourChemist.dtos.responses.DispenseDrugsResponse;
import ng.ourChemist.dtos.responses.UpdateDrugResponse;

public interface ChemistService {

    AddDrugResponse addDrug(AddDrugRequest request);
    UpdateDrugResponse updateDrug(UpdateDrugRequest request);
    DeleteDrugResponse deleteDrug(DeleteDrugRequest request);
//    DispenseDrugsResponse dispenseDrugs(DispenseDrugsRequest request);
}
