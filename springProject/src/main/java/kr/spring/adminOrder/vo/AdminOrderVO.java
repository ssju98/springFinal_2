package kr.spring.adminOrder.vo;

import java.sql.Date;

public class AdminOrderVO {
	private int order_no;
	private int mem_num;
	private String order_zipcode;
	private String order_address1;
	private String order_address2;
	private int order_method;
	private Date order_date;
	private int order_pay;
	private String mem_id; //JOIN - member
	private String mem_name; //JOIN - member_detail
	private String mem_phone; //JOIN - member_detail
	private String d_status_name; //JOIN - delivery_status

	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getOrder_zipcode() {
		return order_zipcode;
	}
	public void setOrder_zipcode(String order_zipcode) {
		this.order_zipcode = order_zipcode;
	}
	public String getOrder_address1() {
		return order_address1;
	}
	public void setOrder_address1(String order_address1) {
		this.order_address1 = order_address1;
	}
	public String getOrder_address2() {
		return order_address2;
	}
	public void setOrder_address2(String order_address2) {
		this.order_address2 = order_address2;
	}
	public int getOrder_method() {
		return order_method;
	}
	public void setOrder_method(int order_method) {
		this.order_method = order_method;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getOrder_pay() {
		return order_pay;
	}
	public void setOrder_pay(int order_pay) {
		this.order_pay = order_pay;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getD_status_name() {
		return d_status_name;
	}
	public void setD_status_name(String d_status_name) {
		this.d_status_name = d_status_name;
	}

	@Override
	public String toString() {
		return "AdminOrderVO [order_no=" + order_no + ", mem_num=" + mem_num + ", order_zipcode=" + order_zipcode
				+ ", order_address1=" + order_address1 + ", order_address2=" + order_address2 + ", order_method="
				+ order_method + ", order_date=" + order_date + ", order_pay=" + order_pay + ", mem_id=" + mem_id
				+ ", mem_name=" + mem_name + ", mem_phone=" + mem_phone + ", d_status_name=" + d_status_name + "]";
	}
}
