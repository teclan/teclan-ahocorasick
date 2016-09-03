package cn.com.menglifang.pixiu.ahocorasick.filter;

import java.io.File;
import java.util.List;

import cn.com.menglifang.pixiu.ahocorasick.trie.Trie;
import cn.com.menglifang.pixiu.ahocorasick.trie.Trie.TrieBuilder;

public class Filter {
    private Trie           trie;
    private static boolean isWindows = false;

    static {
        isWindows = System.getProperty("os.name").toLowerCase()
                .contains("windows");
    }

    public Filter(List<String> keywords) {
        trie = generateTrie(keywords);
    }

    public Filter(List<String> keywords, boolean caseInsensitive) {
        trie = generateTrie(keywords, caseInsensitive);
    }

    public boolean filter(File file) {

        if (isWindows) {
            return FileContent.found(file, trie, "GBK")
                    || FileContent.found(file, trie, "UTF-8");

        } else {
            return FileContent.found(file, trie, "UTF-8")
                    || FileContent.found(file, trie, "GBK");
        }

    }

    private Trie generateTrie(List<String> keywords) {
        return generateTrie(keywords, false);
    }

    private Trie generateTrie(List<String> keywords, boolean caseInsensitive) {
        TrieBuilder builder = Trie.builder();
        if (caseInsensitive) {
            builder.caseInsensitive();
        }
        for (String keyword : keywords) {
            builder.addKeyword(keyword);
        }
        return builder.build();
    }
}
