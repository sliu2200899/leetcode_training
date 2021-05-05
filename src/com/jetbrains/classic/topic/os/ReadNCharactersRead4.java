package com.jetbrains.classic.topic.os;

public class ReadNCharactersRead4 {
    /*
        Back to the problem, the question is "how does the memory work":
        refer to:  how ddr memory works?   https://hexus.net/tech/tech-explained/ram/702-ddr-ii-how-it-works/

        Because of the physical implementation, loading 4 bytes in DDR is faster than to load 1 byte 4 times.

        On the majority of computers today, collection of 4 bytes, or 32 bits, is called a word. Most modern CPUs are optimized for the operations with words.

        To sum up, the problem is a practical low-level question. The standard approach (Approach 1) to solve it using the internal buffer of 4 characters:

        File -> Internal Buffer of 4 Characters -> Buffer of N Characters.
            file  ---->    buf4  ---->   buf

        when using c/c++, we can optimized the program
            file  ---->    buf
     */

    /*
        we need to deal with two situations
            1. file content(number of characters in file) is larger than n   => we would check copiedChars == n when coping
            2. file content(number of characters in file) is smaller than n   =>  check readChars == 4 every time we call read4()
     */
    public int read(char[] buf, int n) {
        int copiedChars = 0, readChars = 4;
        char[] buf4 = new char[4];   //temp interal buffer

        while(copiedChars < n && readChars == 4) {
            readChars = read4(buf4);

            for (int i = 0; i < readChars; ++i) {
                if (copiedChars == n) {
                    return copiedChars;
                }

                buf[copiedChars++] = buf4[i];
            }
        }

        return copiedChars;
    }

    private int read4(char[] dest) {
        return 0;
    }

    /*
        follow up:  how to deal with calling multiple times

        what's difference between call once and call multiple times?
            Think that you have 4 chars "a, b, c, d" in the file, and you want to call your function twice like this:

            read(buf, 1); // should return 'a'
            read(buf, 3); // should return 'b, c, d'
            All the 4 chars will be consumed in the first call. So the tricky part of this question is how can you preserve the remaining 'b, c, d' to the second call.


        the key point is to used buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls
     */
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read2(char[] buf, int n) {
        int ptr = 0;   // curr pointer for return
        while (ptr < n) {   // each round for reading buffer
            if (buffPtr == 0) {   // means that we don't have any left characters in the previous read
                buffCnt = read4(buff);
            }
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            // all chars in buff used up, set pointer to 0
            if (buffPtr == buffCnt) buffPtr = 0;
            // read4 returns less than 4, end of file
            if (buffCnt < 4) break;
        }
        return ptr;
    }

}
