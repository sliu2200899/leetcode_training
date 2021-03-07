package com.jetbrains.classic.searchByStructure.sequentialSearch;

public class SqrtMath {

    // calculator in reality:  e ^ (1/2 * log x)
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }

    // use binary search
    // Set the left boundary to 2, and the right boundary to x / 2.
    public int mySqrt2(int x) {
        if (x <= 1) return x;
        // find the last element that meet the condition that num * num < x
        int start = 1, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((long)mid * mid > x) {
                end = mid - 1;
            } else if ((long)mid * mid == x) {
                return mid;
            } else {
                if ((long)(mid + 1) * (mid + 1) > x) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            }
        }

        return -1;
    }

    //newton's method
    //refer to:  https://en.wikipedia.org/wiki/Newton%27s_method
    /*
    The idea is to start with an initial guess which is reasonably close to the true root, then to approximate the function by its tangent line using calculus,
    and finally to compute the x-intercept of this tangent line by elementary algebra. This x-intercept will typically be a better approximation to the original function's root than the first guess,
    and the method can be iterated.
    refer to: https://developer.aliyun.com/article/655109
    refer to:  https://www.math.upenn.edu/~kazdan/202F09/sqrt.pdf
     */

    public int mySqrt3(int x) {
        long i = x;
        while (i * i > x) {
            i = (i + x / i) / 2;
        }
        return (int)i;
    }
}
