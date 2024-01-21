package com.backend.testManagement.services.Impl;

import com.backend.testManagement.config.KeycloakConfig;
import com.backend.testManagement.dto.*;
import com.backend.testManagement.exceptions.BadRequestException;
import com.backend.testManagement.exceptions.EntityNotFoundException;
import com.backend.testManagement.exceptions.InternalServerErrorException;
import com.backend.testManagement.mapper.PorosiaMapper;
import com.backend.testManagement.model.Porosia;
import com.backend.testManagement.mapper.UserMapper;
import com.backend.testManagement.model.Test;
import com.backend.testManagement.model.User;
import com.backend.testManagement.repository.UserRepository;
import com.backend.testManagement.services.UserService;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserMapper userMapper;

    private final Keycloak keycloak;
    private KeycloakConfig keycloakConfig;

    private final UserRepository userRepository;




    @Autowired
    public UserServiceImpl(UserMapper userMapper, Keycloak keycloak, KeycloakConfig keycloakConfig, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.keycloak = keycloak;
        this.keycloakConfig = keycloakConfig;
        this.userRepository = userRepository;
    }


    public CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }


    private RoleRepresentation getDefaultRole(){
        return keycloak.realm(keycloakConfig.getRealm()).clients()
                .get(keycloakConfig.getPublicClientId()).roles().get("user").toRepresentation();
    }

    public void createUser(UserSaveDTO userSaveDTO) {
        try{
            CredentialRepresentation credential = createPasswordCredentials(userSaveDTO.getPassword());
            UserRepresentation user = new UserRepresentation();
            user.setUsername(userSaveDTO.getUsername());
            user.setFirstName(userSaveDTO.getFirstname());
            user.setLastName(userSaveDTO.getLastname());
            user.setEmail(userSaveDTO.getEmail());
            user.setCredentials(Collections.singletonList(credential));
            user.setEnabled(true);

            UsersResource instance = keycloak.realm(keycloakConfig.getRealm()).users();

            Response response = instance.create(user);

            if(response.getStatus() != 201) {
                throw new InternalServerErrorException("Could not create user");
            }

            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            UserResource userResource = instance.get(userId);
            var defaultRole = getDefaultRole();
            userResource.roles().clientLevel(keycloakConfig.getPublicClientId()).add(Collections.singletonList(defaultRole));


            User userEntity = new User();
            userEntity.setId(userId);
            userEntity.setUsername(userSaveDTO.getUsername());
            userEntity.setEmail(userSaveDTO.getEmail());

            userRepository.save(userEntity);



        }
        catch (DataAccessException ex) {
            // Handle data access exceptions
            logAndThrowInternalServerError("Error saving test", ex);
        } catch (BadRequestException ex) {
            // Handle validation exceptions
            logAndThrowBadRequest("Invalid request: " + ex.getMessage());
        }
    }



    public String loginUser(UserLoginDTO userLoginDTO) {
        try {

            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakConfig.getServerUrl())
                    .realm(keycloakConfig.getRealm())
                    .clientId("rest-api")
                    .username(userLoginDTO.getUsername())
                    .password(userLoginDTO.getPassword())
                    .grantType(OAuth2Constants.PASSWORD)
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                    .build();

            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();

            return tokenResponse.getToken();
        }
        catch (DataAccessException ex) {
            // Handle data access exceptions
            logAndThrowInternalServerError("Error saving test", ex);
            return null;
        } catch (BadRequestException ex) {
            // Handle validation exceptions
            logAndThrowBadRequest("Invalid request: " + ex.getMessage());
            return null;
        }
    }


//    @Override
//    @Transactional(readOnly = true)
//    public CommonResponseDTO<TestDTO> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDirection) {
//        ValidationUtilsDTO.validatePageParameters(pageNo, pageSize);
//        Sort sort = Sort.by(sortBy);
//        if ("desc".equalsIgnoreCase(sortDirection)) {
//            sort = sort.descending();
//        }
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Page<Test> testPage = testRepository.findAll(pageable);
//        if (testPage.isEmpty()) {
//            logAndThrowEntityNotFoundException("No tests found");
//        }
//        List<TestDTO> testDTOs = testPage.getContent().stream()
//                .map(testMapper::mapToDTO)
//                .collect(Collectors.toList());
//        return buildCommonResponse(testDTOs, testPage);
//    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }



    private void logAndThrowEntityNotFoundException(String message) {
        logger.warning(message);
        throw new EntityNotFoundException(message);
    }

    private void logAndThrowInternalServerError(String message, Exception ex) {
        logger.severe(message + ": " + ex.getMessage());
        throw new InternalServerErrorException(message);
    }

    private void logAndThrowBadRequest(String message) {
        logger.warning(message);
        throw new BadRequestException(message);
    }

//    private CommonResponseDTO<UserDTO> buildCommonResponse(List<UserDTO> userDTOs, Page<User> userPage) {
//
//        return CommonResponseDTO.<PorosiaDTO>builder()
//                .list(userDTOs)
//                .totalItems(userPage.getTotalElements())
//                .currentPage(userPage.getNumber())
//                .pageNumber(userPage.getNumber())
//                .pageSize(userPage.getSize())
//                .build();
//    }


}
