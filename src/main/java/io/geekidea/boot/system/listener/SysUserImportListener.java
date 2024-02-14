package io.geekidea.boot.system.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import io.geekidea.boot.system.dto.SysUserExcelDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author geekidea
 * @date 2023/12/25
 **/
@Slf4j
public class SysUserImportListener implements ReadListener<SysUserExcelDto> {

    /**
     * 每隔10条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10;
    /**
     * 缓存的数据
     */
    private List<SysUserExcelDto> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    @Override
    public void invoke(SysUserExcelDto data, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
