package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.DispensedDrug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispensedDrugRepositoryTest {

    private DispensedDrugRepository dispensedDrugRepository;

    @BeforeEach
    public void setUp() {
        dispensedDrugRepository = new DispensedDrugRepositoryImpl();
    }

    @Test
    public void newRepository_isEmptyTest() {
        assertEquals(0, dispensedDrugRepository.count());
    }

//    @Test
//    public void saveDrug_incrementsCountTest() {
//        dispensedDrugRepository.save(dispensedDrug());
//        assertEquals(1, dispensedDrugRepository.count());
//    }

    @Test
    public void saveDispensedDrug_findDrugById_returnsDispensedDrugTest() {
        DispensedDrug dispensedDrug = new DispensedDrug();
        DispensedDrug savedDrug = dispensedDrugRepository.save(dispensedDrug);
        DispensedDrug foundDrug = dispensedDrugRepository.findById(savedDrug.getId());
        assertEquals(savedDrug, foundDrug);
    }

    @Test
    public void updateDispensedDrug_findByIdReturnsUpdatedDispensedDrugTest(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        DispensedDrug saved = dispensedDrugRepository.save(dispensedDrug);
        dispensedDrugRepository.save(saved);
        DispensedDrug found = dispensedDrugRepository.findById(saved.getId());
        assertEquals(1, dispensedDrugRepository.count());
    }

    @Test
    public void findById_nonExistentId_returnsNullTest(){
        DispensedDrug found = dispensedDrugRepository.findById(20);
        assertNull(found);
    }

    @Test
    public void saveTwoDispensedDrugs_incrementsCountTest(){
        dispensedDrugRepository.save(new DispensedDrug());
        dispensedDrugRepository.save(new DispensedDrug());
        assertEquals(2, dispensedDrugRepository.count());
    }

    @Test
    public void deleteDispensedDrug_decrementsCountTest(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        dispensedDrugRepository.delete(dispensedDrug);
        assertNull(dispensedDrugRepository.findById(dispensedDrug.getId()));
        assertEquals(0, dispensedDrugRepository.count());
    }

    @Test
    public void deleteById_removesCorrectDispensedDrugTest(){
        DispensedDrug firstDrug = new DispensedDrug();
        DispensedDrug secondDrug = new DispensedDrug();
        dispensedDrugRepository.save(firstDrug);
        dispensedDrugRepository.save(secondDrug);
        dispensedDrugRepository.deleteById(1);
        assertEquals(1, dispensedDrugRepository.count());
        assertFalse(dispensedDrugRepository.existsById(1));
        assertTrue(dispensedDrugRepository.existsById(2));
    }

    @Test
    public void deleteAll_repositoryBecomesEmptyTest(){
        dispensedDrugRepository.save(new DispensedDrug());
        dispensedDrugRepository.save(new DispensedDrug());
        dispensedDrugRepository.deleteAll();
        assertEquals(0, dispensedDrugRepository.count());
    }

    @Test
    public void existsById_returnsTrueWhenDispensedDrugExistsTest(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        assertTrue(dispensedDrugRepository.existsById(dispensedDrug.getId()));
    }

    @Test
    public void existsById_returnsFalseWhenDispensedDrugDoesNotExistTest(){
        assertFalse(dispensedDrugRepository.existsById(1));
    }

}