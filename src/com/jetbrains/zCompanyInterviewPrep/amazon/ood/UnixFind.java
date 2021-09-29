package com.jetbrains.zCompanyInterviewPrep.amazon.ood;

import java.util.ArrayList;
import java.util.List;

class File {
    String name;
    int size;
    int type;
    boolean isDirectory;
    File[] children;
}

abstract class Filter {
    abstract boolean apply(File file);
}

class MinSizeFilter extends Filter {

    int minSize;

    public MinSizeFilter(int minSize) {
        this.minSize = minSize;
    }

    @Override
    boolean apply(File file) {
        return file.size > minSize;
    }
}

class TypeFilter extends Filter {

    int type;

    public TypeFilter(int type) {
        this.type = type;
    }

    @Override
    boolean apply(File file) {
        return file.type == type;
    }
}

class FindCommand {

    public List<File> findWithFilters(File directory, List<Filter> filters) {
        if (!directory.isDirectory) {
//            return new NotADirectoryException();
            System.out.println("throw exception....");
        }
        List<File> output = new ArrayList<>();
        findWithFilters(directory, filters, output);
        return output;
    }

    private void findWithFilters(File directory, List<Filter> filters, List<File> output) {
        if (directory.children == null) {
            return;
        }
        for (File file : directory.children) {
            if (file.isDirectory) {
                findWithFilters(file, filters, output);
            } else {
                boolean selectFile = true;
                for (Filter filter : filters) {      // filter condition 1 and 2 should be both satisfied
                    if (!filter.apply(file)) {
                        selectFile = false;
                    }
                }
                if (selectFile) {
                    output.add(file);
                }
            }
        }
    }
}

public class UnixFind {
    public static void main(String[] args) {

    }
}
