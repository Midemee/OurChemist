package ng.ourChemist.data.repositories;
import ng.ourChemist.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    private UserRepository userRepository;
    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void newRepository_isEmptyTest(){
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUser_incrementsCountTest(){
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.save(new User());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void saveNewUser_findByIdReturnsUserTest() {
        User user = new User();
        userRepository.save(user);
        User savedUser = userRepository.findById(1);
        assertNotNull(savedUser);
        assertEquals(1, savedUser.getId());
    }

    @Test
    public void updateUser_FindByIdReturnsUserTest(){
        User user = new User();
        User saved = userRepository.save(user);
        saved.setFullName("Tyron Praise");
        userRepository.save(saved);

        User found = userRepository.findById(saved.getId());
        assertNotNull(found);
        assertEquals("Tyron Praise", found.getFullName());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void findById_nonExistentId_returnsNullTest(){
        User found = userRepository.findById(626);
        assertNull(found);
    }

    @Test
    public void saveTwoUsers_incrementsCountTest() {
        User firstUser = new User();
        User secondUser = new User();
        userRepository.save(firstUser);
        userRepository.save(secondUser);

        assertEquals(2, userRepository.count());
    }

    @Test
    public void deleteUser_decrementsCountTest() {
        User user = new User();
        userRepository.save(user);
        userRepository.delete(user);
        assertNull(userRepository.findById(user.getId()));
        assertEquals(0, userRepository.count());
    }

    @Test
    public void deleteById_removesCorrectUserTest() {
        User firstUser = new User();
        User secondUser = new User();
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.deleteById(1);

        assertEquals(1, userRepository.count());
        assertFalse(userRepository.existsById(1));
        assertTrue(userRepository.existsById(2));
    }

    @Test
    public void deleteAll_repositoryBecomesEmptyTest() {
        userRepository.save(new User());
        userRepository.save(new User());
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());
    }

    @Test
    public void existsById_returnsTrueWhenUserExistsTest() {
        User user = new User();
        userRepository.save(user);

        assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    public void existsById_returnsFalseWhenUserDoesNotExistTest() {
        assertFalse(userRepository.existsById(1));
    }

}

