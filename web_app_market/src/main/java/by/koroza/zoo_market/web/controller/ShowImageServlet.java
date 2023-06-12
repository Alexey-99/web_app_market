package by.koroza.zoo_market.web.controller;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IMAGE_FILE_PATH;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowImageServlet
 */
@WebServlet(urlPatterns = "/showImageServlet")
public class ShowImageServlet extends HttpServlet {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private static final String RESPONSE_CONTENT_TYPE_IMG = "img/*";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imagePath = request.getParameter(PARAMETER_IMAGE_FILE_PATH);
		if (!imagePath.isBlank()) {
			File fileImage = new File(imagePath);
			if (fileImage.exists()) {
				response.setContentType(RESPONSE_CONTENT_TYPE_IMG);
				String formatNameImage = imagePath.substring(imagePath.lastIndexOf(".") + 1);
				if (isSupportFormat(formatNameImage)) {
					ImageIO.write(ImageIO.read(fileImage), formatNameImage, response.getOutputStream());
				}
			}
		}
	}

	private boolean isSupportFormat(String format) {
		boolean result = false;
		for (String elementFormat : ImageIO.getWriterFileSuffixes()) {
			if (format.equalsIgnoreCase(elementFormat)) {
				result = true;
			}
		}
		if (!result) {
			LOGGER.log(Level.WARN, "The class ImageIO don't support image format: " + format);
		}
		return result;
	}
}