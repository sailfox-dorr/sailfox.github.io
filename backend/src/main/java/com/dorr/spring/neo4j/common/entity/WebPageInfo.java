package com.dorr.spring.neo4j.common.entity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class WebPageInfo<T> implements Serializable {

    private String code;

    /**
     * 提示信息
     */
    private String msg;

    public WebPageInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebPageInfo(List<T> data) {
        this.data = new PageInfo<>(data);
    }

    public WebPageInfo(String code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = new PageInfo<>(data);
    }

    public WebPageInfo(PageInfo<T> data) {
        this.code = "200";
        this.msg = "查询成功";
        this.data = data;
    }

    /**
     * 具体的内容
     */
    private PageInfo<T> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageInfo<T> getData() {
        return data;
    }

    public static <T> WebPageInfo<T> success(List<T> data, Integer pageSize, Integer pageNum) {
        // 结果分页
        PageHelper.startPage(pageNum, pageSize);
        int pageStart = pageNum == 1 ? 0 : (pageNum - 1) * pageSize;
        int pageEnd = data.size() < pageSize * pageNum ? data.size() : pageSize * pageNum;
        List<T> pageResult = new LinkedList<T>();
        if (data.size() > pageStart) {
            pageResult = data.subList(pageStart, pageEnd);
        }
        PageInfo<T> pageInfo = new PageInfo<T>(pageResult);
        //获取PageInfo其他参数
        pageInfo.setTotal(data.size());
        int endRow = pageInfo.getEndRow() == 0 ? 0 : (pageNum - 1) * pageSize + pageInfo.getEndRow() + 1;
        pageInfo.setEndRow(endRow);
        boolean hasNextPage = data.size() <= pageSize * pageNum ? false : true;
        pageInfo.setHasNextPage(hasNextPage);
        boolean hasPreviousPage = pageNum == 1 ? false : true;
        pageInfo.setHasPreviousPage(hasPreviousPage);
        pageInfo.setIsFirstPage(!hasPreviousPage);
        boolean isLastPage = (data.size() > pageSize * (pageNum - 1) && data.size() <= pageSize * pageNum) ? true : false;
        pageInfo.setIsLastPage(isLastPage);
        int pages = data.size() % pageSize == 0 ? data.size() / pageSize : (data.size() / pageSize) + 1;
        pageInfo.setNavigateLastPage(pages);
        int[] navigatePageNums = new int[pages];
        for (int i = 1; i < pages; i++) {
            navigatePageNums[i - 1] = i;
        }
        pageInfo.setNavigatepageNums(navigatePageNums);
        int nextPage = pageNum < pages ? pageNum + 1 : 0;
        pageInfo.setNextPage(nextPage);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPages(pages);
        pageInfo.setPrePage(pageNum - 1);
        pageInfo.setSize(pageInfo.getList().size());
        int starRow = data.size() < pageSize * pageNum ? 1 + pageSize * (pageNum - 1) : 0;
        pageInfo.setStartRow(starRow);

        return new WebPageInfo<>(pageInfo);
    }

    public static <T> WebPageInfo<T> success() {
        return new WebPageInfo("200", "执行成功");
    }

    public void setData(PageInfo<T> data) {
        this.data = data;
    }
}
