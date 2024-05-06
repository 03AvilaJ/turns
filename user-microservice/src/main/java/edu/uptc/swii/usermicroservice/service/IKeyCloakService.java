package edu.uptc.swii.usermicroservice.service;


import edu.uptc.swii.usermicroservice.entity.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeyCloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUserId(Integer userId);
    String createUser(UserDTO userDTO);
    void deleteUser(Integer userId);
    void updateUser(Integer userId, UserDTO userDTO) ;
}
