package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

@Service
public class QuarterlyBonusService {
    public double calculateQuarterlyBonus(Positions position, double salary, double bonus) {
        // Проверяем, является ли сотрудник менеджером
        if (!position.isManager()) {
            System.out.println("Квартальная премия не положена: " + position.name() + " не является менеджером");
            return 0.0;
        }

        // Расчет квартальной премии (зарплата * бонус * коэффициент позиции)
        double quarterlyBonus = salary * bonus * position.getPositionCoefficient();

        System.out.println("Квартальная премия для " + position.name() + ": " + quarterlyBonus);
        return quarterlyBonus;
    }

    /**
     * Альтернативный метод с более детальным выводом
     */
    public double calculateQuarterlyBonusDetailed(Positions position, double salary, double bonus) {
        if (!position.isManager()) {
            String message = String.format(
                    "Отказ: %s (%s) не имеет права на квартальную премию",
                    position.name(),
                    position.getDescription()
            );
            System.out.println(message);
            return 0.0;
        }

        double quarterlyBonus = salary * bonus * position.getPositionCoefficient();

        String message = String.format(
                "Одобрено: %s (%s) получает квартальную премию: %.2f",
                position.name(),
                position.getDescription(),
                quarterlyBonus
        );
        System.out.println(message);

        return quarterlyBonus;
    }
}
