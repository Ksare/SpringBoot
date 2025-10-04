package ru.arkhipov.MySecondTestAppSpringBoot.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Response {
    /** Уникальный идентификатор сообщения */
    private String uid;

    /** Уникальный идентификатор операции */
    private String operationUid;

    /** Время формирования сообщения */
    private String systemTime;

    /** Код выполнения операции */
    private Codes code;

    /** Код ошибки */
    private ErrorCodes errorCode;

    /** Краткое описание ошибки */
    private ErrorMessages errorMessage;

}
