package com.example.functional_programming.start.with;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StartsWithFunc {
  final static List<String> prefectures = List.of("Hokkaido", "Yamagata","Tokyo", "Toyama", "Yamaguchi");

  public StartsWithFunc() {
    Predicate<String> predicateForStartingWithT = name -> name.startsWith("T");
    Predicate<String> predicateForStartingWithY = name -> name.startsWith("Y");

    List<String> prefecturesStartedWithT = prefectures.stream().filter(predicateForStartingWithT).toList();
    List<String> prefecturesStartedWithY = prefectures.stream().filter(predicateForStartingWithY).toList();

    List<String> prefecturesStartedWithTAndY = Stream.concat(prefecturesStartedWithT.stream(), prefecturesStartedWithY.stream()).toList();
    System.out.println("default: " + prefecturesStartedWithTAndY);

    efficientWay1();
    efficientWay2();

    pickName(prefectures, "T");
    pickName2(prefectures, "Y");

    getMaxLength(prefectures);
    getMaxLength2(prefectures);
  }

  void efficientWay1() {
    List<String> prefecturesStartedWithTAndY =
            prefectures.stream().
                    filter(name -> name.startsWith("T") || name.startsWith("Y"))
                    .toList();
    System.out.println("efficientWay1: " + prefecturesStartedWithTAndY);
  }

  final Function<String, Predicate<String>> startWithLetter = (String letter) -> {
    Predicate<String> checkStarts = name -> name.startsWith(letter);
    return checkStarts;
  };

  final Function<String, Predicate<String>> startWithLetter2 =
          letter -> name -> name.startsWith(letter);

  void efficientWay2() {
    List<String> prefecturesStartedWithT =
            prefectures.stream().
                    filter(startWithLetter.apply("T"))
                    .toList();
    List<String> prefecturesStartedWithY =
            prefectures.stream().
                    filter(startWithLetter2.apply("Y"))
                    .toList();
    List<String> prefecturesStartedWithTAndY = Stream.concat(prefecturesStartedWithT.stream(), prefecturesStartedWithY.stream()).toList();
    System.out.println("efficientWay2: " + prefecturesStartedWithTAndY);
  }

  void pickName(final List<String> names, final String startingLetter) {
    String foundName = null;
    for (String name: names) {
      if (name.startsWith(startingLetter)) {
        foundName = name;
        break;
      }
    }

    System.out.printf("A name starting with %s: ", startingLetter);
    System.out.println(Objects.requireNonNullElse(foundName, "No name found"));
  }

  void pickName2(final List<String> names, final String startingLetter) {
    final Optional<String> foundName =
            names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
    System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
  }

  void getMaxLength(final List<String> names) {
    System.out.println(names.stream().mapToInt(String::length).max().getAsInt());
  }

  void getMaxLength2(final List<String> names) {
    names
      .stream()
      .reduce((name1, name2) -> name1.length() > name2.length() ? name1 : name2)
      .ifPresent(name -> System.out.println(name.length()));
  }
}
