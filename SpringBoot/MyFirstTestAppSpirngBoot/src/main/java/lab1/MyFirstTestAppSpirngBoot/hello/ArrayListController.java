package lab1.MyFirstTestAppSpirngBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class ArrayListController {

    public static ArrayList<String> stringList = null;

    // GET-метод updateArrayList(String s)
    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam String s) {
        if (stringList == null) {
            stringList = new ArrayList<>();  // Создаем ПУСТОЙ ArrayList
            return "Создан ПУСТОЙ ArrayList. Размер: 0. Значение '" + s + "' не добавлено.";
        } else {
            stringList.add(s);  // Добавляем значение только при последующих вызовах
            return "Добавлено: '" + s + "'. Размер: " + stringList.size();
        }
    }
    public ArrayList<String> getStringList() {
        return stringList;
    }
}

