package com.lithiumsakura.DSAUnit;

public class Main {
    public static void main(String[] args) {

        MaxCut maxCut = new MaxCut();
        try {
            maxCut.naive();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
