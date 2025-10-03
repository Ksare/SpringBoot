package lab1.MyFirstTestAppSpirngBoot.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowAllLenght {

    @GetMapping("/show-all-length")
    public String showAllLength() {
        int arrayListSize = 0;
        int hashMapSize = 0;

        // Получаем размер ArrayList
        if (ArrayListController.stringList != null) {
            arrayListSize = ArrayListController.stringList.size();
        }

        // Получаем размер HashMap
        if (HashMapController.hashMap != null) {
            hashMapSize = HashMapController.hashMap.size();
        }

        return " Количество элементов в коллекциях:\n" +
                "================================\n" +
                " ArrayList: " + arrayListSize + " элементов\n" +
                " HashMap: " + hashMapSize + " элементов\n" +
                "================================\n" +
                " Общее количество: " + (arrayListSize + hashMapSize) + " элементов";
    }


}
