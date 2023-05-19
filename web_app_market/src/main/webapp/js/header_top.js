function getProducts(nameClass) {
	console.log(nameClass);
	let idProducts = '';
	for (let i = 0; i < localStorage.length; i++) {
		let key = localStorage.key(i);
		let value = localStorage.getItem(key);
		idProducts = idProducts + key + ',' + value + ' ; ';
	}
	let el = document.querySelector(nameClass);
	el.setAttribute('value', idProducts);
}