const mainEnterAreas = document.querySelectorAll('.main-enter-area');
mainEnterAreas[0].onclick = e => {
    location.href = '/history';
}

mainEnterAreas[1].onclick = e => {
    location.href = '/talk';
}