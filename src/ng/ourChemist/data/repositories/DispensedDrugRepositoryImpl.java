package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.DispensedDrug;
import ng.ourChemist.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository{
    private int nextId;
    private final List<DispensedDrug> dispensedDrugs = new ArrayList<>();

    @Override
    public long count() {
        return dispensedDrugs.size();
    }

    @Override
    public DispensedDrug save(DispensedDrug dispensedDrug) {
        if (isNew(dispensedDrug)) {
            saveNew(dispensedDrug);
        } else {
            updateExisting(dispensedDrug);
        }
        return dispensedDrug;
    }

    private void saveNew(DispensedDrug dispensedDrug) {
        dispensedDrug.setId(++nextId);
        dispensedDrugs.add(dispensedDrug);
    }

    private boolean isNew(DispensedDrug dispensedDrug) {
        return dispensedDrug.getId() == 0;
    }

    private void updateExisting(DispensedDrug dispensedDrug) {
        for (int index = 0; index < dispensedDrugs.size(); index++) {
            if (dispensedDrugs.get(index).getId() == dispensedDrug.getId()) {
                dispensedDrugs.set(index, dispensedDrug);
                return;
            }
        }
    }

    @Override
    public DispensedDrug findById(int id) {
        for (DispensedDrug dispensedDrug : dispensedDrugs) {
            if (dispensedDrug.getId() == id) {
                return dispensedDrug;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        DispensedDrug dispensedDrug = findById(id);
        if (dispensedDrug != null) {
            dispensedDrugs.remove(dispensedDrug);
        }
    }

    @Override
    public void delete(DispensedDrug dispensedDrug) {
        dispensedDrugs.remove(dispensedDrug);
    }

    @Override
    public void deleteAll() {
        dispensedDrugs.clear();
        nextId = 0;
    }

    @Override
    public boolean existsById(int id) {
        return findById(id) != null;
    }

}
