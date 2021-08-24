package com.jetbrains.zCompanyInterviewPrep.amazon;

public class UtilizationCheck {
    public int check(int[] arr, int instances) {

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < 25) {
                if (instances > 1) {
                    instances = (int)Math.ceil(instances / 2);
                    i += 10;
                }
            } else if (arr[i] > 60) {
                if ((instances * 2) < 217) {
                    instances = instances * 2;
                    i += 10;
                }
            }
        }

        return instances;
    }
}
