package ru.spb.yakovlev;

/*

 **** Создать программу для построения двоичного дерева.
 * В цикле построить двадцать деревьев с глубиной в 6 уровней.
 * Данные, которыми необходимо заполнить узлы деревьев представляются
 * в виде чисел типа int. Число, которое попадает в узел, должно
 * генерироваться случайным образом в диапазоне от -100 до 100.
 * Запустить программу.

 **** Проанализировать, какой процент созданных деревьев является
 * несбалансированными.

*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static int NUMBER_OF_TREES = 20;
    private static int MAX_DEPTH = 6;


    public static void main(String[] args) {

        Random random = new Random();
        List<Tree> trees = new ArrayList<>();

        int returnState = 0;
        for (int i = 0; i < NUMBER_OF_TREES; i++) {
            Tree tree = new Tree();
            while (returnState >= 0) {
                int key = random.nextInt(200) - 100;
                returnState = tree.insert(key);
            }
            returnState = 0;
            trees.add(tree);
        }

        int areBalanced[] = new int[NUMBER_OF_TREES];
        int i = 0;
        for (Tree tree : trees) {
            int minDepth =tree.getMinDepth();
            areBalanced[i] = (MAX_DEPTH - minDepth <= 1) ? 1 : 0;
            System.out.printf("Минимальная глубина дерева %2d: %d\n",++i, minDepth);
        }

        float sum = 0;
        for (int treeBalance : areBalanced) sum += treeBalance;
        System.out.printf("Из %d деревьев сбалансированных %2.1f%%", NUMBER_OF_TREES, (sum * 100 / NUMBER_OF_TREES));
    }


}
