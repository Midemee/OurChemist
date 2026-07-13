package ng.ourChemist.data.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispensedDrugs {
    private User dispensedBy;
    private List<DispensedDrug> dispensedDrugs = new ArrayList<>();
    private LocalDateTime dispensedDateTime;
    private int id;
}
