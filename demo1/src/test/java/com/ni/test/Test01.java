package com.ni.test;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @ClassName Test01
 * @Description todo
 * @Author zihao.ni
 * @Date 2023/4/20 16:42
 * @Version 1.0
 **/
public class Test01 {
    @Test
    public void testA() throws UnsupportedEncodingException {
        String a = "���������������������";
        String s = new String(a.getBytes(), "UTF-8");
        System.out.println(s);
    }

    @Test
    public void testB() {
        RuntimeException runtimeException = new RuntimeException();
        System.out.println(runtimeException.getMessage());
        System.out.println("====================");
        runtimeException.printStackTrace();
        System.out.println("====================");
        System.out.println(runtimeException.getLocalizedMessage());
    }

    @Test
    public void testFileCreation() {
        LocalDate nowDate = LocalDate.now();
        int day = nowDate.getDayOfMonth();
        int month = nowDate.getMonthValue();
        int year = nowDate.getYear();
        File file = new File(String.format("/tmp/principal/%s-%s/%s-%s-%s", year, month, year, month, day));
        System.out.println(file.exists());
        file.delete();
        System.out.println(file.exists());

        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true, false);
            file.setReadable(true, false);
            file.setExecutable(true, false);
            file.getParentFile().setWritable(true, false);
            file.getParentFile().setReadable(true, false);
            file.getParentFile().setExecutable(true, false);
            file.getParentFile().getParentFile().setWritable(true, false);
            file.getParentFile().getParentFile().setReadable(true, false);
            file.getParentFile().getParentFile().setExecutable(true, false);
        }
    }


    @Test
    public void testRegex() {
        System.out.println(Pattern.matches("[\\x00-\\x1f\\x7f]", ""));

    }

    @Test
    public void reverseString() {
        String a = "fact_transaction_detail_label";
        System.out.println(StringUtils.reverse(a));
    }

    @Test
    public void printFileSeparator() {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }
}