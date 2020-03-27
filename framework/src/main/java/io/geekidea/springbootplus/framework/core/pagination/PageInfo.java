/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.core.pagination;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义分页参数
 *
 * @author geekidea
 * @date 2020/3/27
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PageInfo<T> extends Page<T> {
	private static final long serialVersionUID = -2211095086394170578L;

	/**
     * 分页参数
     */
    private BasePageParam pageParam;

    /**
     * 默认排序字段信息
     */
    private OrderItem defaultOrderItem;

    /**
     * 排序字段映射
     */
    private OrderMapping orderMapping;


    public PageInfo() {

    }

    /**
     * 传入分页参数
     *
     * @param pageParam
     */
    public PageInfo(BasePageParam pageParam) {
        this(pageParam, null, null);
    }

    /**
     * 传入分页参数，默认排序
     *
     * @param basePageParam
     * @param defaultOrderItem
     */
    public PageInfo(BasePageParam basePageParam, OrderItem defaultOrderItem) {
        this(basePageParam, defaultOrderItem, null);
    }

    /**
     * 传入分页参数，排序字段映射
     *
     * @param pageParam
     * @param orderMapping
     */
    public PageInfo(BasePageParam pageParam, OrderMapping orderMapping) {
        this(pageParam, null, orderMapping);
    }

    /**
     * 传入分页参数，默认排序，排序字段映射
     *
     * @param pageParam
     * @param defaultOrderItem
     * @param orderMapping
     */
    public PageInfo(BasePageParam pageParam, OrderItem defaultOrderItem, OrderMapping orderMapping) {
        this.pageParam = pageParam;
        this.defaultOrderItem = defaultOrderItem;
        this.orderMapping = orderMapping;
        this.handle();
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public PageInfo(long current, long size) {
        super(current, size, 0);
    }

    public PageInfo(long current, long size, long total) {
        super(current, size, total, true);
    }

    public PageInfo(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public PageInfo(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }

    /**
     * 如果是pageParam是OrderPageParam，并且不为空，则使用前端排序
     * 否则使用默认排序
     */
    private void handle() {
        if (pageParam == null) {
            return;
        }
        // 设置pageIndex/pageSize
        super.setCurrent(pageParam.getPageIndex());
        super.setSize(pageParam.getPageSize());
        // 排序字段处理
        BasePageOrderParam basePageOrderParam = (BasePageOrderParam) pageParam;
        List<OrderItem> orderItems = basePageOrderParam.getPageSorts();
        if (CollectionUtils.isEmpty(orderItems)) {
            setDefaultOrder(defaultOrderItem);
            return;
        }
        if (orderMapping == null) {
            orderMapping = new OrderMapping(true);
        }
        orderMapping.filterOrderItems(orderItems);
        super.setOrders(orderItems);
    }

    /**
     * 设置默认排序
     *
     * @param defaultOrderItem
     * @return
     */
    public PageInfo<T> setDefaultOrder(OrderItem defaultOrderItem) {
        if (defaultOrderItem != null) {
            this.defaultOrderItem = defaultOrderItem;
            super.setOrders(Arrays.asList(defaultOrderItem));
        }
        return this;
    }

}
