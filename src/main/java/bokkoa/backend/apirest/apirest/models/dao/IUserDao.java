package bokkoa.backend.apirest.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bokkoa.backend.apirest.apirest.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {
    
    // Through the method name it executes a JPQL query
    // select user from User where user.username=?
    public User findByUsername(String username);

    // another way with explicit implementation, 1 is the first param
    @Query("select u from User u where u.username=?1")
    public User findByUsername2(String username);

}
