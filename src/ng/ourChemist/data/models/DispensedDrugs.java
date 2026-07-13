package ng.ourChemist.data.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispensedDrugs {
    private User dispensedBy;
    private List<DispensedDrug> dispensedDrugs = new ArrayList<>();
    private LocalDateTime dispensedDateTime;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDispensedBy() {
        return dispensedBy;
    }

    public void setDispensedBy(User dispensedBy) {
        this.dispensedBy = dispensedBy;
    }

    public LocalDateTime getDispensedDateTime() {
        return dispensedDateTime;
    }

    public void setDispensedDateTime(LocalDateTime dispensedDateTime) {
        this.dispensedDateTime = dispensedDateTime;
    }

    public List<DispensedDrug> getDispensedDrugs() {
        return dispensedDrugs;
    }

    public void setDispensedDrugs(List<DispensedDrug> dispensedDrugs) {
        this.dispensedDrugs = dispensedDrugs;
    }
}
