package ru.arkhipov.MySecondTestAppSpringBoot.model;


import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false, "Разработчик"),
    HR(1.2, false, "HR специалист"),
    TL(2.6, true, "Team Lead"),
    PM(2.4, true, "Project Manager"),
    ANALYST(2.1, false, "Бизнес-аналитик"),
    DESIGNER(1.9, false, "Дизайнер");

    private final double positionCoefficient;
    private final boolean isManager;
    private final String description;

    Positions(double positionCoefficient, boolean isManager, String description){
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
        this.description = description;
    }
}
