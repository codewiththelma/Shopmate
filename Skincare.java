import java.text.DecimalFormat;

public class Skincare extends Products{
	private String skinType;
	private String spfRating;
	private boolean isVegan ;
	
	
	public Skincare() {
		
	}
	
	
	public Skincare(int productID, String productName, String productBrand, String productSize, double productPrice,
			int productQty,String category, String skinType, String spfRating, boolean isVegan) {
		super(productID, productName, productBrand, productSize, productPrice, productQty, category);
		this.skinType = skinType;
		this.spfRating = spfRating;
		this.isVegan = isVegan;
	}
	public void printProductdetails(Skincare s) {
		 DecimalFormat currency = new DecimalFormat("€0.00");
		   
		   System.out.println("----------------------------------------------------");
		   System.out.println("ID:             " + s.getProductID());
		   System.out.println("Name:           " + s.getProductName());
		   System.out.println("Brand:          "+s.getProductBrand());
		   System.out.println("Size:           "+ s.getProductSize());
		   System.out.println("Price:          " + currency.format(s.getProductPrice()));
		   System.out.println("In stock:       "+ s.getProductQty());
		   System.out.println("Skin Type:      "+ skinType);
		   System.out.println("Spf Rating:     " + spfRating);
		   System.out.println("Vegan:          " + isVegan);
		   System.out.println("----------------------------------------------------");
	}
	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public String getSpfRating() {
		return spfRating;
	}

	public void setSpfRating(String spfRating) {
		this.spfRating = spfRating;
	}

	public boolean isVegan() {
		return isVegan;
	}

	public void setVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}
	
}
