package com.example.disasterrelief.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.disasterrelief.Enum.DocType;
import com.example.disasterrelief.Enum.VerificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenDocumentResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer documentId;
    private DocType docType;
    private String fileURI;
    private VerificationStatus verificationStatus;
    private LocalDateTime uploadedDate;
}