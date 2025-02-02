package edu.uptc.swii.usermicroservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationConverter implements Converter <Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    @Value("${jwt.auth.converter.principal-attribute}")
    private String principalAttribute;

    @Value("${jwt.auth.converter.resource-id}")
    private String resourceid;
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt){
        Map<String, Object> resourceAccess;
        Map<String, Object> resources;
        Collection<String> resourceRole;

        if(jwt.getClaim("resource_access")== null){
            return List.of();

        }
        resourceAccess = jwt.getClaim("resource_access");

        if(resourceAccess.get(resourceid)==null){
            return List.of();
        }
        resources = (Map<String, Object>) resourceAccess.get(resourceid);

        if(resources.get("roles")==null){
            return List.of();
        }
        resourceRole =(Collection<String>) resources.get("roles");
        return resourceRole.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
                .toList();

    }

    private String getPrincipalName(Jwt jwt){
        String claimName= JwtClaimNames.SUB;

        if(principalAttribute !=null){
            claimName = principalAttribute;
        }

        return  jwt.getClaim(claimName);
    }
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities= Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
                .toList();
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalName(jwt));
    }
}
