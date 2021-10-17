package kr.spring.delivery.vo;

public class DeliveryVO {
	private int delivery_no;
	private String order_no;
	private int d_status_num;
	private String tracking_num;
	private String dcompany_name;
	private String d_status_name; //JOIN - delivery_status
	private String mem_id; //JOIN - member
	
	public int getDelivery_no() {
		return delivery_no;
	}
	public void setDelivery_no(int delivery_no) {
		this.delivery_no = delivery_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public int getD_status_num() {
		return d_status_num;
	}
	public void setD_status_num(int d_status_num) {
		this.d_status_num = d_status_num;
	}
	public String getTracking_num() {
		return tracking_num;
	}
	public void setTracking_num(String tracking_num) {
		this.tracking_num = tracking_num;
	}
	public String getDcompany_name() {
		return dcompany_name;
	}
	public void setDcompany_name(String dcompany_name) {
		this.dcompany_name = dcompany_name;
	}
	public String getD_status_name() {
		return d_status_name;
	}
	public void setD_status_name(String d_status_name) {
		this.d_status_name = d_status_name;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	@Override
	public String toString() {
		return "DeliveryVO [delivery_no=" + delivery_no + ", order_no=" + order_no + ", d_status_num=" + d_status_num
				+ ", tracking_num=" + tracking_num + ", dcompany_name=" + dcompany_name + ", d_status_name="
				+ d_status_name + ", mem_id=" + mem_id + "]";
	}
}