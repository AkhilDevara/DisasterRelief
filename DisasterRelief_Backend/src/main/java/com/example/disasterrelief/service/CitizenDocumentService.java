package com.example.disasterrelief.service;

import com.example.disasterrelief.dao.CitizenDocumentRepository;
import com.example.disasterrelief.dao.CitizenRepository;
import com.example.disasterrelief.dto.request.CitizenDocumentRequestDTO;
import com.example.disasterrelief.entity.Citizen;
import com.example.disasterrelief.entity.CitizenDocument;
import org.springframework.stereotype.Service;

@Service
public class CitizenDocumentService {

    private final CitizenDocumentRepository documentRepository;
    private final CitizenRepository citizenRepository;

    public CitizenDocumentService(CitizenDocumentRepository documentRepository,
                                  CitizenRepository citizenRepository) {
        this.documentRepository = documentRepository;
        this.citizenRepository = citizenRepository;
    }

    // ✅ UPLOAD DOCUMENT
    public CitizenDocument uploadDocument(CitizenDocumentRequestDTO requestDTO) {

        Citizen citizen = citizenRepository.findById(requestDTO.getCitizenId())
                .orElseThrow(() ->
                        new RuntimeException("Citizen not found with ID: "
                                + requestDTO.getCitizenId()));

        CitizenDocument document = new CitizenDocument();
        document.setCitizen(citizen);
        document.setDocType(requestDTO.getDocType());
        document.setFileURI(requestDTO.getFileURI());
        document.setVerificationStatus(requestDTO.getVerificationStatus());

        return documentRepository.save(document);
    }

    // ✅ GET BY ID
    public CitizenDocument getDocumentById(int docId) {
        return documentRepository.findById(docId)
                .orElseThrow(() ->
                        new RuntimeException("CitizenDocument not found with ID: " + docId));
    }

    // ✅ DELETE
    public void deleteDocument(int docId) {
        documentRepository.deleteById(docId);
    }
}