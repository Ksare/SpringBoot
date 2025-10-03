package ru.arkhipov.MySecondTestAppSpringBoot.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32, message = "UID не может быть длиннее 32 символов")
    private String uid;

    @NotBlank(message = "OperationUid не может быть пустым")
    @Size(max = 32, message = "OperationUid не может быть длиннее 32 символов")
    private String operationUid;


    private String systemName;

    @NotBlank(message = "SystemTime не может быть пустым")
    private String systemTime;


    private String source;

    @NotNull(message = "CommunicationId обязателен")
    @Min(value = 1, message = "CommunicationId не может быть меньше 1")
    @Max(value = 100000, message = "CommunicationId не может быть больше 100000")
    private Integer communicationId;

    private int templateId;


    private int productCode;
    private int smsCode;

}
