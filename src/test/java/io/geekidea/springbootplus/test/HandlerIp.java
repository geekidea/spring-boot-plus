/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *
 * </p>
 * @auth geekidea
 * @date 2019-05-24
 **/
public class HandlerIp {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = HandlerIp.class.getClassLoader().getResourceAsStream("1.txt");
        InputStream inputStream = new FileInputStream("");
        System.out.println(inputStream);

        Scanner scanner = new Scanner(inputStream);

        File file = new File("/opt/ip.sql");
        System.out.println("file = " + file);
        if (!file.exists()){
            file.createNewFile();
        }

        PrintWriter printWriter = new PrintWriter(file);

        while (scanner.hasNext()){
            String line = scanner.nextLine();
            System.out.println(line);

            Pattern pattern = Pattern.compile("(\\d+.\\d+.\\d+.\\d+)\\s+(\\d+.\\d+.\\d+.\\d+)\\s+(.*)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()){
                String ipStart = matcher.group(1);
                String ipEnd = matcher.group(2);
                String location = matcher.group(3);
                String province = null;
                String city = null;
                if (location != null){
                    int index = location.indexOf(" ");
                    if (index != -1){
                        province = location.substring(0,index);
                        city = location.substring(index);
                    }else{
                        province = location;
                        city = location;
                    }
                }

                if (ipStart != null) ipStart = ipStart.trim();
                if (ipEnd != null) ipEnd = ipEnd.trim();
                if (province != null) province = province.trim();
                if (city != null) city = city.trim();
                if (location != null) location = location.trim();

                System.out.println("ipStart = " + ipStart);
                System.out.println("ipEnd = " + ipEnd);
                System.out.println("province = " + province);
                System.out.println("city = " + city);
                System.out.println("location = " + location);

                String sql = "INSERT INTO ip (ip_start, ip_end, province, city, location) VALUES ('"+ipStart+"', '"+ipEnd+"', '"+province+"', '"+city+"', '"+location+"');";

                printWriter.println(sql);

            }


        }

        printWriter.flush();
        printWriter.close();

    }
}
