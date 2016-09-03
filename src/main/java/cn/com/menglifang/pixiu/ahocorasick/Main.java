package cn.com.menglifang.pixiu.ahocorasick;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.menglifang.pixiu.ahocorasick.filter.Filter;

public class Main {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Missing parameters ...");
            return;
        }

        File file = new File(args[0]);

        if (!file.exists() || file.isDirectory()) {
            System.out.println(
                    " The file specify does not exist or it is a directory ...");
            return;
        }

        List<String> keywords = new ArrayList<String>();
        for (int i = 1; i < args.length; i++) {
            keywords.add(args[i]);
        }

        if (new Filter(keywords).filter(file)) {
            System.out.println("Found the key word specify");
        } else {
            System.out.println("Does not found the key word specify");
        }

    }

}
