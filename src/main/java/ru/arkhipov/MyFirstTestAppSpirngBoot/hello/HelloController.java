package ru.arkhipov.MyFirstTestAppSpirngBoot.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class HelloController {

    private ArrayList<String> stringList = null;

    // GET-метод для обновления массива
    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam String s) {
        if (stringList == null) {
            stringList = new ArrayList<>();
            stringList.add(s);
            return "Создан новый ArrayList. Добавлено: '" + s + "'. Размер: 1";
        } else {
            stringList.add(s);
            return "Добавлено: '" + s + "'. Размер: " + stringList.size();
        }
    }

    // Дополнительный GET-метод для просмотра массива
    @GetMapping("/view-array")
    public String viewArrayList() {
        if (stringList == null) {
            return "Список пуст";
        }
        return "Содержимое списка: " + stringList.toString();
    }

    // Простой тестовый GET-метод
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}

