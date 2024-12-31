document.querySelector('.btn-back-circle').addEventListener('click', (e) => {
    location.href = history.go(-1);
});