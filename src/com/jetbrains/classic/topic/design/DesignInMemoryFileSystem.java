package com.jetbrains.classic.topic.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DesignInMemoryFileSystem {
    /*
                    root
               files:  File Name:        e
                       File Contents:   e.content

               dirs:  Subdirectory name        a                    b
                      Subdirectory structure  a.structure        b.structure

          /                                                         \
       a                                                            b
   files: ...
   dirs: ...


ls: In this case, we start off by initializing tt, a temporary directory pointer, to the root directory.
We split the input directory path based on / and obtain the individual levels of directory names in a dd array.
Then, we traverse over the tree directory structure based on the individual directories found and we keep on updating
the tt directory pointer to point to the new level of directory(child) as we go on entering deeper into the directory structure.
At the end, we will stop at either the end level directory or at the file name depending upon the input given. If the last level
in the input happens to be a file name, we simply need to return the file name. So, we directly return the last entry in the dd array.
If the last level entry happens to be a directory, we can obtain its subdirectory list from the list of keys in its dirsdirs hashmap.
Similarly, we can obtain the list of files in the last directory from the keys in the corresponding filesfiles hashmap.
We append the two lists obtained, sort them and return the sorted appended list.

mkdir: In response to this command, as in case of ls, we start entering the directory structure level by level.
Whenever we reach a state where a directory mentioned in the path of mkdir doesn't exist, we create a new entry in the
last valid directory's dirsdirs structure and initialize its subdirectory list as an empty list.
We keep on doing so till we reach the end level directory.

addContentToFile: In response to this command as well, as in case of ls, we start entering the directory structure level by level.
When we reach the level of the file name, we check if the file name already exists in the filesfiles keys. If it exists,
we concatenate the current contents to the contents of the file(in the value section of the corresponding file).
If it doesn't exist, we create a new entry in the current directory's filesfiles and initialize its contents with the current contents.

readContentFromFile: As the previous cases, we reach the last directory level by traversing through the directory structure level by level.
Then, in the last directory, we search for the file name entry in the corresponding filesfiles' keys and return its corresponding value as the contents of the file.

     */
    class Dir {
        HashMap<String, Dir> dirs = new HashMap<>();   // individule name of directory
        HashMap<String, String> files = new HashMap<>();   // individule name of file
    }

    Dir root;

    public DesignInMemoryFileSystem() {
        root = new Dir();
    }

    public List<String> ls(String path) {
        Dir t = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length - 1; ++i) {
                t = t.dirs.get(d[i]);
            }

            // if user query a file
            if (t.files.containsKey(d[d.length - 1])) {
                files.add(d[d.length - 1]);
                return files;
            } else {
                // if user query a directory
                t = t.dirs.get(d[d.length - 1]);
            }
        }

        files.addAll(new ArrayList<>(t.dirs.keySet()));
        files.addAll(new ArrayList<>(t.files.keySet()));
        Collections.sort(files);
        return files;
    }

    public void mkdir(String path) {
        Dir t = root;
        String[] d = path.split("/");

        for (int i = 1; i < d.length; ++i) {
            if (!t.dirs.containsKey(d[i])) {
                t.dirs.put(d[i], new Dir());
            }
            t = t.dirs.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; ++i) {
            t = t.dirs.get(d[i]);
        }
        t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.dirs.get(d[i]);
        }
        return t.files.get(d[d.length - 1]);
    }
}
