package by.dudkoandrei.quizer.task_generators;

import by.dudkoandrei.quizer.tasks.Task;
import by.dudkoandrei.quizer.tasks.Task.Generator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Task.Generator, который позволяет объединить несколько других Task.Generator.
 */
public class GroupTaskGenerator implements Task.Generator {

  private final ArrayList<Generator> generators;

  /**
   * Конструктор с переменным числом аргументов
   *
   * @param generators генераторы, которые в конструктор передаются через запятую
   */
  GroupTaskGenerator(Task.Generator... generators) {
    this(Arrays.asList(generators));
  }

  /**
   * Конструктор, который принимает коллекцию генераторов
   *
   * @param generators генераторы, которые передаются в конструктор в Collection (например,
   *                   {@link ArrayList})
   */
  GroupTaskGenerator(Collection<Generator> generators) {
    if (generators == null) {
      // exception
    }
    if (generators.isEmpty()) {
      // exception
    }
    if (generators.contains(null)) {
      // exception
    }

    this.generators = new ArrayList<>(generators);
  }

  /**
   * @return результат метода generate() случайного генератора из списка. Если этот генератор
   * выбросил исключение в методе generate(), выбирается другой. Если все генераторы выбрасывают
   * исключение, то и тут выбрасывается исключение.
   */
  public Task generate() {
    Collections.shuffle(generators);
    try {
      for (Generator generator : generators) {
        return generator.generate();
      }
    } catch (Exception ignored) {
    }

    throw new RuntimeException("");
  }
}
