package com.backend.testManagement.services.Impl;

import com.backend.testManagement.dto.CommonResponseDTO;
import com.backend.testManagement.dto.TestDTO;
import com.backend.testManagement.dto.TestDTOSave;
import com.backend.testManagement.dto.PorosiaDTO;
import com.backend.testManagement.dto.PorosiaDTOSave;
import com.backend.testManagement.dto.ValidationUtilsDTO;
import com.backend.testManagement.exceptions.BadRequestException;
import com.backend.testManagement.exceptions.EntityNotFoundException;
import com.backend.testManagement.exceptions.InternalServerErrorException;
import com.backend.testManagement.mapper.PorosiaMapper;
import com.backend.testManagement.mapper.TestMapper;
import com.backend.testManagement.model.Test;
import com.backend.testManagement.model.Porosia;
import com.backend.testManagement.model.User;
import com.backend.testManagement.repository.PorosiaRepository;
import com.backend.testManagement.services.TestService;
import com.backend.testManagement.services.PorosiaService;
import com.backend.testManagement.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PorosiaServiceImpl implements PorosiaService {
    private static final Logger logger = Logger.getLogger(PorosiaServiceImpl.class.getName());

      private final PorosiaMapper porosiaMapper;
      private final PorosiaRepository porosiaRepository;

      private final UserRepository userRepository;


    @Autowired
    public PorosiaServiceImpl(PorosiaMapper porosiaMapper, PorosiaRepository porosiaRepository, UserRepository userRepository) {
        this.porosiaMapper = porosiaMapper;
        this.porosiaRepository = porosiaRepository;
        this.userRepository = userRepository;
    }


    @Override
        @Transactional(readOnly = true)
        public List<PorosiaDTO> getPorosiaByUserId(String userId) {
            List<Porosia> porosiaList = porosiaRepository.findByUserId(userId);
            return porosiaList.stream().map(porosiaMapper::mapToDTO).collect(Collectors.toList());
    }


    public List<PorosiaDTO> getPorosiaByUserIdAndShteti(String userId, String shteti) {
        List<Porosia> porosiaList = porosiaRepository.findByUserIdAndShteti(userId, shteti);
        return porosiaList.stream().map(porosiaMapper::mapToDTO).collect(Collectors.toList());
    }



    @Override
    public int getNumriPorosiveByUserIdAndShteti(String userId, String shteti) {
        return porosiaRepository.countPorosiaByUserIdAndShteti(userId, shteti);
    }

    @Override
    public int getNumriStatusitByUserId(String userId, String statusi) {
        return porosiaRepository.countStatusiByUserId(userId, statusi);
    }



    // post a porosia post request
    @Override
    @Transactional
    public PorosiaDTO saveTest(PorosiaDTOSave porosiaDTO, String userId ) {
        try {
            validatePorosiaDTO(porosiaDTO);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

            Porosia porosia = porosiaMapper.mapToEntity(porosiaDTO);
            porosia.setStatusi("Regjistruar");
            porosia.setUser(user);
            Porosia savedTest = porosiaRepository.save(porosia);
            return porosiaMapper.mapToDTO(savedTest);
        } catch (DataAccessException ex) {
            logAndThrowInternalServerError("Error saving porosia", ex);
            return null;
        } catch (BadRequestException ex) {
            logAndThrowBadRequest("Invalid request: " + ex.getMessage());
            return null;
        }
    }

    private void validatePorosiaDTO(PorosiaDTOSave porosiaDTO) {
        if (StringUtils.isBlank(porosiaDTO.getEmriBleresit())
                || StringUtils.isBlank(porosiaDTO.getShteti())
                || StringUtils.isBlank(porosiaDTO.getQyteti())
                || StringUtils.isBlank(porosiaDTO.getAdresa())
                || StringUtils.isBlank(porosiaDTO.getPostcode())
                || StringUtils.isBlank(porosiaDTO.getTelefoni())
                || StringUtils.isBlank(porosiaDTO.getEmertimiPakos())
                || StringUtils.isBlank(porosiaDTO.getPershkrimiPakos())
                || StringUtils.isBlank(porosiaDTO.getInstruksioneDorezimit())
                || StringUtils.isBlank(porosiaDTO.getMadhesia())
        ) {
            throw new BadRequestException("All field is required!");
        }

        if(porosiaDTO.getVleraPakos() == null) {
            throw new BadRequestException("Cannot be null!");
        }

    }


    // find a porosia by ID filtring porosia
    @Override
    public Porosia findTestById(String id) {
        return porosiaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warning("Test not found: " + id);
                    return new EntityNotFoundException("Test not found");
                });
    }

    // delete an porosi
    @Override
    @Transactional
    public Porosia deletePorosi(String id) {
        try {
            // Find the existing test by id
            Porosia existingPorosi = findTestById(id);

            // Delete the test from the repository
            porosiaRepository.delete(existingPorosi);

            // Log the successful deletion
            logger.info("Test deleted successfully: " + id);
            return existingPorosi;
        } catch (DataAccessException ex) {
            logAndThrowInternalServerError("Error deleting test", ex);
        } catch (EntityNotFoundException ex) {
            logAndThrowEntityNotFoundException("Test not found: " + id);
        }
        return null;
    }




    @Transactional
    public PorosiaDTO updatePorosiaStatusToDerguar(String id) {
        try {
            Porosia existingPorosia = findTestById(id);

            // Check if the current status is "Regjistruar" before updating to "derguar"
            if ("Regjistruar".equals(existingPorosia.getStatusi())) {
                existingPorosia.setStatusi("Derguar");
                Porosia updatedPorosia = porosiaRepository.save(existingPorosia);
                return porosiaMapper.mapToDTO(updatedPorosia);
            } else {
                throw new IllegalStateException("Invalid status transition");
            }
        } catch (DataAccessException ex) {
            logAndThrowInternalServerError("Error updating porosia status", ex);
            return null;
        } catch (EntityNotFoundException ex) {
            logAndThrowEntityNotFoundException("Porosia not found: " + id);
            return null;
        }
    }

    @Transactional
    public PorosiaDTO updatePorosiaStatusToDorezuar(String id) {
        try {
            Porosia existingPorosia = findTestById(id);

            // Check if the current status is "Regjistruar" before updating to "derguar"
            if ("Derguar".equals(existingPorosia.getStatusi())) {
                existingPorosia.setStatusi("Dorezuar");
                Porosia updatedPorosia = porosiaRepository.save(existingPorosia);
                return porosiaMapper.mapToDTO(updatedPorosia);
            } else {
                throw new IllegalStateException("Invalid status transition");
            }
        } catch (DataAccessException ex) {
            logAndThrowInternalServerError("Error updating porosia status", ex);
            return null;
        } catch (EntityNotFoundException ex) {
            logAndThrowEntityNotFoundException("Porosia not found: " + id);
            return null;
        }
    }


    @Override
    @Transactional
    public PorosiaDTO updatePorosi(String id, PorosiaDTOSave porosiaDTO) {
        Porosia existingPorosi = findTestById(id);
        existingPorosi.setEmriBleresit(porosiaDTO.getEmriBleresit());
        existingPorosi.setShteti(porosiaDTO.getShteti());
        existingPorosi.setQyteti(porosiaDTO.getQyteti());
        existingPorosi.setAdresa(porosiaDTO.getAdresa());
        existingPorosi.setPostcode(porosiaDTO.getPostcode());
        existingPorosi.setTelefoni(porosiaDTO.getTelefoni());
        existingPorosi.setEmertimiPakos(porosiaDTO.getEmertimiPakos());
        existingPorosi.setPershkrimiPakos(porosiaDTO.getPershkrimiPakos());
        existingPorosi.setInstruksioneDorezimit(porosiaDTO.getInstruksioneDorezimit());
        existingPorosi.setVleraPakos(porosiaDTO.getVleraPakos());
        existingPorosi.setMadhesia(porosiaDTO.getMadhesia());

        Porosia updatedPorosi = porosiaRepository.save(existingPorosi);
        logger.info("Porosia updated successfully: " + id);
        return porosiaMapper.mapToDTO(updatedPorosi);
    }

    @Override
    @Transactional(readOnly = true)
    public CommonResponseDTO<PorosiaDTO> getAllPorosi(int pageNo, int pageSize, String sortBy, String sortDirection) {
        ValidationUtilsDTO.validatePageParameters(pageNo, pageSize);
        Sort sort = Sort.by(sortBy);
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Porosia> porosiaPage = porosiaRepository.findAll(pageable);
        if (porosiaPage.isEmpty()) {
            logAndThrowEntityNotFoundException("No tests found");
        }
        List<PorosiaDTO> porosiaDTOs = porosiaPage.getContent().stream()
                .map(porosiaMapper::mapToDTO)
                .collect(Collectors.toList());
        return buildCommonResponse(porosiaDTOs, porosiaPage);
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

    private CommonResponseDTO<PorosiaDTO> buildCommonResponse(List<PorosiaDTO> porosiaDTOs, Page<Porosia> porosiaPage) {

        return CommonResponseDTO.<PorosiaDTO>builder()
                .list(porosiaDTOs)
                .totalItems(porosiaPage.getTotalElements())
                .currentPage(porosiaPage.getNumber())
                .pageNumber(porosiaPage.getNumber())
                .pageSize(porosiaPage.getSize())
                .build();

    }

//    private CommonResponseDTO<PorosiaDTO> buildCommonResponse(List<PorosiaDTO> porosiaDTOs, Page<Porosia> porosiaPage) {
//
//        return CommonResponseDTO.<PorosiaDTO>builder()
//                .list(porosiaDTOs)
//                .totalItems(porosiaPage.getTotalElements())
//                .currentPage(porosiaPage.getNumber())
//                .pageNumber(porosiaPage.getNumber())
//                .pageSize(porosiaPage.getSize())
//                .build();
//
//    }

}
