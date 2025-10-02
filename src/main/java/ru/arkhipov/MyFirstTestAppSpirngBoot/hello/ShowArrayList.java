package ru.arkhipov.MyFirstTestAppSpirngBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;


@RestController
public class ShowArrayList {

        private ArrayListController arrayListController;

        public ShowArrayList(ArrayListController arrayListController) {
            this.arrayListController = arrayListController;
        }

        @GetMapping("/show-array")
        public String showArrayList() {

            return "Элементы в ArrayList: " + arrayListController.getStringList();
        }
    }

