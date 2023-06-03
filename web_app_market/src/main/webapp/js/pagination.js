function selectPage(numberPage, countPages) {
	removeActiveClassAllListItems(countPages);
	const elementListItem = selectElement(
		`.pagination_page_list_item_${numberPage}`
	);
	addClass(elementListItem, "active");
}

function removeActiveClassAllListItems(countPages) {
	for (let i = 1; i < countPages + 1; i++) {
		const elementListItem = selectElement(`.pagination_page_list_item_${i}`);
		removeClass(elementListItem, "active");
	}
}