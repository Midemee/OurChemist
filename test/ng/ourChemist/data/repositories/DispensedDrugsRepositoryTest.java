package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.DispensedDrugs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispensedDrugsRepositoryTest {
    private DispensedDrugsRepository dispensedDrugsRepository;

    @BeforeEach
    public void setUp() {
        dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
    }

    @Test
    public void newRepository_isEmptyTest() {
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveDrug_incrementsCountTest() {
        dispensedDrugsRepository.save(new DispensedDrugs());
        assertEquals(1, dispensedDrugsRepository.count());
    }

    @Test
    public void saveDispensedDrugs_findDrugById_returnsDispensedDrugsTest() {
        DispensedDrugs dispensedDrug = new DispensedDrugs();
        DispensedDrugs savedDrug = dispensedDrugsRepository.save(dispensedDrug);
        DispensedDrugs foundDrug = dispensedDrugsRepository.findById(savedDrug.getId());
        assertEquals(savedDrug, foundDrug);
    }

    @Test
    public void updateDispensedDrugs_findByIdReturnsUpdatedDispensedDrugsTest(){
        DispensedDrugs dispensedDrug = new DispensedDrugs();
        DispensedDrugs saved = dispensedDrugsRepository.save(dispensedDrug);
        dispensedDrugsRepository.save(saved);
        DispensedDrugs found = dispensedDrugsRepository.findById(saved.getId());
        assertEquals(1, dispensedDrugsRepository.count());
    }

    @Test
    public void findById_nonExistentId_returnsNullTest(){
        DispensedDrugs found = dispensedDrugsRepository.findById(20);
        assertNull(found);
    }

    @Test
    public void saveTwoDispensedDrugss_incrementsCountTest(){
        dispensedDrugsRepository.save(new DispensedDrugs());
        dispensedDrugsRepository.save(new DispensedDrugs());
        assertEquals(2, dispensedDrugsRepository.count());
    }

    @Test
    public void deleteDispensedDrugs_decrementsCountTest(){
        DispensedDrugs dispensedDrug = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrug);
        dispensedDrugsRepository.delete(dispensedDrug);
        assertNull(dispensedDrugsRepository.findById(dispensedDrug.getId()));
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void deleteById_removesCorrectDispensedDrugsTest(){
        DispensedDrugs firstDrug = new DispensedDrugs();
        DispensedDrugs secondDrug = new DispensedDrugs();
        dispensedDrugsRepository.save(firstDrug);
        dispensedDrugsRepository.save(secondDrug);
        dispensedDrugsRepository.deleteById(1);
        assertEquals(1, dispensedDrugsRepository.count());
        assertFalse(dispensedDrugsRepository.existsById(1));
        assertTrue(dispensedDrugsRepository.existsById(2));
    }

    @Test
    public void deleteAll_repositoryBecomesEmptyTest(){
        dispensedDrugsRepository.save(new DispensedDrugs());
        dispensedDrugsRepository.save(new DispensedDrugs());
        dispensedDrugsRepository.deleteAll();
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void existsById_returnsTrueWhenDispensedDrugsExistsTest(){
        DispensedDrugs dispensedDrug = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrug);
        assertTrue(dispensedDrugsRepository.existsById(dispensedDrug.getId()));
    }

    @Test
    public void existsById_returnsFalseWhenDispensedDrugsDoesNotExistTest(){
        assertFalse(dispensedDrugsRepository.existsById(1));
    }

}