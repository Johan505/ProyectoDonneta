document.addEventListener('DOMContentLoaded', () => {
    const products = document.querySelectorAll('.table tbody tr');

    for (let i = 0; i < products.length; i++) {
        products[i].onchange = (e) => {
            const cantidad = Number(e.target.value)
            const precio = Number(e.path[2].children[1].innerText)
            let subTotal = Number(e.path[2].children[3].innerText);

            e.path[2].children[3].innerText = precio * cantidad;

            const arrSubTotal = document.querySelectorAll('.subTotal');
            const arrTotal = [];

            for (let i = 0; i < arrSubTotal.length; i++) {
                arrTotal.push(Number(arrSubTotal[i].innerText));
            }

            const sum = arrTotal.reduce((a, b) => a + b, 0);
            total.innerText = sum;
            
        }
    }
})



