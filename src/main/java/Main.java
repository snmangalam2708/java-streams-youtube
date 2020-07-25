import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach ❌

   List<Person> females = new ArrayList<>();
    for(Person person: people){

      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }

    females.forEach(System.out::println);
    System.out.println("\n");




    // Declarative approach ✅

    // Filter
    List<Person> females_filter = people.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .collect(Collectors.toList());

    females_filter.forEach(System.out::println);
    System.out.println("\n");

    // Sort

    List<Person> females_sort = people.stream()
            .sorted(Comparator.comparing(Person::getAge).reversed())
            .collect(Collectors.toList());

    females_sort.forEach(System.out::println);
    System.out.println("\n");


    // All match

    boolean age_greater_5 = people.stream()
            .allMatch(person -> person.getAge()>5);

    System.out.println(age_greater_5+"\n");

    // Any match

    boolean name_has_james = people.stream()
            .anyMatch(person -> person.getName().equals("Jamie Goa"));

    System.out.println(name_has_james+"\n");


    // None match

    boolean age_greater_150 = people.stream()
            .noneMatch(person -> person.getAge()>108);

    System.out.println(age_greater_150);
      System.out.println();

    // Max

      Optional<Person> max = people.stream()
              .max(Comparator.comparing(Person::getAge));
//              .ifPresent(System.out::println);
      System.out.println(max);
      System.out.println();
   

    // Min

      Optional<Person> min = people.stream()
              .min(Comparator.comparing(Person::getAge));
//              .ifPresent(System.out::println);
      System.out.println(min);
      System.out.println();


    // Group

      Map<Gender, List<Person>> groups = people.stream()
              .collect(Collectors.groupingBy(Person::getGender));

      groups.forEach((gender, people1) -> {
          System.out.println(gender);
          people1.forEach(System.out::println);
          System.out.println();
      });

      //oldest female and print name//

     Optional<String> oldest_female_age =  people.stream()
              .filter(person -> person.getGender().equals(Gender.FEMALE))
              .max(Comparator.comparing(Person::getAge)).map(Person::getName);

      oldest_female_age.ifPresent(System.out::println);
      System.out.println();
  }



  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }

}
