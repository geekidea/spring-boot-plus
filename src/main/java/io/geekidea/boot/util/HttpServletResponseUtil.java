package io.geekidea.boot.util;

import com.alibaba.fastjson2.JSON;
import io.geekidea.boot.framework.response.ApiResult;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author geekidea
 * @date 2018-11-08
 */
public final class HttpServletResponseUtil {

    private static String UTF8 = "UTF-8";
    private static String CONTENT_TYPE = "application/json";

    private HttpServletResponseUtil() {
        throw new AssertionError();
    }


    public static void printFailJsonMessage(HttpServletResponse response, String errorMessage) {
        ApiResult apiResult = ApiResult.fail(errorMessage);
        printJson(response, apiResult);
    }

    public static void printJson(HttpServletResponse response, Object object) {
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(object));
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    public static void print(HttpServletResponse response, String string) {
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(string);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

}
