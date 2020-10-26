package com.wj.client.model.VO;


public class ProductVO  {
	private String pCode;
	private String pName;
    private int price;
    private int quantity;
    private String category;
    private String info;
    private String imageLink;
    
    public ProductVO() {}
 
	public ProductVO(String pCode, String pName, int price, String category, String info, String imageLink) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.price = price;
		this.category = category;
		this.info = info;
		this.imageLink = imageLink;
	}
	public ProductVO(String pCode, String pName, int price, int quantity, String category, String info,
			String imageLink) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.info = info;
		this.imageLink = imageLink;
	}

	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	@Override
	public String toString() {
		return "ProductVO [pCode=" + pCode + ", pName=" + pName + ", price=" + price + ", category=" + category
				+ ", info=" + info + ", imageLink=" + imageLink + "]";
	}
    
    
}
