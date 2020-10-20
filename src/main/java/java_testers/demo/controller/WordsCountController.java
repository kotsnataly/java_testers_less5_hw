package java_testers.demo.controller;

import java_testers.demo.model.Words;
import java_testers.demo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@RestController
public class WordsCountController {

    @Autowired
    private BooksRepository repository;

    public WordsCountController(BooksRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/books")
    public Words countWords(@RequestBody String titleOfBook, String searchedWords) throws IOException {
        Words searchThese = new Words(searchedWords);
//в этих цикалх, берется каждое слово из строки и забрасывается в поиск по книге, по книге читается каждая строка,
// и если она содержит данное слово, проходим циклом по строке, и увеличиваем счетчик сеттером при совпадении, по каждому i-му элементу мы получим i-е значение
// в массиве int[]; т.е. в итоге после завершения цикла по всему массиву слов получим объект Word в котором через сеттеры
// будет выствален String[] <-> int[] , т.е. слово и количество его вхождений.
        try (InputStream book = new FileInputStream(titleOfBook)) {
            Scanner scanner = new Scanner(book);
            for (int i = 0; i < searchThese.getParsedString().length; i++) {
                int personalCounterForEachWord = 0;
                while (scanner.hasNext()) {
                    String toDevour = scanner.nextLine();
                    String wordWeSearch = searchThese.getParsedString()[i];
                    if (toDevour.contains(wordWeSearch)) {
                        String[] splittedStringForAnalyze = toDevour.split(" ");
                        for (String stripped : splittedStringForAnalyze) {
                            stripped = stripped.replaceAll("[^A-z]+", "");
                            if (stripped.equals(wordWeSearch)) {
                                personalCounterForEachWord++;
                                searchThese.setCounters(personalCounterForEachWord, i);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return searchThese;
    }
}
