/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchalgorithms;

import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author Mehdi Raza Rajani
 */
public class DFS {
    
    public static void search(Integer[][] graph, Integer size, Integer start, Integer destination, state[] s_list){
        if (Objects.equals(start, destination)) {
            System.out.println("All ready at goal");
            return; 
        }
        Boolean[] isVisited = new Boolean[size];
        for (int i = 0; i < size; i++) {
            isVisited[i] = false;
        }
        Stack<Integer> frontier = new Stack();
        frontier.add(start);
        isVisited[start] = true;
        while(true){
            if (frontier.isEmpty()) {
                System.out.println("(DFS): No such path exists.");
                break;
            }
//            isVisited[frontier.peek()] = true;
            boolean check = true;
            for (int i = 0; i < size; i++) {
                if (graph[frontier.peek()][i] != 0){
                    if (!isVisited[i]){
                        frontier.add(i);
                        isVisited[i] = true;
                        if (Objects.equals(i, destination)) {
                                                        
                            System.out.println("The DFS traversal route from start to end will be as following:");
                            
                            Stack<Integer> reversingStack = new Stack(); //reverse the content of stack
                            while (!frontier.isEmpty()) {                                
                                reversingStack.add(frontier.pop());
                            }
                            Integer totalCost = 0;
                            while (!reversingStack.isEmpty()) {                                
                                System.out.print(s_list[reversingStack.peek()].name + ", ");
                                if (reversingStack.size() > 1 ){
                                    totalCost += graph[reversingStack.pop()][reversingStack.peek()];
                                } else{
                                    reversingStack.pop();
                                }
                            }
                            System.out.println();
                            System.out.println("Total Cost = " + totalCost);
                            return;
                        }
                        check = false;
                        break;
                    }
                }
            }
            if (check){
                frontier.pop();            
            }
        }
    }

}
