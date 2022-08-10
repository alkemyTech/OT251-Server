package com.alkemy.ong.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Component
public class ImageUtils {

	public MultipartFile base64Image2MultipartFile(String base64) {
		final String[] base64Array = base64.split(",");
		String dataUir, data;
		if (base64Array.length > 1) {
			dataUir = base64Array[0];
			data = base64Array[1];
		} else {
			dataUir = "data:image/jpg;base64";
			data = base64Array[0];
		}
		return new Base64ToMultipartFile(data, dataUir);
	}
}
