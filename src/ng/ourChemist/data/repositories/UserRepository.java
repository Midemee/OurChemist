package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.User;

public interface UserRepository {
    long count();
    User save(User user);
    User findById(int id);
    User findByUsername(String username);
    void deleteById(int id);
    void delete(User user);
    void deleteAll();
    boolean existsById(int id);

}
