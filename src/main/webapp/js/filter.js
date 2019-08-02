const filterForm = document.getElementById('filter-param');

function handleFilter(event) {
    console.log(event.get().value);
}

function doFilter(filterForm) {
    console.log(filterForm);
    const filterType = document.getElementById('shoestype');
    const brand = filterForm.elements.nike;

    filterType.addEventListener('change', handleFilter);
    brand.addEventListener('change', handleFilter);
}


doFilter(filterForm);
