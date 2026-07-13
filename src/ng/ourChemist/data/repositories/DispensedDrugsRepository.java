package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.DispensedDrugs;
import ng.ourChemist.data.models.Drug;

public interface DispensedDrugsRepository {
    long count();
    DispensedDrugs save(DispensedDrugs dispensedDrugs);
    DispensedDrugs findById(int id);
    void deleteById(int id);
    void delete(DispensedDrugs dispensedDrugs);
    void deleteAll();
    boolean existsById(int id);
}
