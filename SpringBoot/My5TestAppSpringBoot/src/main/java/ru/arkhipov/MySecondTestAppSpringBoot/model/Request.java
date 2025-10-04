package ru.arkhipov.MySecondTestAppSpringBoot.model;


import jakarta.validation.constraints.*;
import lombok.*;
import lombok.ToString;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {

    /** Время отправки сообщения клиентом */
    @Builder.Default
    private long clientSendTime = System.currentTimeMillis();

    /** Уникальный идентификатор сообщения */


    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32, message = "UID не может быть длиннее 32 символов")
    private String uid;
    /** Уникальный идентификатор операции */
    @NotBlank(message = "OperationUid не может быть пустым")
    @Size(max = 32, message = "OperationUid не может быть длиннее 32 символов")
    private String operationUid;

    /** Наименование системы отправителя */


    private String systemName;  //выременно заменил для 4 проекта с Systems на String
    /** Время формирования сообщения */

    @NotBlank(message = "SystemTime не может быть пустым")
    private String systemTime;

    /** Наименование ресурса */

    private String source;


    private String position;       // Должность
    private Integer salary;        // Зарплата
    private Double bonus;          // Бонус
    private Integer workDays;      // Рабочие дни


    /** Уникальный идентификатор коммуникации */
    @NotNull(message = "CommunicationId обязателен")
    @Min(value = 1, message = "CommunicationId не может быть меньше 1")
    @Max(value = 100000, message = "CommunicationId не может быть больше 100000")
    private Integer communicationId;
    /** Уникальный идентификатор шаблона */

    private int templateId;

    /** Код продукта */

    private int productCode;

    /** код SMS */

    private int smsCode;


    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';

    }
}