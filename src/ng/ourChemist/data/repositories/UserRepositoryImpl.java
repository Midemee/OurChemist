package ng.ourChemist.data.repositories;

import ng.ourChemist.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private static int nextId;
    private static final List<User> users = new ArrayList<>();

    @Override
    public long count(){
        return users.size();
    }

    @Override
    public User save(User user){
        if (isNew(user)) saveNew(user);
        else updateExisting(user);
        return user;
    }

    public void saveNew(User user){
        user.setId(++nextId);
        users.add(user);
    }

    public boolean isNew(User user){
        return user.getId() == 0;
    }

    public void updateExisting(User user){
        deleteById(user.getId());
        users.add(user);
    }

    @Override
    public User findById(int id){
        for(User user : users){
            if(user.getId() == id) return user;
        }
        return null;
    }

    @Override
    public void deleteById(int id){
        User user = findById(id);
        if(user != null) users.remove(user);
    }
    @Override
    public void delete(User user){
        users.remove(user);
    }

    @Override
    public void deleteAll(){
        users.clear();
        nextId = 0;
    }

    @Override
    public boolean existsById(int id){
        return findById(id) != null;
    }

    @Override
    public User findByUsername(String username){
        for(User user: users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
