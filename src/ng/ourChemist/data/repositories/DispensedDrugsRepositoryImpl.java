package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.DispensedDrugs;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugsRepositoryImpl implements DispensedDrugsRepository{
    private int nextId;
    private final List<DispensedDrugs> dispensedDrugs = new ArrayList<>();

    @Override
    public long count() {
        return dispensedDrugs.size();
    }

    @Override
    public DispensedDrugs save(DispensedDrugs dispensedDrug) {
        if (isNew(dispensedDrug)) {
            saveNew(dispensedDrug);
        } else {
            updateExisting(dispensedDrug);
        }
        return dispensedDrug;
    }

    private void saveNew(DispensedDrugs dispensedDrug) {
        dispensedDrug.setId(++nextId);
        dispensedDrugs.add(dispensedDrug);
    }

    private boolean isNew(DispensedDrugs dispensedDrug) {
        return dispensedDrug.getId() == 0;
    }

    private void updateExisting(DispensedDrugs dispensedDrug) {
        for (int index = 0; index < dispensedDrugs.size(); index++) {
            if (dispensedDrugs.get(index).getId() == dispensedDrug.getId()) {
                dispensedDrugs.set(index, dispensedDrug);
                return;
            }
        }
    }

    @Override
    public DispensedDrugs findById(int id) {
        for (DispensedDrugs dispensedDrug : dispensedDrugs) {
            if (dispensedDrug.getId() == id) {
                return dispensedDrug;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        DispensedDrugs dispensedDrug = findById(id);
        if (dispensedDrug != null) {
            dispensedDrugs.remove(dispensedDrug);
        }
    }

    @Override
    public void delete(DispensedDrugs dispensedDrug) {
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
