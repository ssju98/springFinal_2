package kr.spring.review.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import kr.spring.util.StringUtil;

public class ReviewVO {
	private int review_no; //리뷰 번호
	private int p_no; 
	private String review_content; //내용
	private String order_no; 
	private byte[] review_image; //업로드 파일
	private String review_image_name; //파일
	private int mem_num; //회원 번호
	private int review_rating; 
	private Date regdate;
	private String p_name;
	private String p_image_name;
	private byte[] p_image;
	private String mem_name;	//join
	
	
	public void setUpload(MultipartFile upload) throws IOException {
	//MultipartFile -> byte[]
	setReview_image(upload.getBytes());
	//파일명 구하기
	setReview_image_name(upload.getOriginalFilename());
		}

	
	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


	public String getP_image_name() {
		return p_image_name;
	}

	public void setP_image_name(String p_image_name) {
		this.p_image_name = p_image_name;
	}

	public byte[] getP_image() {
		return p_image;
	}

	public void setP_image(byte[] p_image) {
		this.p_image = p_image;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getReview_no() {
		return review_no;
	}
	
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public byte[] getReview_image() {
		return review_image;
	}
	public void setReview_image(byte[] review_image) {
		this.review_image = review_image;
	}
	public String getReview_image_name() {
		return review_image_name;
	}
	public void setReview_image_name(String review_image_name) {
		this.review_image_name = review_image_name;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public int getReview_rating() {
		return review_rating;
	}

	public void setReview_rating(int review_rating) {
		this.review_rating = review_rating;
	}


	/*
	 * @Override public String toString() { return "ReviewVO [review_no=" +
	 * review_no + ", p_no=" + p_no + ", review_content=" + review_content +
	 * ", order_no=" + order_no + ", review_image_name=" + review_image_name +
	 * ", mem_num=" + mem_num + ", review_rating=" + review_rating + ", p_name=" +
	 * p_name + ", p_image_name=" + p_image_name + ", mem_name=" + mem_name + "]"; }
	 */
	
}
