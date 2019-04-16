/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSP;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Mehdi Raza Rajani
 */
public class cspNQueen {
    
    static Integer N = 8;
    static ArrayList<ArrayList<Integer>> spaces = new ArrayList<>(); // 0 - can be placed, 1 filled , -(i+2) cant be placed due to i
    
    
    public static void main(String[] args) {
        
        //initialize the spaces by 0
        for (int i = 0; i < N; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < N; j++)
                spaces.get(i).add(0);
        }

        Random rand = new Random();         
        Stack<Integer> stack = new Stack();
        
        
        for (int i = 0; i < N; i++) {            

            ArrayList<Integer> available = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (spaces.get(i).get(j) == 0)
                    available.add(j);
            }

            if (available.isEmpty()) {
                if (i == 0){
                    System.out.println("No Solution Exists");
                    return;
                }
                
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        if (spaces.get(k).get(l) == -(i))
                            spaces.get(k).set(l, 0);
                    }
                }
                spaces.get(i-1).set(stack.pop(), -i+1);

                System.out.println(stack);
//                for (int x = 0; x < N; x++) {
//                    for (int y = 0; y < N; y++) {
//                        System.out.print(spaces.get(x).get(y) + "\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println("-----------------------");

                i-=2;
                continue;
            }

            int randomIndex = rand.nextInt(available.size()); 
            Integer currentColumnSelected = available.get(randomIndex);
            spaces.get(i).set(currentColumnSelected,1);
            stack.add(currentColumnSelected);

            //set all vertical enteries as -(currentColumnSelected + 2)
            for (int j = i+1; j < N; j++)
                if (spaces.get(j).get(currentColumnSelected) == 0)
                    spaces.get(j).set(currentColumnSelected, -(i + 1));

            //set all leading diagonal enteries as -(currentColumnSelected + 2)
            for (int j = i+1 ; j < N && currentColumnSelected + (j-i) < N; j++) 
                if (spaces.get(j).get(currentColumnSelected + (j-i)) == 0)
                    spaces.get(j).set(currentColumnSelected + (j-i), -(i + 1));

            //set all backward diagonal enteries as -(currentColumnSelected + 2)
            Integer a = currentColumnSelected-1;
            for (int j = i+1 ; j < N && a >=0; j++, a--)
                if (spaces.get(j).get(a) == 0)
                    spaces.get(j).set(a , -(i + 1));    
            
            System.out.println(stack);                                    
        }
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                System.out.print(spaces.get(x).get(y) + "\t");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
        System.out.println(stack);
    }
    
}
