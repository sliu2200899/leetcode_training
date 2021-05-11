package com.jetbrains.classic.topic.mathBit;

import java.util.HashMap;
import java.util.Map;

public class ReverseBits {
    /*
        this problem is is hte same as the number of 1 bits....
     */
    /*
        reverse bit by bit

        notice that, we cannot use base-2 % / operation to reverse like
            ans = ans * 2 + n % 2;
            n /= 10
        you should use bit operators:
            ans = (ans << 1) | (n & 1)
            n >>= 1

        time: O(logn)
        space: O(1)
     */

    public int reverseBits(int n) {
        int num = 0;
        for (int i = 0; i < 32; ++i) {
            num += (n & 1);
            n >>>= 1;   // must do unsigned shift
            if (i < 31) {   // for last digit, don't shift
                num <<= 1;
            }
        }

        return num;
    }

    /*
        follow up;
            How to optimize if this function is called multiple times? We can divide an int into 4 bytes,
            and reverse each byte then combine into an int. For each byte, we can use cache to improve performance
     */
     private final Map<Byte, Integer> cache = new HashMap<>();
    public int reverseBits2(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; ++i) {
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        }
        int result = 0;
        for (int i = 0; i < 4; ++i) {
            result += reverseByte(bytes[i]);
            if (i < 3) {
                result <<= 8;
            }
        }

        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b);
        if (value != null) {
            return value;
        }

        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; ++i) {
            value += ((b >>> i) & 1);
            if (i < 7) {
                value <<= 1;
            }
        }
        cache.put(b, value);
        return value;
    }
}
