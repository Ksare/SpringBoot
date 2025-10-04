package ru.arkhipov.MySecondTestAppSpringBoot.service;


import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        return salary * bonus * 365 * positions.getPositionCoefficient() / workDays;
    }

    public int calculate(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return 366; // Високосный год
        } else {
            return 365; // Невисокосный год
        }
    }
}