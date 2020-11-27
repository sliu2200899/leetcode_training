package com.jetbrains.hard;

public class Search2DMatrix {

    /*
    O(log(m)) = some constant factor times log(m) = c1 * log(m) = log(m^c1)
    O(log(n)) = c2 * log(n) = log(n^c2)
    O(log(m)) + O(log(n)) = log(m^c1) + log(n^c2) = log((m^c1) * (n^c2))
    Assume there is a c > c1,c2
    Thus, O(log(m)) = c * log(m) = log(m^c),
    Similarly O(log(n)) = log(n^c)
    Now, O(log(m)) + O(log(n)) = log((m^c) * (n^c)) = log((m * n)^c) = c * log(m * n) = O(log(m * n))
     */
    public boolean searchMatrixTwoBS(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int n = matrix.length, m = matrix[0].length;

        int rowIndex = binarySearchRow(matrix, 0, n - 1, target);
        if (rowIndex == -1) return false;

        int colIndex = binarySearchCol(matrix, 0, m - 1, rowIndex, target);

        return colIndex != -1;
    }

    private int binarySearchRow(int[][] matrix, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] > target) {
                end = mid - 1;
            } else {
                if (matrix[mid][0] == target) return mid;
                if (mid == matrix.length - 1 || matrix[mid + 1][0] > target) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    private int binarySearchCol(int[][] matrix, int start, int end, int row, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] > target) end = mid - 1;
            else if (matrix[row][mid] < target) start = mid + 1;
            else return mid;
        }

        return -1;
    }

    /*
    Sorted array is a perfect candidate for the binary search because the element index in this virtual array
    (for sure we're not going to construct it for real) could be easily transformed into the row and column
     in the initial matrix

        row = idx / n and col = idx % n.

    The algorithm is a standard binary search :

        Initialise left and right indexes left = 0 and right = m x n - 1.
        While left <= right :
        Pick up the index in the middle of the virtual array as a pivot index : pivot_idx = (left + right) / 2.
        The index corresponds to row = pivot_idx / n and col = pivot_idx % n in the initial matrix, and hence one could get the pivot_element.
        This element splits the virtual array in two parts.
        Compare pivot_element and target to identify in which part one has to look for target.
     */
    public boolean searchMatrixOneBS(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        // binary search
        int start = 0, end = m * n - 1;
        while (start <= end) {
            int midIndex = start + (end - start) / 2;
            int midValue = matrix[midIndex / n][midIndex % n];
            if (target == midValue) return true;
            else {
                if (target < midValue) end = midIndex - 1;
                else start = midIndex + 1;
            }
        }
        return false;
    }




    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int n = matrix.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/ 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[start][0] == target || matrix[end][0] == target) {
            return true;
        } else if (matrix[start][0] > target) {
            return false;
        }

        int row = 0;
        if ((matrix[start][0] < target && matrix[end][0] > target)) {
            row = start;
        } else if ((matrix[end][0] < target)) {
            row = end;
        }

        start = 0;
        end = matrix[row].length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/ 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[row][start] == target || matrix[row][end] == target) {
            return true;
        }

        return false;
    }


    // follow up: search matrix with following properties:
    // 1. Integers in each row are sorted in ascending from left to right.
    // 2. Integers in each column are sorted in ascending from top to bottom.

    /*
        we just need to narrow the search space.
        First, we initialize a (row, col) pointer to the bottom-left of the matrix.[1]
        Then, until we find target and return true we do the following:
            if the currently-pointed-to value is larger than target we can move one row "up".
            Otherwise, if the currently-pointed-to value is smaller than target, we can move one column "right".
            It is not too tricky to see why doing this will never prune the correct answer;
            because the rows are sorted from left-to-right, we know that every value to the right of the current value is larger.
            Therefore, if the current value is already larger than target, we know that every value to its right will also be too large.
            A very similar argument can be made for the columns, so this manner of search will always find target in the matrix (if it is present).
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length, n = matrix[0].length;

        int row = m - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }

        return false;
    }


}
