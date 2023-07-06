package by.koroza.zoo_market.service.impl.image;

import static by.koroza.zoo_market.web.command.name.path.ImagePath.STORAGE_IMAGES_FOLDER_PATH;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.product.ProductFeedsAndOtherDaoImpl;
import by.koroza.zoo_market.dao.impl.product.ProductPetDaoImpl;
import by.koroza.zoo_market.service.ImageFileService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class ImageFileServiceImpl implements ImageFileService {
	private static Logger log = LogManager.getLogger();
	private static final ImageFileService INSTANCE = new ImageFileServiceImpl();

	private ImageFileServiceImpl() {
	}

	public static ImageFileService getInstance() {
		return INSTANCE;
	}

	@Override
	public String saveImageOnDisk(InputStream inputStream, String submittedName) throws ServiceException {
		String imagePath = null;
		try {
			try (inputStream) {
				File path = new File(STORAGE_IMAGES_FOLDER_PATH);
				if (!path.exists()) {
					path.mkdirs();
				}
				imagePath = STORAGE_IMAGES_FOLDER_PATH.concat("/").concat(submittedName);
				Files.copy(inputStream, Path.of(imagePath), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			log.log(Level.ERROR, "exception in method saveImageToDisk()", e);
			throw new ServiceException("Exception when save image", e);
		}
		return imagePath;
	}

	@Override
	public boolean removeProductImage(String imagePath) throws ServiceException {
		boolean result = false;
		File fileImagePath = new File(imagePath);
		if (fileImagePath.exists()) {
			try {
				if (!ProductPetDaoImpl.getInstance().existsProductWithImagePath(imagePath)
						&& !ProductFeedsAndOtherDaoImpl.getInstance().existsProductWithImagePath(imagePath)) {
					result = fileImagePath.delete();
				}
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}
}