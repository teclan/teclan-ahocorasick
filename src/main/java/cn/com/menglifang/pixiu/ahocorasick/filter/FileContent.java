package cn.com.menglifang.pixiu.ahocorasick.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.menglifang.pixiu.ahocorasick.emit.Emit;
import cn.com.menglifang.pixiu.ahocorasick.trie.Trie;

public class FileContent {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FileContent.class);

    // 使用tika parse() 时默认最大读取字节长度 512MB
    private static final int  DEFAULT_WRITE_LIMIT = 512 * 1024 * 1024;
    // 指定编码读取文件时如果文件大于100MB时每次读取100MB，否则读取实际长度
    private static final long DEFAUL_READ_LENGTH  = 100 * 1024 * 1024;
    private static int        writeLimit          = DEFAULT_WRITE_LIMIT;

    public static boolean found(File file, Trie trie, String encoding) {
        InputStreamReader read = null;
        Collection<Emit> emits = null;

        long length = file.length();
        char[] content;

        try {
            read = new InputStreamReader(new FileInputStream(file), encoding);

            if (DEFAUL_READ_LENGTH > length) {
                content = new char[(int) file.length()];
                read.read(content, 0, (int) length);

                emits = trie.parseText(new String(content));

                return !emits.isEmpty();
            } else {

                int m = (int) (length / DEFAUL_READ_LENGTH);

                for (int i = 0; i <= m; i++) {
                    content = new char[(int) DEFAUL_READ_LENGTH];
                    read.read(content);
                    emits = trie.parseText(new String(content));

                    if (!emits.isEmpty()) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                read.close();
            } catch (IOException e) {
            }
        }
        return false;

    }

    public static String parse(File file, String encoding) {
        InputStreamReader read = null;

        long length = file.length();
        char[] content;

        try {
            read = new InputStreamReader(new FileInputStream(file), encoding);
            if (length < DEFAUL_READ_LENGTH) {
                content = new char[(int) file.length()];
                read.read(content, 0, (int) length);
                return new String(content);
            } else {
                LOGGER.warn(
                        "The file specify is bigger than 100MB,this method is not support,please change to use the method named \"found(File file, Trie trie, String encoding)\":{}",
                        file.getAbsoluteFile());
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                read.close();
            } catch (IOException e) {
            }
        }
        return "";
    }

    /**
     * 设置默认最大读取字节长度为指定字节
     * 
     * @param newWriteLimit
     */
    public static void setWriteLimit(int newWriteLimit) {
        writeLimit = newWriteLimit;
    }

    /**
     * 设置默认最大读取字节长度为 512MB
     */
    public static void setWriteLimit() {
        writeLimit = DEFAULT_WRITE_LIMIT;
    }

}
