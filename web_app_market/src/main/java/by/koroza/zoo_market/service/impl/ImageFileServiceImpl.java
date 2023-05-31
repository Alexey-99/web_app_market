package by.koroza.zoo_market.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.service.ImageFileService;
import by.koroza.zoo_market.service.exception.ServiceException;

import jakarta.servlet.http.Part;

public class ImageFileServiceImpl implements ImageFileService {
	private static final Logger log = LogManager.getLogger();
	private static final ImageFileService INSTANCE = new ImageFileServiceImpl();
	private static final String UPLOAD_DIR = "image";

	private ImageFileServiceImpl() {
	}

	public static ImageFileService getInstance() {
		return INSTANCE;
	}

	@Override
	public String saveImageOnDisk(Part imagePart, String imageUploadDir) throws ServiceException {
		String imagePath = null;
		try (InputStream inputStream = imagePart.getInputStream()) {
			String submittedName = imagePart.getSubmittedFileName();
			Path path = new File(imageUploadDir.concat(submittedName)).toPath();
			Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
			imagePath = File.separator.concat(UPLOAD_DIR).concat(File.separator).concat(submittedName);
		} catch (IOException e) {
			log.log(Level.ERROR, "exception in method saveImageToDisk()", e);
			throw new ServiceException("Exception when save image", e);
		}
		return imagePath;
	}
}