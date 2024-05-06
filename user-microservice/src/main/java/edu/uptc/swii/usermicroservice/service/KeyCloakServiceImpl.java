package edu.uptc.swii.usermicroservice.service;

import edu.uptc.swii.usermicroservice.entity.UserDTO;
import edu.uptc.swii.usermicroservice.utils.KeyCloakProvider;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KeyCloakServiceImpl implements  IKeyCloakService{

    @Override
    public List<UserRepresentation> findAllUsers() {
        return KeyCloakProvider.getRealmResource().users().list();
    }

    @Override
    public List<UserRepresentation> searchUserByUserId(Integer userId) {
        return null;
    }

    @Override
    public String createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public void updateUser(Integer userId, UserDTO userDTO) {

    }
}
