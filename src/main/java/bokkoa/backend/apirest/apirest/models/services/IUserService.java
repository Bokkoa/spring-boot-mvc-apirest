package bokkoa.backend.apirest.apirest.models.services;

import bokkoa.backend.apirest.apirest.models.entity.User;

public interface IUserService {
    public User findByUsername( String username );
}
