package kr.spring.order.vo;

import java.sql.Date;

import javax.validation.constraints.Size;

public class OrderListVO {
	private String order_no;
	private int mem_num;
	private Date order_date;
	private int p_no;
	private String p_name;
	private Byte[] p_image;
	private int p_image_name;
	private int p_cnt;
	private String d_status_name;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Byte[] getP_image() {
		return p_image;
	}
	public void setP_image(Byte[] p_image) {
		this.p_image = p_image;
	}
	public int getP_image_name() {
		return p_image_name;
	}
	public void setP_image_name(int p_image_name) {
		this.p_image_name = p_image_name;
	}
	public int getP_cnt() {
		return p_cnt;
	}
	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}
	public String getD_status_name() {
		return d_status_name;
	}
	public void setD_status_name(String d_status_name) {
		this.d_status_name = d_status_name;
	}
	@Override
	public String toString() {
		return "OrderListVO [order_no=" + order_no + ", mem_num=" + mem_num + ", order_date=" + order_date + ", p_no="
				+ p_no + ", p_name=" + p_name + ", p_image_name=" + p_image_name + ", p_cnt=" + p_cnt
				+ ", d_status_name=" + d_status_name + "]";
	}
	
}
