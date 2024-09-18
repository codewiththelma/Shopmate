import java.text.DecimalFormat;

public class Fragrance extends Products{
	private String gender;
	private String longevity;
	
	public Fragrance() {
		
	}

	public Fragrance(int productID, String productName, String productBrand, String productSize, double productPrice,
			int productQty,String category, String gender, String longevity) {
		super(productID, productName, productBrand, productSize, productPrice, productQty, category);
		this.gender = gender;
		this.longevity = longevity;
	}
	public void printProductdetails(Fragrance f) {
		 DecimalFormat currency = new DecimalFormat("€0.00");
		   
		   System.out.println("----------------------------------------------------");
		   System.out.println("ID:             " + f.getProductID());
		   System.out.println("Name:           " + f.getProductName());
		   System.out.println("Brand:          "+f.getProductBrand());
		   System.out.println("Size:           "+ f.getProductSize());
		   System.out.println("Price:          " + currency.format(f.getProductPrice()));
		   System.out.println("In stock:       "+ f.getProductQty());
		   System.out.println("Gender:         "+ gender);
		   System.out.println("Longevity:      " + longevity);
		   System.out.println("----------------------------------------------------");
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLongevity() {
		return longevity;
	}

	public void setLongevity(String longevity) {
		this.longevity = longevity;
	}
	
	
	
}
