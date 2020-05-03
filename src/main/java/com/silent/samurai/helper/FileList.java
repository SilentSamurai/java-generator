package com.silent.samurai.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class FileList extends ArrayList<String> {

    public static FileList from(Collection<String> list) {
        FileList strings = new FileList();
        strings.addAll(list);
        return strings;
    }

    public static FileList from(String... strings) {
        FileList list = new FileList();
        list.addAll(Arrays.asList(strings));
        return list;
    }

    public static FileList from() {
        return new FileList();
    }

}
