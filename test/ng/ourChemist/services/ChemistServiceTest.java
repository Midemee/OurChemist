package ng.ourChemist.services;

import ng.ourChemist.data.repositories.DrugRepository;
import ng.ourChemist.data.repositories.DrugRepositoryImpl;
import ng.ourChemist.dtos.requests.AddDrugRequest;
import ng.ourChemist.dtos.requests.DeleteDrugRequest;
import ng.ourChemist.dtos.requests.UpdateDrugRequest;
import ng.ourChemist.dtos.responses.AddDrugResponse;
import ng.ourChemist.dtos.responses.DeleteDrugResponse;
import ng.ourChemist.dtos.responses.UpdateDrugResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChemistServiceTest {
    private ChemistService chemistService;
    private AddDrugRequest request;
    private DrugRepository drug;

    @BeforeEach
    public void setUp(){
        chemistService = new ChemistServiceImpl();
        request = new AddDrugRequest();
        drug = new DrugRepositoryImpl();
    }
    @Test
    public void addDrugs_CountIncreaseTest(){
        request.setBrand("Emzor");
        request.setName("Panadol");
        request.setPrice(100);
        AddDrugResponse response = chemistService.addDrug(request);
//        assertEquals(1, drug.count());

        assertEquals(1, response.getId());
        assertEquals("Emzor", response.getBrand());
//        assertEquals("Panadol", response.getName());
        assertEquals(100, response.getPrice());
        assertEquals("Drug added successfully", response.getMessage());
    }

    @Test
    void deleteDrugRemovesDrugFromRepository() {
        AddDrugRequest request = new AddDrugRequest();
        request.setName("Paracetamol");
        request.setBrand("Acme");
        request.setPrice(500);
        AddDrugResponse response = chemistService.addDrug(request);

        DeleteDrugRequest deleteRequest = new DeleteDrugRequest();
        deleteRequest.setId(response.getId());
        DeleteDrugResponse result = chemistService.deleteDrug(deleteRequest);
        assertEquals("Drug deleted successfully", result.getMessage());

        assertThrows(IllegalArgumentException.class, () -> {chemistService.deleteDrug(deleteRequest);
        });
    }

    @Test
    void updateDrugChangesExistingDrugAndReturnsUpdatedResponse() {
        AddDrugRequest addRequest = new AddDrugRequest();
        addRequest.setName("Paracetamol");
        addRequest.setBrand("Acme");
        addRequest.setPrice(500);
        AddDrugResponse addResponse = chemistService.addDrug(addRequest);

        UpdateDrugRequest updateRequest = new UpdateDrugRequest();
        updateRequest.setId(addResponse.getId());
        updateRequest.setName("Panadol");
        updateRequest.setBrand("Emzor");
        updateRequest.setPrice(500);

        UpdateDrugResponse updateResponse = chemistService.updateDrug(updateRequest);

        assertEquals(addResponse.getId(), updateResponse.getId());
        assertEquals("Panadol", updateResponse.getName());
        assertEquals("Emzor", updateResponse.getBrand());
        assertEquals(500, updateResponse.getPrice());
        assertEquals("Drug updated successfully", updateResponse.getMessage());
    }

}