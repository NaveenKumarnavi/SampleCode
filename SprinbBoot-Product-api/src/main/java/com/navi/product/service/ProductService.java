package com.navi.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navi.product.entity.Product;
import com.navi.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
    private ProductRepository repository;
	
    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){
        return (List<Product>) repository.saveAll(products);
    }
    public List<Product> getProducts(){
        return  (List<Product>) repository.findAll();
    }
    public Product getProductByPsaf_sys_id(int psaf_sys_id){
        return repository.findById(psaf_sys_id);
    }
    public String deleteProduct(int psaf_sys_id){
        repository.deleteById(psaf_sys_id);
        return "Deleted!";
    }
    public Product updateProduct(Product product){
        Product existingProduct= repository.findById((int) product.getPsaf_sys_id());
        existingProduct.setPsaf_prod_code((String)product.getPsaf_code());
        existingProduct.setPsaf_comp_code((String)product.getPsaf_comp_code());
        existingProduct.setPsaf_sch_code((String)product.getPsaf_sch_code());
        existingProduct.setPsaf_tran_type((String)product.getPsaf_tran_type());
        existingProduct.setPsaf_end_type((String)product.getPsaf_end_type());
        existingProduct.setPsaf_code((String)product.getPsaf_code());
        existingProduct.setPsaf_lvl((String)product.getPsaf_lvl());
        existingProduct.setPsaf_type((String)product.getPsaf_type());
        existingProduct.setPsaf_dflt_yn((String)product.getPsaf_dflt_yn());
        existingProduct.setPsaf_desc((String)product.getPsaf_desc());
        existingProduct.setPsaf_desc_bl((String)product.getPsaf_desc_bl());
        existingProduct.setPsaf_short_desc((String)product.getPsaf_short_desc());
        existingProduct.setPsaf_short_desc_bl((String)product.getPsaf_short_desc_bl());
        existingProduct.setPsaf_long_desc((String)product.getPsaf_long_desc());
        existingProduct.setPsaf_long_desc_bl((String)product.getPsaf_long_desc_bl());
        existingProduct.setPsaf_value((String)product.getPsaf_value());
        existingProduct.setPsaf_eff_fm_dt((Date)product.getPsaf_eff_to_dt());
        existingProduct.setPsaf_eff_to_dt((Date)product.getPsaf_eff_fm_dt());
        existingProduct.setPsaf_round_off((Long) product.getPsaf_round_off());

        return repository.save(existingProduct);
    }

	
	
	
}
	
	

