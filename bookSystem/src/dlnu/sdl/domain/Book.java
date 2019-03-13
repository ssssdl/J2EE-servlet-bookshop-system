package dlnu.sdl.domain;
//定义图书的Javabean接口
public class Book {
	private int id;
	
	private String name;
	
	private String author;
	
	private String publishHouse;
	
	private float price;
	
	private int nums;
	
	private int shoppingNum=1;

	public int getShoppingNum() {
		return shoppingNum;
	}

	public void setShoppingNum(int shoppingNum) {
		this.shoppingNum = shoppingNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishHouse() {
		return publishHouse;
	}

	public void setPublishHouse(String publishHouse) {
		this.publishHouse = publishHouse;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}
}
