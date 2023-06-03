package by.koroza.zoo_market.web.tag;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;

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
	private int maxcountpage;
	private int numberpage;

	public void setMaxcountpage(int maxcountpage) {
		this.maxcountpage = maxcountpage;
	}

	public void setNumberpage(int numberpage) {
		this.numberpage = numberpage;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getRequest().getRequestDispatcher("");
			HttpSession session = pageContext.getSession();
			String locale = (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE);
			@SuppressWarnings("unchecked")
			List<Pet> listProductPets = (List<Pet>) session.getAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS);
			printPagination(listProductPets, locale);
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

	private void printPagination(List<Pet> listProductPets, String locale) throws IOException {

		StringBuilder builder = new StringBuilder();
		int countPage = (int) Math.ceil(listProductPets.size() / maxcountpage);
		numberLastPage = countPage;
		builder.append("<nav>");
		builder.append("<ul class=\"pagination d-flex justify-content-center align-items-center\">");
		if (numberpage > NUMBER_FIRST_PAGE) {
			builder.append("<li class=\"page-item \">");
			builder.append("<button ").append("class=\"page-link pagination_page_list_item_btn\"")
					.append("onclick=\"selectPage(").append(numberpage - 1).append(",").append(countPage).append(")\"")
					.append(">");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("Предыдущая");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("Previous");
			} else {
				builder.append("Previous");
			}
			builder.append("</button>");
			builder.append("</li>");
		}
		for (int i = 1; i < countPage + 1; i++) {
			if (i == numberpage) {
				builder.append("<li class=\"page-item active pagination_page_list_item_").append(i).append("\"")
						.append(">");
			} else {
				builder.append("<li class=\"page-item pagination_page_list_item_").append(i).append("\"").append(">");
			}
			builder.append("<button ")
					.append("class=\"page-link pagination_page_list_item_btn pagination_page_list_item_btn_").append(i)
					.append("\"").append("onclick=\"selectPage(").append(i).append(",").append(countPage).append(")\"")
					.append(">");
			builder.append(i);
			builder.append("</button>");
			builder.append("</li>");
		}
		if (numberpage < numberLastPage) {
			builder.append("<li class=\"page-item \">");
			builder.append("<button ").append("class=\"page-link pagination_page_list_item_btn").append("\"")
					.append("onclick=\"selectPage(").append(numberpage + 1).append(",").append(countPage).append(")\"")
					.append(">");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("Следующая");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("Next");
			} else {
				builder.append("Next");
			}
			builder.append("</button>");
			builder.append("</li>");
		}
		builder.append("</ul>");
		builder.append("</nav>");
		pageContext.getOut().write(builder.toString());
	}
}