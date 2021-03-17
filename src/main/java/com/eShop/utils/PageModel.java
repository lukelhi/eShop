package com.eShop.utils;

public class PageModel {
    //总的页数,通过计算得来
    private int totalSize;
    //总的记录数
    private int recordCount;
    //当前页码
    private int pageIndex;
    //每页大小
    private int pageSize=4;

    //计算总页数
    public int getTotalSize() {
        if(recordCount%pageSize == 0){
            totalSize = recordCount/pageSize;
        }else{
            totalSize = recordCount/pageSize + 1;
        }
        return totalSize;
    }
//    public void setTotalSize(int totalSize) {
//        this.totalSize = totalSize;
//    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getFirstLimitParam(){
        return (this.getPageIndex()-1)*this.getPageSize();//需要使用this调用getPageSize()方法；
    }
}
