package com.backend.testManagement.services.Impl;

import com.backend.testManagement.dto.CommonResponseDTO;
import com.backend.testManagement.dto.FinancatDTO;
import com.backend.testManagement.dto.PorosiaDTOSave;
import com.backend.testManagement.dto.ValidationUtilsDTO;
import com.backend.testManagement.exceptions.BadRequestException;
import com.backend.testManagement.exceptions.EntityNotFoundException;
import com.backend.testManagement.exceptions.InternalServerErrorException;
import com.backend.testManagement.mapper.FinancatMapper;
import com.backend.testManagement.mapper.PorosiaMapper;
import com.backend.testManagement.model.Financat;
import com.backend.testManagement.model.Porosia;
import com.backend.testManagement.model.User;
import com.backend.testManagement.repository.FinancatRepository;
import com.backend.testManagement.repository.PorosiaRepository;
import com.backend.testManagement.repository.UserRepository;
import com.backend.testManagement.services.FinancatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FinancatServiceImpl implements FinancatService {
    private static final Logger logger = Logger.getLogger(FinancatServiceImpl.class.getName());

      private final FinancatMapper financatMapper;
      private final FinancatRepository financatRepository;

      private final PorosiaRepository porosiaRepository;

      private final UserRepository userRepository;


    @Autowired
    public FinancatServiceImpl(FinancatMapper financatMapper, FinancatRepository financatRepository, UserRepository userRepository, PorosiaRepository porosiaRepository) {
        this.financatMapper = financatMapper;
        this.financatRepository = financatRepository;
        this.userRepository = userRepository;
        this.porosiaRepository = porosiaRepository;
    }




    @Override
    public double llogaritShumenEPorosive(String userId) {
        return porosiaRepository.llogaritShumenEporosive(userId);
    }









//    @Override
//    @Transactional(readOnly = true)
//    public CommonResponseDTO<PorosiaDTO> getAllPorosi(int pageNo, int pageSize, String sortBy, String sortDirection) {
//        ValidationUtilsDTO.validatePageParameters(pageNo, pageSize);
//        Sort sort = Sort.by(sortBy);
//        if ("desc".equalsIgnoreCase(sortDirection)) {
//            sort = sort.descending();
//        }
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Page<Porosia> porosiaPage = porosiaRepository.findAll(pageable);
//        if (porosiaPage.isEmpty()) {
//            logAndThrowEntityNotFoundException("No tests found");
//        }
//        List<PorosiaDTO> porosiaDTOs = porosiaPage.getContent().stream()
//                .map(porosiaMapper::mapToDTO)
//                .collect(Collectors.toList());
//        return buildCommonResponse(porosiaDTOs, porosiaPage);
//    }
    



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
