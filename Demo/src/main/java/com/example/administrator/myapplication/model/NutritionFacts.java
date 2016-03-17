package com.example.administrator.myapplication.model;

/**
 * Created by Administrator on 2015/12/14.
 * 建造者模式
 */
public class NutritionFacts {
    private int fat;
    private int sodium;
    private int serviceSize;


    public NutritionFacts(Builder builder) {
        fat = builder.fat;
        serviceSize = builder.serviceSize;
        sodium = builder.sodium;
    }


    public static class Builder {
        private int fat = 0;
        private int sodium = 0;
        private int serviceSize = 0;


        public Builder(int serviceSize, int sodium) {
            this.serviceSize = serviceSize;
            this.sodium = sodium;
        }


        public Builder fat(int val) {
            fat = val;
            return this;
        }


        public Builder sodium(int val) {
            sodium = sodium;
            return this;
        }


        public NutritionFacts Builder() {
            return new NutritionFacts(this);
        }
    }
}
