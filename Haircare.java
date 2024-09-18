import java.text.DecimalFormat;

public class Haircare extends Products{
	private String hairType;
	private String hairConcerns;
	private boolean isUvProtectant;
	
	public Haircare() {
		
	}

	public Haircare(int productID, String productName, String productBrand, String productSize, double productPrice,
			int productQty,String category, String hairType, String hairConcerns, boolean isUvProtectant) {
		super(productID, productName, productBrand, productSize, productPrice, productQty, category);
		this.hairType = hairType;
		this.hairConcerns = hairConcerns;
		this.isUvProtectant = isUvProtectant;
	}
	
	public void printProductdetails(Haircare h) {
		 DecimalFormat currency = new DecimalFormat("€0.00");
		   
		   System.out.println("----------------------------------------------------");
		   System.out.println("ID:             " + h.getProductID());
		   System.out.println("Name:           " + h.getProductName());
		   System.out.println("Brand:          "+h.getProductBrand());
		   System.out.println("Size:           "+ h.getProductSize());
		   System.out.println("Price:          " + currency.format(h.getProductPrice()));
		   System.out.println("In stock:       "+ h.getProductQty());
		   System.out.println("Hair Type:      "+ hairType);
		   System.out.println("Hair Concerns:  " + hairConcerns);
		   System.out.println("UV Protection:  " + isUvProtectant);
		   System.out.println("----------------------------------------------------");
	}
	//private static int numSold = 0;
    
    //public static void addNumSold() {
     //   numSold++;
   // }
    
    //public static int getNumSold() {
     //   return numSold;
    //}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public String getHairConcerns() {
		return hairConcerns;
	}

	public void setHairConcerns(String hairConcerns) {
		this.hairConcerns = hairConcerns;
	}

	public boolean isUvProtectant() {
		return isUvProtectant;
	}

	public void setUvProtectant(boolean isUvProtectant) {
		this.isUvProtectant = isUvProtectant;
	}
	
}
