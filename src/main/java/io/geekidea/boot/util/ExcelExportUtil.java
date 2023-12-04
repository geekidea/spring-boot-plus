package io.geekidea.boot.util;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Excel导出工具类
 *
 * @author geekidea
 * @date 2022/8/22
 **/
public class ExcelExportUtil {

    private static final String CONTENT_TYPE = "application/vnd.ms-excel";
    private static final String CONTENT_DISPOSITION = "Content-disposition";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String EXCEL_SUFFIX = ".xlsx";


    public static void export(List<?> list, Class<?> headClass, HttpServletResponse response) {
        export(list, headClass, null, null, response);
    }

    public static void export(List<?> list, Class<?> headClass, String exportFileName, HttpServletResponse response) {
        export(list, headClass, exportFileName, null, response);
    }

    public static void export(List<?> list, Class<?> headClass, String exportFileName, String sheetName, HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        ExcelWriter excelWriter = null;
        try {
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            //设置内容水平居中
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            if (StringUtils.isBlank(exportFileName)) {
                String currentTime = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
                String random = RandomStringUtils.randomNumeric(6);
                exportFileName = currentTime + random;
            }
            exportFileName = URLEncoder.encode(exportFileName, CHARACTER_ENCODING);
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + exportFileName + EXCEL_SUFFIX);
            excelWriter = EasyExcel.write(response.getOutputStream())
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerConverter(new LongStringConverter())
                    .build();
            WriteSheet mainSheet = EasyExcel.writerSheet(0, sheetName).head(headClass).build();
            excelWriter.write(list, mainSheet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

}
