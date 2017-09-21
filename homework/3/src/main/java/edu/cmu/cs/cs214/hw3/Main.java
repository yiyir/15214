package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        CommandInterface<Integer> impl = new PermutationCommand<Integer>(list);
        List<List<Integer>> ourResult=impl.generate();
       System.out.println(ourResult);
        //for (int i = 0 ; i < ourResult.size(); i++) {
          //  System.out.println(ourResult.get(i));
        //}
        
        
    }
}
