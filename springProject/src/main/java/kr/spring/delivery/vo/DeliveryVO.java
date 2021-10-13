package kr.spring.delivery.vo;

import java.sql.Date;

public class DeliveryVO {
	private int delivery_no;
	private int order_no;
	private String d_status_name;
	private Date delivery_date;
	private String dcompany_name;
	private int d_status_num; //JOIN - delivery_status
	
	public int getDelivery_no() {
		return delivery_no;
	}
	public void setDelivery_no(int delivery_no) {
		this.delivery_no = delivery_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getD_status_name() {
		return d_status_name;
	}
	public void setD_status_name(String d_status_name) {
		this.d_status_name = d_status_name;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getDcompany_name() {
		return dcompany_name;
	}
	public void setDcompany_name(String dcompany_name) {
		this.dcompany_name = dcompany_name;
	}
	public int getD_status_num() {
		return d_status_num;
	}
	public void setD_status_num(int d_status_num) {
		this.d_status_num = d_status_num;
	}
	
	@Override
	public String toString() {
		return "DeliveryVO [delivery_no=" + delivery_no + ", order_no=" + order_no + ", d_status_name=" + d_status_name
				+ ", delivery_date=" + delivery_date + ", dcompany_name=" + dcompany_name + ", d_status_num="
				+ d_status_num + "]";
	}
}
