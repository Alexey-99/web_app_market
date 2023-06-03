function selectPage(numberPage, countPages) {
  removeActiveClassAllListItems(countPages);
  const elementListItem = selectElement(
    `.pagination_page_list_item_${numberPage}`
  );
  addClass(elementListItem, "active");
  const element2 = selectElement(".pagination_block");
  element2.innerHTML = `<pgn:pagination_pet numberPage="${numberPage}" maxCountPage="4" />`;
}

function removeActiveClassAllListItems(countPages) {
  for (let i = 1; i < countPages + 1; i++) {
    const elementListItem = selectElement(`.pagination_page_list_item_${i}`);
    removeClass(elementListItem, "active");
  }
}
