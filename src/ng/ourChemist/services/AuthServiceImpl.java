package ng.ourChemist.services;

import ng.ourChemist.data.models.User;
import ng.ourChemist.data.repositories.UserRepository;
import ng.ourChemist.data.repositories.UserRepositoryImpl;
import ng.ourChemist.dtos.requests.LogoutRequest;
import ng.ourChemist.dtos.requests.RegisterUserRequest;
import ng.ourChemist.dtos.requests.LoginRequest;
import ng.ourChemist.dtos.responses.LogoutResponse;
import ng.ourChemist.dtos.responses.RegisterUserResponse;
import ng.ourChemist.dtos.responses.LoginResponse;
import ng.ourChemist.utils.Mapper;

public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public RegisterUserResponse registerChemist(RegisterUserRequest request) {
        request.setUsername(request.getUsername().toLowerCase());
        User user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            throw new IllegalArgumentException("Username " + request.getUsername() + " already exists");
        }
        RegisterUserResponse response = new RegisterUserResponse();
        response.setFullName(request.getFullName());
        response.setUsername(request.getUsername());
        userRepository.save(Mapper.mapToUser(request));
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if(request.getUsername() == null || request.getPassword() == null || request.getUsername().isEmpty() || request.getPassword().isEmpty()){
            throw new IllegalArgumentException("Username or password is empty");
        }
        if(user == null){
            throw new IllegalArgumentException("Username" + request.getUsername() + "does not exist");
        }
        if(!user.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("incorrect Password");
        }

        userRepository.save(user);
        user.setLoggedIn(true);
        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername().toLowerCase());
        response.setLoggedIn(true);
        return response;

    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.setLoggedIn(false);
        LogoutResponse response = new LogoutResponse();
        response.setUsername(user.getUsername().toLowerCase());
        response.setLoggedIn(false);
        return response;
    }

}

