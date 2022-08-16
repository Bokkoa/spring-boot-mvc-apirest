package bokkoa.backend.apirest.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import bokkoa.backend.apirest.apirest.models.entity.User;
import bokkoa.backend.apirest.apirest.models.services.IUserService;

@Component
public class AditionalTokenInfo implements TokenEnhancer {

    @Autowired
    IUserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());

        Map<String, Object> info = new HashMap<>();
        info.put("name", user.getName());
        info.put("lastName", user.getLastName());
        info.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
    
}
