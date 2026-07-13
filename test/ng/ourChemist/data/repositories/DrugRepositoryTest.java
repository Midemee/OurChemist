package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DrugRepositoryTest {
    private DrugRepository drugRepository;

    @BeforeEach
    public void setUp() {
        drugRepository = new DrugRepositoryImpl();
    }

    @Test
    public void newRepository_isEmptyTest() {
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void saveDrug_incrementsCountTest() {
        drugRepository.save(new Drug("Paracetamol", "Emzor", LocalDate.of(2028, 5, 20), 500));
        assertEquals(1, drugRepository.count());
    }

    @Test
    public void saveNewDrug_findByIdReturnsDrugTest() {
        Drug drug = new Drug("Paracetamol", "Emzor", LocalDate.of(2028, 5, 20), 500);
        drugRepository.save(drug);
        Drug savedDrug = drugRepository.findById(1);
        assertNotNull(savedDrug);
        assertEquals("Paracetamol", savedDrug.getName());
        assertEquals("Emzor", savedDrug.getBrand());
        assertEquals(500, savedDrug.getPrice());
        assertEquals(LocalDate.of(2028,5,20), savedDrug.getExpiryDate());
    }

    @Test
    public void updateDrug_findByIdReturnsUpdatedDrugTest(){
        Drug drug = new Drug("Paracetamol", "Emzor", LocalDate.of(2028, 5, 20), 500);
        Drug saved = drugRepository.save(drug);
        saved.setPrice(700);
        drugRepository.save(saved);
        Drug found = drugRepository.findById(saved.getId());
        assertEquals(700, found.getPrice());
        assertEquals(1, drugRepository.count());
    }

    @Test
    public void findById_nonExistentId_returnsNullTest(){
        Drug found = drugRepository.findById(20);
        assertNull(found);
    }

    @Test
    public void saveTwoDrugs_incrementsCountTest(){
        drugRepository.save(new Drug("Paracetamol","Emzor",LocalDate.of(2028,5,20),500));
        drugRepository.save(new Drug("Vitamin C","Emvit",LocalDate.of(2027,10,15),800));
        assertEquals(2, drugRepository.count());
    }

    @Test
    public void deleteDrug_decrementsCountTest(){
        Drug drug = new Drug("Paracetamol", "Emzor", LocalDate.of(2028, 5, 20), 500);
        drugRepository.save(drug);
        drugRepository.delete(drug);
        assertNull(drugRepository.findById(drug.getId()));
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void deleteById_removesCorrectDrugTest(){
        Drug firstDrug = new Drug("Paracetamol", "Emzor", LocalDate.of(2028, 5, 20), 500);
        Drug secondDrug = new Drug("Vitamin C","Emvit", LocalDate.of(2027,10,15),800);
        drugRepository.save(firstDrug);
        drugRepository.save(secondDrug);
        drugRepository.deleteById(1);
        assertEquals(1, drugRepository.count());
        assertFalse(drugRepository.existsById(1));
        assertTrue(drugRepository.existsById(2));
    }

    @Test
    public void deleteAll_repositoryBecomesEmptyTest(){
        drugRepository.save(new Drug("Paracetamol","Emzor",LocalDate.of(2028,5,20),500));
        drugRepository.save(new Drug("Vitamin C","Emvit",LocalDate.of(2027,10,15),800));
        drugRepository.deleteAll();
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void existsById_returnsTrueWhenDrugExistsTest(){
        Drug drug = new Drug("Paracetamol", "Emzor", LocalDate.of(2028, 5, 20), 500);
        drugRepository.save(drug);
        assertTrue(drugRepository.existsById(drug.getId()));
    }

    @Test
    public void existsById_returnsFalseWhenDrugDoesNotExistTest(){
        assertFalse(drugRepository.existsById(1));
    }

}