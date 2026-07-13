package ng.ourChemist.data.repositories;
import ng.ourChemist.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository{
    private int nextId;
    private final List<Drug> drugs = new ArrayList<>();

    @Override
    public long count() {
        return drugs.size();
    }

    @Override
    public Drug save(Drug drug) {
        if (isNew(drug)) {
            saveNew(drug);
        } else {
            updateExisting(drug);
        }
        return drug;
    }

    private void saveNew(Drug drug) {
        drug.setId(++nextId);
        drugs.add(drug);
    }

    private boolean isNew(Drug drug) {
        return drug.getId() == 0;
    }

    private void updateExisting(Drug drug) {
        for (int index = 0; index < drugs.size(); index++) {
            if (drugs.get(index).getId() == drug.getId()) {
                drugs.set(index, drug);
                return;
            }
        }
    }

    @Override
    public Drug findById(int id) {
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Drug drug = findById(id);
        if (drug != null) {
            drugs.remove(drug);
        }
    }

    @Override
    public void delete(Drug drug) {
        drugs.remove(drug);
    }

    @Override
    public void deleteAll() {
        drugs.clear();
        nextId = 0;
    }

    @Override
    public boolean existsById(int id) {
        return findById(id) != null;
    }
}
