function updateTicketPackages() {
    const container = document.getElementById("ticketPackages");
    const packageCount = parseInt(document.getElementById("countOfTP").value) || 0;

    container.innerHTML = "";

    for (let i = 0; i < packageCount; i++) {
        addTicketPackage();
    }
}

function addTicketPackage() {
    const container = document.getElementById("ticketPackages");
    const index = container.children.length;

    const packageDiv = document.createElement("div");
    packageDiv.classList.add("ticket-package");

    packageDiv.innerHTML = `
        <label>Цена билета:</label>
        <input type="number" name="tickets[${index}].cost" required>

        <label>Количество билетов:</label>
        <input type="number" name="tickets[${index}].count" required>

        <button type="button" onclick="removePackage(this)">Удалить</button>
    `;

    container.appendChild(packageDiv);
}


function removePackage(button) {
    button.parentElement.remove();
}