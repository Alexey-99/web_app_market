function isHaveClass(element, className) {
  return element.classList.contains(className);
}

function removeClass(element, className) {
  if (isHaveClass(element, className)) {
    element.classList.remove(className);
  }
}

function addClass(element, className) {
  if (!isHaveClass(element, className)) {
    element.classList.add(className);
  }
}

function setAttribute(element, attributeName, attributevalue) {
  element.setAttribute(attributeName, attributevalue);
}

function selectElement(className) {
  const element = document.querySelector(className);
  if (element == null) {
    console.log(`Element with className - ${className}, not found.`);
  }
  return element;
}
