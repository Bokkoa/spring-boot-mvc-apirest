package bokkoa.backend.apirest.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bokkoa.backend.apirest.apirest.models.dao.IUserDao;
import bokkoa.backend.apirest.apirest.models.entity.User;

@Service
public class UserService implements UserDetailsService {
    
    private Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  userDao.findByUsername(username);

        if( user == null){
            logger.error("Error login, theres not user in system.");
            throw new UsernameNotFoundException("Error login, theres not user in system.");
        }

        // taking roles and convert to a collection of simple granted authorities
        List<GrantedAuthority> authorities = user.getRoles()
                                                 .stream()
                                                 .map( role -> { 
                                                        return  new SimpleGrantedAuthority(role.getName() ); 
                                                  })
                                                 .peek(authority -> logger.info("Role: " + authority.getAuthority()) )
                                                 .collect( Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
        
    }
    
}
