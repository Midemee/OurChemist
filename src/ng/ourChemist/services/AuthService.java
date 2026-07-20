package ng.ourChemist.services;
import ng.ourChemist.dtos.requests.LogoutRequest;
import ng.ourChemist.dtos.requests.RegisterUserRequest;
import ng.ourChemist.dtos.requests.LoginRequest;
import ng.ourChemist.dtos.responses.LogoutResponse;
import ng.ourChemist.dtos.responses.RegisterUserResponse;
import ng.ourChemist.dtos.responses.LoginResponse;

public interface AuthService {

    RegisterUserResponse registerChemist(RegisterUserRequest request);

    LoginResponse login(LoginRequest request);
    LogoutResponse logout(LogoutRequest request);

}
