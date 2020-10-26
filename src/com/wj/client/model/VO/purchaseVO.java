package com.wj.client.model.VO;

public class purchaseVO {
	private String purchaseCode;
	private String buyerId;
	private String ProductCode;
	private String quantity;
	private int price;
	private String purchaseDate;
	private String purchaseType;
	private String status;
	
	public purchaseVO() {}

	public purchaseVO(String purchaseCode, String buyerId, String productCode, String quantity, int price,
			String purchaseDate, String purchaseType, String status) {
		this.purchaseCode = purchaseCode;
		this.buyerId = buyerId;
		this.ProductCode = productCode;
		this.quantity = quantity;
		this.price = price;
		this.purchaseDate = purchaseDate;
		this.purchaseType = purchaseType;
		this.status = status;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getProductCode() {
		return ProductCode;
	}

	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
