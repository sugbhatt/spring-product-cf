package com.example.product.springproductcf;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterUtil {
	public static Product convertJSONtoPOJO(File file) {
		Product product = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			product = mapper.readValue(file, Product.class);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}
	
}
