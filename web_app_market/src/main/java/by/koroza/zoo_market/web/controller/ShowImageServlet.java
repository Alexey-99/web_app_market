package by.koroza.zoo_market.web.controller;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IMAGE_FILE_PATH;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.licensing.License;
import com.groupdocs.conversion.options.convert.ImageConvertOptions;

/**
 * Servlet implementation class ShowImageServlet
 */
@WebServlet(urlPatterns = "/showImageServlet")
public class ShowImageServlet extends HttpServlet {
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
				ImageIO.write(ImageIO.read(fileImage), formatNameImage, response.getOutputStream());
			}
		}
	}

	private void svgToPng() {
		// Как преобразовать файл SVG в формат JPG в Java, используя параметры по
		// умолчанию
		Converter converter = new Converter("path/vector-graphics.svg");

		ImageConvertOptions options = new ImageConvertOptions();
		options.setFormat(ImageFileType.Jpg);

		converter.convert("path/svg-to-jpg.jpg", options);
	}
}