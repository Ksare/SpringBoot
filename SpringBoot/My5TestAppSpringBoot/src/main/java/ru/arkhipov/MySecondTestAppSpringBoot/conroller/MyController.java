package ru.arkhipov.MySecondTestAppSpringBoot.conroller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.arkhipov.MySecondTestAppSpringBoot.service.*;
import ru.arkhipov.MySecondTestAppSpringBoot.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final CheckUidService checkUidService;
    private final ModifyResponseService modifyResponseService;


    @Autowired
    private AnnualBonusServiceImpl annualBonusService;

    public MyController(ValidationService validationService, CheckUidService checkUidService, @Qualifier("modifyOperationUidResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.checkUidService = checkUidService;
        this.modifyResponseService = modifyResponseService;


    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        long service2ReceiveTime = System.currentTimeMillis();
        long totalTimeFromPostman = service2ReceiveTime - request.getClientSendTime();

        log.info("ВРЕМЯ ОТ POSTMAN ДО СЕРВИСА 2: {} мс", totalTimeFromPostman);
        log.info("Время отправки клиентом: {}", new Date(request.getClientSendTime()));
        log.info("Время получения Сервисом 2: {}", new Date(service2ReceiveTime));

        log.info("request: {}", request);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        try {
            log.info("Начало валидации данных");
            validationService.isValid(bindingResult);
            log.info("Валидация пройдена успешно");

            log.info("Проверка UID: {}", request.getUid());
            checkUidService.checkUid(request);
            log.info("Проверка UID пройдена");

        } catch (ValidationFailedException e) {
            log.error("Ошибка валидации: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            log.error("Неподдерживаемый UID: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION); // Новый код ошибки
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Неизвестная ошибка: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Модификация ответа");
        modifyResponseService.modify(response);

        log.info("Ответ отправлен: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test-days/{year}")
    public String testDaysInYear(@PathVariable int year) {
        int days = annualBonusService.calculate(year);
        boolean isLeap = (days == 366);

        return String.format("""
                Год: %d
                Количество дней: %d
                Високосный: %s
                """, year, days, isLeap ? "Да" : "Нет");
    }

    @GetMapping("/test-positions-manager")
    public String testPositionsManager() {
        StringBuilder result = new StringBuilder("=== ТЕСТ ПОЗИЦИЙ ===\n");

        for (Positions position : Positions.values()) {
            result.append(String.format(
                    "%-10s | Менеджер: %-5s | Коэффициент: %.1f%n",
                    position.name(),
                    position.isManager() ? "ДА" : "нет",
                    position.getPositionCoefficient()
            ));
        }

        // Проверяем сколько менеджеров
        long managers = java.util.Arrays.stream(Positions.values())
                .filter(Positions::isManager)
                .count();

        result.append(String.format("\nВсего менеджеров: %d из %d", managers, Positions.values().length));

        return result.toString();
    }

    @GetMapping("/test-days")
    public String testMultipleYears() {
        StringBuilder result = new StringBuilder();
        int[] years = {2024, 2023, 2000, 1900, 2020, 2022};

        for (int year : years) {
            int days = annualBonusService.calculate(year);
            result.append(String.format("Год %d: %d дней%n", year, days));
        }

        return result.toString();
    }

    @Autowired
    private QuarterlyBonusService quarterlyBonusService;

    @GetMapping("/test-quarterly-bonus/{position}/{salary}/{bonus}")
    public String testQuarterlyBonus(@PathVariable String position,
                                     @PathVariable double salary,
                                     @PathVariable double bonus) {
        try {
            Positions pos = Positions.valueOf(position.toUpperCase());
            double result = quarterlyBonusService.calculateQuarterlyBonus(pos, salary, bonus);

            return String.format("""
                            Должность: %s
                            Менеджер: %s
                            Зарплата: %.2f
                            Коэффициент бонуса: %.2f
                            Квартальная премия: %.2f
                            """,
                    pos.name(),
                    pos.isManager() ? "Да" : "Нет",
                    salary,
                    bonus,
                    result
            );

        } catch (IllegalArgumentException e) {
            return "Ошибка: неверное название должности. Доступные: " +
            java.util.Arrays.toString(Positions.values());
        }


    }

}
