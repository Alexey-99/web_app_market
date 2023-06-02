package by.koroza.zoo_market.web.tag;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;

import java.io.IOException;
import java.util.List;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class ProductPetPaginationTag extends TagSupport {
	private static final long serialVersionUID = -8639846713001679127L;
	private static final int NUMBER_FIRST_PAGE = 1;
	private static int numberLastPage;
	private int maxCountPage;
	private int numberPage;

	public void setMaxCountPage(int maxCountPage) {
		this.maxCountPage = maxCountPage;
	}

	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpSession session = pageContext.getSession();
			List<Pet> listProductPets = (List<Pet>) session.getAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS);
			printPagination(listProductPets);
			printProducts();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	private void printProducts() throws IOException {
		// JspWriter out = pageContext.getOut();
		// StringBuilder builder = new StringBuilder();

	}

	/*
	 * <nav> <ul
	 * class="pagination d-flex justify-content-center align-items-center"> <li
	 * class="page-item"><a class="page-link" href="#">Предыдущая</a> </li> <li
	 * class="page-item"><a class="page-link" href="#">1</a></li> <li
	 * class="page-item"><a class="page-link" href="#">2</a></li> <li
	 * class="page-item"><a class="page-link" href="#">3</a></li> <li
	 * class="page-item"><a class="page-link" href="#">Следующая</a> </li> </ul>
	 * </nav>
	 */

	private void printPagination(List<Pet> listProductPets) throws IOException {

		StringBuilder builder = new StringBuilder();
		int countPage = (int) Math.ceil(listProductPets.size() / maxCountPage);
		numberLastPage = countPage;
		builder.append("<nav>");
		builder.append("<ul class=\"pagination d-flex justify-content-center align-items-center\">");
		if (numberPage > NUMBER_FIRST_PAGE) {
			builder.append("<li class=\"page-item \">");
			builder.append("<button class=\"page-link\" >");
			builder.append("Предыдущая");
			builder.append("</button>");
			builder.append("</li>");
		}
		for (int i = 1; i < countPage + 1; i++) {
			if (i == numberPage) {
				builder.append("<li class=\"page-item active\">");
			} else {
				builder.append("<li class=\"page-item \">");
			}
			builder.append("<button class=\"page-link\" >");
			builder.append(i);
			builder.append("</button>");
			builder.append("</li>");
		}
		if (numberPage < numberLastPage) {
			builder.append("<li class=\"page-item \">");
			builder.append("<button class=\"page-link\" >");
			builder.append("Следующая");
			builder.append("</button>");
			builder.append("</li>");
		}
		builder.append("</ul>");
		builder.append("</nav>");
		pageContext.getOut().write(builder.toString());
	}
}