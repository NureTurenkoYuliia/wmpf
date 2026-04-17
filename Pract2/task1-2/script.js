let data = [];

// Завдання 1 - Завантаження JSON
fetch('data.json')
    .then(response => response.text())
    .then(text => {
        data = JSON.parse(text);
        renderList(data);
    });

function renderList(items) {
    const list = document.getElementById('list');
    list.innerHTML = '';

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = `${item.name} (${item.category})`;
        list.appendChild(li);
    });
}

// Завдання 2 — Фільтрація
function applyFilter() {
    const value = document.getElementById('filterInput').value.toLowerCase();
    const filtered = data.filter(item =>
        item.category.toLowerCase().includes(value)
    );

    renderList(filtered);
}