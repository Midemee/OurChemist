package ng.ourChemist.utils;

import ng.ourChemist.data.models.Drug;
import ng.ourChemist.data.models.User;
import ng.ourChemist.dtos.requests.AddDrugRequest;
import ng.ourChemist.dtos.requests.RegisterUserRequest;

public class Mapper {

    public static User mapToUser(RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        return user;
    }

    public static Drug mapToDrug(AddDrugRequest request){
        Drug drug = new Drug();
        drug.setId(request.getId());
        drug.setPrice(request.getPrice());
        drug.setBrand(request.getBrand());
        drug.setName(request.getBrand());
        return drug;
    }

}
