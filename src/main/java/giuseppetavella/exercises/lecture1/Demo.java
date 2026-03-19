package giuseppetavella.exercises.lecture1;

import giuseppetavella.entities.DemoEntity;
import giuseppetavella.functional_interfaces.Adder;

public class Demo {
    static void main(String[] args) {
        // using lamba functions
        Adder adder = (x, y) -> x + y;
        System.out.println(adder.add(1, 2));

        //     using classes with generics
        DemoEntity<Integer> demo1 = new DemoEntity<>();
        System.out.println(demo1.returnSameValue(1));
    }
}
