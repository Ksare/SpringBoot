package lab1.MyFirstTestAppSpirngBoot.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HashMapController {

    public static HashMap<Integer, String> hashMap = null;
    private AtomicInteger keyCounter = new AtomicInteger(1);

    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam String s) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
            return "Создан ПУСТОЙ HashMap. Значение '" + s + "' не добавлено. Размер: 0";
        } else {
            // ПОСЛЕДУЮЩИЕ ВЫЗОВЫ - добавляем значение
            int key = keyCounter.getAndIncrement();
            hashMap.put(key, s);
            return " Добавлено: [" + key + " = '" + s + "']. Размер: " + hashMap.size();
        }

    }

    @GetMapping("/show-map")
    public String showHashMap() {
        if (hashMap == null) {
            return " HashMap еще не создан.\n Вызовите сначала: /update-map?s=ваше_значение";
        } else if (hashMap.isEmpty()) {
            return " HashMap создан, но пуст";
        } else {
            StringBuilder result = new StringBuilder();
            result.append(" HashMap (всего ").append(hashMap.size()).append(" элементов):\n");

            hashMap.forEach((key, value) -> {
                result.append("  ").append(key).append(" → \"").append(value).append("\"\n");
            });

            return result.toString();
        }
    }
}

