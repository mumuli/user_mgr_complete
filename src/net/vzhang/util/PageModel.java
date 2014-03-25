package net.vzhang.util;

public class PageModel {
	
	private int page = 1;
	
	private int pageSize = 20;
	
	private long count;
	
	private int pageCount;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page > 0) {
			this.page = page;
		} 
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getPageCount() {
		
		if(this.count % this.pageSize == 0) {
			this.pageCount = (int)(this.count / this.pageSize);
		} else {
			this.pageCount = (int)(this.count / this.pageSize) + 1;
		}
		return pageCount;
	}
}
