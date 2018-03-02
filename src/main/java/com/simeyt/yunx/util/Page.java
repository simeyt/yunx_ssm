package com.simeyt.yunx.util;

public class Page {
    private int start;//开始页数
    private int count;//每页显示
    private int total;//总共有多少数据
    private String param;//参数
    private static final int defaultCount = 5;//默认每页5个数据

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Page() {
        count = defaultCount;//默认构造每页5个数据
    }
    public Page(int start,int count){
        this();
        this.start = start;
        this.count = count;
    }
    public boolean isHasPreviouse(){
        if(start == 0)
            return false;
        return true;
    }
    public boolean isHasNext(){

        return true;
    }
    public int getTotalPage(){//计算出总共有多少页
        int totalPage;
        //假设总数50,50%5=0 有10页 ，假设总数51，51%5=1 有11页
        if(total % count == 0){
            totalPage = total / count;
        }
        else totalPage = (total / count) + 1;
        if(totalPage == 0){
            totalPage = 1;//假设总数是0 还是一页
        }
        return totalPage;
    }

    public int getLast(){//计算出最后一页的数值是多少
        int last;
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        if(total % count == 0){
            last = total - count ;
        }
        else //假设总数51，最后一页就是50开始
            last = total - total % count;
        last = last<0?0:last;
        return last;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }
}
