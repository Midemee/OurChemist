package ng.ourChemist.data.repositories;
import ng.ourChemist.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        userRepository = new UserRepositoryImpl();
        userRepository.deleteAll();
    }
    @Test
    public void NewRepository_IsEmpty(){
        assertEquals(0, userRepository.count());
    }
    @Test
    public void SaveNewUser_CountIncreases(){
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void SaveTwoUsers_CountIsIncreasesToTwo(){
        User userOne= new User();
        User userTwo = new User();
        userRepository.save(userOne);
        userRepository.save(userTwo);
        assertEquals(2, userRepository.count());
    }

    @Test
    public void findUserById_UserIsFound(){
        userRepository.save(new User());
        User savedUser = userRepository.findById(1);
        assertEquals(1, savedUser.getId());
    }

    @Test
    public void saveTwoUsers_findUsersByTheirId_returnsTheUsersTest() {
        User userOne =  new User();
        User savedUserOne = userRepository.save(userOne);
        User userTwo =  new User();
        User savedUserTwo = userRepository.save(userTwo);

        assertEquals(2, userRepository.count());

        User foundUserOne = userRepository.findById(savedUserOne.getId());
        User foundUserTwo = userRepository.findById(savedUserTwo.getId());

        assertEquals(savedUserOne, foundUserOne);
        assertEquals(savedUserTwo, foundUserTwo);
    }

    @Test
    public void deleteUser_decrementsTest(){
        User user = new User();
        userRepository.save(user);
        userRepository.delete(user);
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveTwoUsers_deleteOneUser_findUserByIdTest(){
        User userOne = userRepository.save(new User());
        User userTwo = userRepository.save(new User());
        assertEquals(2, userRepository.count());
        userRepository.delete(userOne);
        assertEquals(1, userRepository.count());
        assertNull(userRepository.findById(userOne.getId()));
        assertEquals(userTwo, userRepository.findById(userTwo.getId()));
    }

    @Test
    public void saveTwoUsers_deleteOneUserById_findUserByIdTest(){
        User userOne = userRepository.save(new User());
        User userTwo = userRepository.save(new User());
        assertEquals(2, userRepository.count());
        userRepository.deleteById(userOne.getId());
        assertEquals(1, userRepository.count());
    }


    @Test
    public void saveThreeUsers_deleteAll_countIsZeroTest(){
        User userOne = userRepository.save(new User());
        User userTwo = userRepository.save(new User());
        User userThree = userRepository.save(new User());
        assertEquals(3, userRepository.count());
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUser_CheckIfUserExistsById(){
        User user = userRepository.save(new User());
        assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    public void saveUser_DeleteUser_CheckIfUserExistsById(){
        User user = userRepository.save(new User());
        assertTrue(userRepository.existsById(user.getId()));
        userRepository.deleteById(user.getId());
        assertFalse(userRepository.existsById(user.getId()));
    }

}

