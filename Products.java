
public class Products {
	private int productID;
	private String productName;
	private String productBrand;
	private String productSize;
	private double productPrice;
	private int productQty;
	private String category;
	
	public Products() {
		
	}

	public Products(int productID, String productName, String productBrand, String productSize, double productPrice,
			int productQty, String category) {
		this.productID = productID;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productSize = productSize;
		this.productPrice = productPrice;
		this.productQty = productQty;
		this.category = category;
	}
	
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}