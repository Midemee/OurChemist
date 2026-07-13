package ng.ourChemist.data.repositories;
import ng.ourChemist.data.models.DispensedDrug;
import ng.ourChemist.data.models.Drug;

public interface DispensedDrugRepository {
    long count();
    DispensedDrug save(DispensedDrug dispensedDrug);
    DispensedDrug findById(int id);
    void deleteById(int id);
    void delete(DispensedDrug dispensedDrug);
    void deleteAll();
    boolean existsById(int id);



}
