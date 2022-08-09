 package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer>{ //basta hangi entitye bakicaksin ve primary keyin tipini iyaziyoruz
	// tabloya bakıyor getBy a göre where koşulu oluştuuryor kendisi jparepo yapıyor 

    Product getByProductName(String productName);   // select * from products where product_name = emre
    
                //1. kolon       2. kolon
    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);  // select * from products where product_name = emre  and category_id = 1

                //1. kolon       2. kolon
    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId); // select * from products where category_id in(1,2,3,4)
                // isimlendirme kuralına uyman yeterli
    
    List<Product> getByCategoryIn(List<Integer>categories); 
    
    List<Product> getByProductNameContains(String productName);
    
    List<Product> getByProductNameStartsWith(String productName);
    
        // veritabaını query yazarken unut
        //  hangi entityden  hangi alan     parametrem 
    @Query("From Product where productName=:productName and category.categoryId=:categoryId")  // select * from products where product_name = bisey and category_id = bisey  
    List<Product> getByNameAndCategory(String productName, int categoryId);
    
//    @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p")         //select p.Product_id, p.product_name, c.category_name from Category c inner join Product p on c.categoryId = p.categoryId        
    // ana tablodan başlayın ilk  2 tabloyu bi tablo yapmamızı sağlıyor
//    List<ProductWithCategoryDto> getProductWithCategoryDetails(); // imza bu
    
    
    @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
}
