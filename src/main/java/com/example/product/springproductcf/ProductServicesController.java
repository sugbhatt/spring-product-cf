package com.example.product.springproductcf;

import java.io.File;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServicesController {
	
    @Autowired
    private Environment env; 
	
	private static final Logger log = LoggerFactory.getLogger(ProductServicesController.class);

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		
		File file = new File(getClass().getResource("/product.json").getPath());
		
		if(id == 1) {
			return new ResponseEntity<Product>(ConverterUtil.convertJSONtoPOJO(file), HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/product/", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Product> createProduct(@RequestHeader("Host") String host,
    														   @RequestHeader("header1") String header1,
    														   @RequestBody Product product) {
		log.info("Host "+host);
		log.info("Header1 "+header1);
		log.info("Product "+ToStringBuilder.reflectionToString(product));
		log.info("OS "+env.getProperty("OS"));
		log.info("App name "+env.getProperty("spring.application.name"));
		
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
}
