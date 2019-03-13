package dlnu.sdl.service;

import java.util.ArrayList;

import dlnu.sdl.domain.Book;
import dlnu.sdl.utils.SqlHelper;

public class BookService {
	@SuppressWarnings({ "static-access", "unchecked" })
	public Book getBookById(String id){
		Book book = new Book();
		
		String sql = "select * from book where id = ?";
		String [] paras = {id};
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		if(al.size()==1){
			Object []object = (Object[]) al.get(0);
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Float.parseFloat(object[4].toString()));
			book.setNums(Integer.parseInt(object[5].toString()));
		}
		
		return book;
		
	}
	
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public ArrayList getAllBook(){
		
		String sql = "select * from book where 1=?";
		String paras [] = {"1"};
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		ArrayList<Book> newAl = new ArrayList<Book>();
		for(int i=0;i<al.size();i++){
			Object []object = (Object[]) al.get(i);
			Book book = new Book();
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Float.parseFloat(object[4].toString()));
			book.setNums(Integer.parseInt(object[5].toString()));
			newAl.add(book);							
		}
		
		return newAl;
	}
	//根据ID查找书籍
	@SuppressWarnings({"unchecked" })
	public ArrayList searchIDBook(int id) {
		String sql = " select * from book where id = ?";
		String paras[] = { id+"" };
		@SuppressWarnings({ "static-access" })
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		ArrayList<Book> newAl = new ArrayList<Book>();
		for(int i=0;i<al.size();i++){
			Object []object = (Object[]) al.get(i);
			Book book = new Book();
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Integer.parseInt(object[4].toString()));
			book.setNums(Integer.parseInt(object[5].toString()));
			newAl.add(book);
		}
		return newAl;
	}
	@SuppressWarnings({"unchecked" })
	public ArrayList searchNameBook(String name) {
		String sql = " select * from book where name like ?";
		String paras[] = { "%"+name+"%" };
		@SuppressWarnings({ "static-access" })
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		ArrayList<Book> newAl = new ArrayList<Book>();
		for(int i=0;i<al.size();i++){
			Object []object = (Object[]) al.get(i);
			Book book = new Book();
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Integer.parseInt(object[4].toString()));
			book.setNums(Integer.parseInt(object[5].toString()));
			newAl.add(book);
		}
		return newAl;
	}
	//删除书籍,返回是是否删除成功
	@SuppressWarnings("static-access")
	public boolean deleteBook(int[] id) {
		String sql = "delete from book where id = ?";
		boolean bo = false;
		String[] paras = new String[id.length];
		for (int i = 0; i < id.length; i++) {
			paras[i] = String.valueOf(id[i]);
		}
		bo = new SqlHelper().executeUpdate(sql, paras);
		return bo;
	}
	//添加书籍 返回是否添加成功
	@SuppressWarnings("static-access")
	public boolean adduser(Book book){
		boolean bo = false;
		String sql = "insert into book values(?,?,?,?,?,?)";
		String paras[] = { book.getId() + "", book.getName(),book.getAuthor(),book.getPublishHouse(),book.getPrice()+"",book.getNums()+"" };
		bo = new SqlHelper().executeUpdate(sql, paras);
		return bo;
	}
	//修改书籍
	@SuppressWarnings("static-access")
	public boolean updateBook(Book book){
		boolean bo = false;
		String sql = "update book set name = ?, author=?,publishHouse =?,price =?, nums =? where id = ?";
		String paras[] = {book.getName(),book.getAuthor(),book.getPublishHouse(),book.getPrice()+"",book.getNums()+"" , book.getId() + ""};
		bo = new SqlHelper().executeUpdate(sql, paras);
		return bo;
	}
}
