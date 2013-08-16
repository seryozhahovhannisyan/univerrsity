package com.university.data_access.util;

import com.university.web.util.ConstantGeneral;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 03.08.13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {

        String filePath = "/data/DIPLOMA/COLLEGE/Hydrangeas.jpg";
        filePath = filePath.replaceFirst(ConstantGeneral.MAIN_PATH,"");

        System.out.println(filePath);

        System.out.println(1 % 10);
        System.out.println(2 % 10);
        System.out.println(3 % 10);
        System.out.println(4 % 10);
        System.out.println();
        System.out.println(11 % 10);
        System.out.println(12 % 10);
        System.out.println(21 % 10);
        System.out.println(21 % 10);
        System.out.println();
        System.out.println(33 % 10);
        System.out.println(34 % 10);
        System.out.println(43 % 10);
        System.out.println(44 % 10);
    }

    private static void setFileName() {
        String fileName = "lastFileName.png";

        int indexOfDot = fileName.lastIndexOf('.');
        String newFileName = "newFileName";
        newFileName += fileName.substring(indexOfDot);
        System.out.println(newFileName);
    }

    private static void createPackagesTest() {
        List<String> packages = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            String pn = "" + i;
            packages.add(pn);
        }
        System.out.println(createPackages(packages));
    }

    public static String createPackages(List<String> packages) {
        String path = "";
        for (String pcg : packages) {
            path += pcg + ConstantGeneral.FILE_SEPARATOR;
        }

        path = path.substring(0, path.lastIndexOf(ConstantGeneral.FILE_SEPARATOR));
        return path;
    }

    public static void replace() {
        String mainPackageName = "878787878 .@.@";
        mainPackageName.trim();
        mainPackageName = mainPackageName.replace(" ", "_");
        mainPackageName = mainPackageName.replace("@", "_");
        String re = mainPackageName.replace(".", "_");
        System.out.println(mainPackageName);
        System.out.println(re);
    }
}
