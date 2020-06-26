package com.aboujihad.vlsmcalc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Calculer {

    public static int puissance(int base,int degr){
        int puissanc=1;
        if(degr==0){
            puissanc=1;
        }else if(degr==1){
            puissanc=base;
        }else{
            puissanc=base;
            for(int i=1;i<degr;i++){

                puissanc=puissanc*base;
            }
        }

        return puissanc;
    }

    public static int[] produitTab(int[] tab1,int[] tab2){
        int[] tab=new int[32];

        for(int i=0;i<tab.length;i++){
            tab[i]=tab1[i]*tab2[i];
        }

        return tab;
    }

    public static int getNbrBitEmprunt(int nbr_de_sous_res){
        int nbrBitEmprunt=0;
        for(int i=0;i<31;i++){
            if(Calculer.puissance(2, i) >= nbr_de_sous_res){
                nbrBitEmprunt=i;
                i=31;
            }
        }

        return nbrBitEmprunt;
    }
    public static int[] SommeTab(int[] tab1,int[] tab2){
        int[] tab=new int[tab1.length];
        for(int i=0;i<tab.length;i++){
            tab[i]=0;
        }
        int taille=tab.length;
        int retenu=0;
        for(int i=taille-1;i>0;i--){
            tab[i]=tab1[i]+tab2[i]+retenu;
            if(tab[i]==2){
                tab[i]=0;
                retenu=1;
            }else if(tab[i]==3){
                tab[i]=1;
                retenu=1;
            }else{
                retenu=0;
            }
        }

        if(tab1[0]==0 && tab2[0]==0){
            tab[0]=0;
        }else{
            tab[0]=1;
        }


        return tab;
    }

    public static int getNbrBitEmpruntInUserId(int nbr_de_Machines){
        int nbrBitEmpruntINUserId=0;
        nbr_de_Machines+=2;
        for(int i=0;i<31;i++){
            if(Calculer.puissance(2, i) >= nbr_de_Machines){
                nbrBitEmpruntINUserId=i;
                i=31;
            }
        }

        return nbrBitEmpruntINUserId;
    }

    public static int[] getNbrOcurrence(int[] Tab){
        int[] tabOccurence = null;


        return tabOccurence;
    }

    public static int[] inverse(int[] tab) {
        int[] tabInv = new int[8];
        for (int i = 7; i != -1; i--) {
            tabInv[i] = tab[7 - i];
        }

        return tabInv;
    }

    static int sumItemsInTab(int[] MachinesInSubNets) {
        int sumItemsInTab=0;
        for(int i=0;i<MachinesInSubNets.length-1;i++){
            sumItemsInTab = MachinesInSubNets[i] + sumItemsInTab;
        }

        return sumItemsInTab;
    }

    static void swapTab(int[] tab,int a,int b){
        int TempVal = tab[a];

        tab[a] = tab[b];
        tab[b] = TempVal;

    }

    static int[] getOrderlyTab(int[] tab){
        int temp;
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab.length-i;j++){
                if(tab[j] < tab[j+1]){
                    temp = tab[j];
                    tab[j] = tab[j+1];
                    tab[j+1] = temp;
                }
            }
        }
//        for(int i=1;i<tab.length;i++){
//            if(tab[i-1] < tab[i]){
//                Calculer.swapTab(tab, i-1, i);
//            }
//        }

        return tab;
    }


    public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


    public static void printMap(Map<String, Integer> map)
    {
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }


    }

}
