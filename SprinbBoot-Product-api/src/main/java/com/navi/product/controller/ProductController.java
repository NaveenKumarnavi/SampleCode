package com.navi.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navi.product.entity.Product;
import com.navi.product.exception.ErrorHandler;
import com.navi.product.exception.RecordNotFoundException;
import com.navi.product.reponse.ResponseHandler;
import com.navi.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/Product")
    public ResponseEntity<Object> Post(@RequestBody Product product){
        try{
           Product result = service.saveProduct(product);
           return ResponseHandler.generateResponse("Successfully added data!",HttpStatus.OK,result);
        }catch (Exception e){
          return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/Products")
    public ResponseEntity<Object> Get(){
            List<Product> result = (List<Product>) service.getProducts();
          //  return new APIResponse<List<Lob>>(result.size(),result);
        try {
            if(result==null){
           throw new RecordNotFoundException("Record_Not_Found");
            }
         return  ResponseHandler.generateResponse("Successfully retrieved Data!",HttpStatus.OK,result);
       }catch(Exception e){

            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }

    }

    @GetMapping("/Product/{psaf_sys_id}")
    public ResponseEntity<Object> Get(@PathVariable int psaf_sys_id) {
    	try{
    		Product result =  this.service.getProductByPsaf_sys_id(psaf_sys_id);
    		
    		if(result==null){
    			throw new RecordNotFoundException("Record_Not_Found");
    		}
    		return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK,result);
    	}catch(RecordNotFoundException e){
    		return ErrorHandler.ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}

    }

    @PutMapping("/Product/{psaf_sys_id")
    public ResponseEntity<Object> Update( @RequestBody Product product){
        try{
            Product result = service.saveProduct(product);
            if(result==null){
                throw new RecordNotFoundException("Record_Not_Found");
            }
            return ResponseHandler.generateResponse("updated",HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @DeleteMapping("/Product/{psaf_sys_id}")
    public ResponseEntity<Object> Delete(@PathVariable int psaf_sys_id){
        try{
            String result = service.deleteProduct(psaf_sys_id);
            if(result==null){
                throw new RecordNotFoundException("Record_Not_Found");
           }
           return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);

        }
    }


}
