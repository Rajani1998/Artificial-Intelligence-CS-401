/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Mehdi Raza Rajani
 */

public class geneticNQueen {
    static final int C = 4;
    static final int N = 8;    
    static ArrayList<Chromosome> population;
    
    public static void main(String args[]) {
        population = new ArrayList<>(C);
        for (int i = 0; i < C; i++) {
            population.add(new Chromosome());
        }
        System.out.println("Initial");
        print();
        Integer k = 0;
        while (!(population.get(0).fitnessValue == 0 || population.get(1).fitnessValue == 0 || population.get(2).fitnessValue == 0 || population.get(3).fitnessValue == 0)){
            crossOver();
            System.out.println("Crossover " + k);
            print();
            for (int i = 0; i < C; i++)
                population.get(i).mutation();
            System.out.println("Mutation " + k);
            print();
            k++;
        }
    }
    
    static void crossOver(){
        Collections.sort(population);
        int randomIndex1 = (int) (Math.random() * N - 1); //-1 to fix minimum 1st gene
        int randomIndex2 = (int) (Math.random() * N - 1); //-1 to fix minimum 1st gene
        
        Chromosome c1 = new Chromosome() ,
                   c2 = new Chromosome() ,
                   c3 = new Chromosome() ,
                   c4 = new Chromosome();

        for(int i = 0 ; i < randomIndex1 ; i++ ){
            c1.genes.set(i, population.get(0).genes.get(i));
            c2.genes.set(i, population.get(1).genes.get(i));
        }
        
        for (int i = randomIndex1 ; i < N; i++) {
            c1.genes.set(i, population.get(1).genes.get(i));
            c2.genes.set(i, population.get(0).genes.get(i));            
        }
        
        for(int i = 0 ; i < randomIndex2 ; i++ ){
            c3.genes.set(i, population.get(1).genes.get(i));
            c4.genes.set(i, population.get(2).genes.get(i));
        }
        
        for (int i = randomIndex2 ; i < N; i++) {
            c3.genes.set(i, population.get(2).genes.get(i));
            c4.genes.set(i, population.get(1).genes.get(i));            
        }
        
        c1.calculateFitnesValue();
        c2.calculateFitnesValue();
        c3.calculateFitnesValue();
        c4.calculateFitnesValue();
        
        population.set(0, c1);
        population.set(1, c2);
        population.set(2, c3);
        population.set(3, c4);
        
    }
    
    static void print(){
        for (int i = 0; i < C; i++) {
            System.out.println(population.get(i));
        }
    }
    
}

class Chromosome implements Comparable{
    ArrayList <Integer> genes = new ArrayList<>(geneticNQueen.N);
    Integer fitnessValue;

    public Chromosome() {
        for (int i = 0; i < geneticNQueen.N; i++)
            genes.add((int)(Math.random() * genes.size() + 1));
        calculateFitnesValue();
    }    
    
    void calculateFitnesValue(){
        int temp;
        int diff;
        int diffm;
        int conflict = 0;
        boolean front = false;
        boolean diagonal = false;
        boolean diagonalup = false;

        for (int j = 0; j < genes.size(); j++) {
            int queen = j;
            diffm = 0;
            for (int k = queen + 1; k < genes.size(); k++) {
                temp = k - queen;
                diff = genes.get(queen) - genes.get(k);                
                if (diff < 0) {
                    diffm = diff * (-1);
                }
                if (diff == 0 && front == false) {
                    front = true;
                    conflict++;
                }
                if (temp == diff && diagonal == false) {
                    diagonal = true;
                    conflict++;
                }
                if (temp == diffm && diagonalup == false) {
                    diagonalup = true;
                    conflict++;
                }
            }
            front = false;
            diagonal = false;
            diagonalup = false;
        }
        fitnessValue = conflict;
    }

    void mutation(){
        if (fitnessValue == 0)
            return;
        Set<Integer> occuredSet = new HashSet<>();
        boolean isOccured[] = new boolean[genes.size()+1];
        ArrayList<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < genes.size() ; i++)
            occuredSet.add(genes.get(i));
        for (int i = 1; i < genes.size() + 1; i++) 
            isOccured[i] = false;        
        for (int i = 1; i < genes.size() + 1; i++) {
            if (!occuredSet.contains(i))
                remaining.add(i);
        }
        if (remaining.isEmpty()) {
            int index1 = (int) (Math.random() * genes.size());
            int index2 = (int) (Math.random() * genes.size());
            while (index1 == index2)
                index2 = (int) (Math.random() * genes.size());
            int temp = genes.get(index1);
            genes.set(index1, genes.get(index2));
            genes.set(index2, temp);
        }
        else {
            for (int i = 0; i < genes.size(); i++) {
                if (isOccured[genes.get(i)]){
                    int randomIndex = (int) (Math.random() * remaining.size());
                    genes.set(i, remaining.get(randomIndex));
                    isOccured[remaining.get(randomIndex)] = true;
                    remaining.remove(randomIndex);
                }
                else 
                    isOccured[genes.get(i)] = true;
            }            
        }
    }
    
    @Override
    public String toString() {
        return "Genes: " + genes.toString() + " FitnessValue: " + fitnessValue;
    }
    
    @Override
    public int compareTo(Object comparestu) {
        int compFV=((Chromosome)comparestu).fitnessValue;
        /* For Descending order do like this */
        return compFV-this.fitnessValue;
    }    
    
}