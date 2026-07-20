package ng.ourChemist.services;

import ng.ourChemist.data.repositories.UserRepository;
import ng.ourChemist.data.repositories.UserRepositoryImpl;
import ng.ourChemist.dtos.requests.LoginRequest;
import ng.ourChemist.dtos.requests.LogoutRequest;
import ng.ourChemist.dtos.requests.RegisterUserRequest;
import ng.ourChemist.dtos.responses.LoginResponse;
import ng.ourChemist.dtos.responses.LogoutResponse;
import ng.ourChemist.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceImplTest {

    private AuthService authService;
    private UserRepository user;
    private RegisterUserRequest request;

    @BeforeEach
    public void setUp() {
        user = new UserRepositoryImpl();
        authService = new AuthServiceImpl();
        request = new RegisterUserRequest();
        user.deleteAll();
    }

    @Test
    public void registerChemistIncreasesTheUsers() {
        request.setUsername("username");
        request.setPassword("password");
        request.setFullName("fullName");
        authService.registerChemist(request);
        assertEquals(1, user.count());
    }


    @Test
    public void registerChemistReturnsResponseWithCorrectFullName() {
        request.setUsername("username");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        RegisterUserResponse response = authService.registerChemist(request);
        assertEquals("username", response.getUsername());
        assertEquals("Aramide Ashiwaju", response.getFullName());
    }

    @Test
    public void registeringWithDuplicateUsername_throwsExceptionTest(){
        request.setUsername("username");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);

        RegisterUserRequest requestOne = new RegisterUserRequest();
        request.setUsername("username");
        request.setPassword("password123");
        request.setFullName("Aramide Ashiwaju");
        assertThrows(NullPointerException.class, () -> authService.registerChemist(requestOne));
    }

    @Test
    public void registeredChemist_LoginSuccessfulTest(){
        LoginRequest userRequest = new LoginRequest();
        request.setUsername("midemee");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);
        assertEquals(1, user.count());

        userRequest.setUsername("midemee");
        userRequest.setPassword("password");
        LoginResponse response = authService.login(userRequest);
        assertTrue(response.isLoggedIn());
    }

    @Test
    public void loginWithWrongUsername_throwsExceptionTest(){
        LoginRequest userRequest = new LoginRequest();
        request.setUsername("midemee");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);
        assertEquals(1, user.count());

        userRequest.setUsername("middy");
        userRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> authService.login(userRequest));
    }

    @Test
    public void loginWithIncorrectPassword_throwsExceptionTest(){
        LoginRequest userRequest = new LoginRequest();
        request.setUsername("midemee");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);
        assertEquals(1, user.count());

        userRequest.setUsername("middy");
        userRequest.setPassword("mide123");
        assertThrows(IllegalArgumentException.class, () -> authService.login(userRequest));
    }

    @Test
    public void afterRegisteringCannotLoginWithAnEmptyUsernameOrEmptyPassword() {
        LoginRequest userRequest = new LoginRequest();
        request.setUsername("midemee");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);
        assertEquals(1, user.count());

        userRequest.setUsername("");
        userRequest.setPassword("");
        assertThrows(IllegalArgumentException.class, () -> authService.login(userRequest));
    }

    @Test
    public void registeredChemist_LogoutSuccessfulTest(){
        LoginRequest userRequest = new LoginRequest();
        request.setUsername("midemee");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);
        assertEquals(1, user.count());

        userRequest.setUsername("midemee");
        userRequest.setPassword("password");
        LoginResponse response = authService.login(userRequest);
        assertTrue(response.isLoggedIn());

        LogoutRequest request = new LogoutRequest();
        request.setUsername("midemee");
        authService.logout(request);
        assertFalse(user.findByUsername("midemee").isLoggedIn());
    }

    @Test
    public void logoutWithInvalidUsernameThrowsException(){
        LoginRequest userRequest = new LoginRequest();
        request.setUsername("midemee");
        request.setPassword("password");
        request.setFullName("Aramide Ashiwaju");
        authService.registerChemist(request);
        assertEquals(1, user.count());

        userRequest.setUsername("midemee");
        userRequest.setPassword("password");
        LoginResponse response = authService.login(userRequest);
        assertTrue(response.isLoggedIn());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("mide123");
        assertThrows(IllegalArgumentException. class, () -> authService.logout(logoutRequest));
    }

}
