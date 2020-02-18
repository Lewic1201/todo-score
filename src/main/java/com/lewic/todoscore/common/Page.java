package com.lewic.todoscore.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 */
@Data
public class Page implements Serializable{

	private static final long serialVersionUID = 5790890024078772097L;
	
	//当前页
    private int pageNo =1;
    //总记录数
    private int total;
    //总页数
    private int totalPage;
    //每页多少
    private int pageSize = 10;
    //开始页码
    private int startRow;
    //结束页码
    private int endRow;

    public Page() {
    }

    public Page(Integer pageSize, Integer pageNo) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    public Page(Integer pageSize, Integer pageNo, Integer total) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setTotal(total);
    }

    public void setPageNo(int pageNo) {
        if (pageNo <= 0) pageNo = 1;
        this.pageNo = pageNo;
    }

    public void setTotal(int total) {
        if (total < pageSize) {
            if (pageNo  > 1) pageNo  = 1;
            startRow=0;
            endRow=total;
			totalPage =1;
        }else{
            if(total%pageSize==0){
				totalPage =total/pageSize;
            }else{
				totalPage =total/pageSize+1;
            }
            if(pageNo >= totalPage){
            	startRow=(totalPage -1)*pageSize;
				pageNo = totalPage;
            }else{
            	startRow=(pageNo -1)*pageSize;
            }
            endRow=startRow+pageSize;
        }
        this.total = total;
    }
    
    public void setPageSize(int pageSize) {
        if (pageSize <= 0) pageSize = 10;
        this.pageSize = pageSize;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endRow;
		result = prime * result + pageNo ;
		result = prime * result + pageSize;
		result = prime * result + startRow;
		result = prime * result + total;
		result = prime * result + totalPage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (endRow != other.endRow)
			return false;
		if (pageNo  != other.pageNo )
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (startRow != other.startRow)
			return false;
		if (total != other.total)
			return false;
		if (totalPage != other.totalPage)
			return false;
		return true;
	}

}
