package kr.spring.product.vo;

public class ProductVO {
	private int p_no;
	private String p_name;
	private int p_price;
	private int p_amount;
	private byte[] p_image;
	private int p_discount;
	private String p_sub_text;
	private byte[] p_sub_image;
	
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
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_amount() {
		return p_amount;
	}
	public void setP_amount(int p_amount) {
		this.p_amount = p_amount;
	}
	public byte[] getP_image() {
		return p_image;
	}
	public void setP_image(byte[] p_image) {
		this.p_image = p_image;
	}
	public int getP_discount() {
		return p_discount;
	}
	public void setP_discount(int p_discount) {
		this.p_discount = p_discount;
	}
	public String getP_sub_text() {
		return p_sub_text;
	}
	public void setP_sub_text(String p_sub_text) {
		this.p_sub_text = p_sub_text;
	}
	public byte[] getP_sub_image() {
		return p_sub_image;
	}
	public void setP_sub_image(byte[] p_sub_image) {
		this.p_sub_image = p_sub_image;
	}
}
