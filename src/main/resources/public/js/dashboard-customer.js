async function getProducts(){
    const response = await fetch("/products");
    const products = await response.json();
    if (products.length > 0) {
      var temp = "";
      products.forEach((itemData) => {
        let path = itemData.imagePath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");
        temp += "<tr>";
        temp += "<td>" + itemData.pid + "</td>";
        temp += "<td id='productName"+itemData.pid+"'>" + itemData.productName + "</td>";
        temp += "<td><img src="+path+" width='60' height='60' alt='Sample Image'></td>";
        temp += "<td id='quantity"+itemData.pid+"'>" + itemData.quantity + "</td>";
        temp += "<td id='priceQuantity"+itemData.pid+"'>" + itemData.priceQuantity + "</td>";
        temp += "<td>" + itemData.productDescription + "</td>";
        temp += "<td><input type='number' class='form-control' id='orderedQuantity"+itemData.pid+"' name='orderedQuantity"+itemData.pid+"' required></td>";
        temp += "<td><button class='btn btn-danger' onClick='return addProduct("+itemData.pid+")'>Add to cart</button></td></tr>";
      });
      document.getElementById('productsList').innerHTML = temp;
    }else{
        document.getElementById('productsList').innerHTML = "";
    }
    getOrders();
}
async function addProduct(pid){
    var ordered=document.getElementById('orderedQuantity'+pid).value;
    var actualqty=document.getElementById('quantity'+pid).innerText;
    if(Number(actualqty) > Number(ordered)){
        var productname1=document.getElementById('productName'+pid).innerText;
        var productprice=document.getElementById('priceQuantity'+pid).innerText;
        var totalPrice1=Number(productprice)*Number(ordered);

        var orderData = {
                productName: productname1,
                quantity: ordered,
                totalPrice: totalPrice1,
                email: window.sessionStorage.getItem("emailid"),
                pid:pid
            };

            // Send login request to backend
            fetch('/order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(orderData)
            })
            .then(data => {
                getProducts();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to Order');
            });
    }else{
        alert('Should be less than quantity');
    }
}

async function getOrders(){
    const response = await fetch("/orders/"+window.sessionStorage.getItem("emailid"));
    const orders = await response.json();
    if (orders.length > 0) {
      var temp = "";
      var TotalPrice=0;
      orders.forEach((itemData) => {
        TotalPrice +=Number(itemData.totalPrice);
        temp += "<tr>";
        temp += "<td>" + itemData.id + "</td>";
        temp += "<td id='productName"+itemData.id+"'>" + itemData.productName + "</td>";
        temp += "<td id='quantity"+itemData.id+"'>" + itemData.quantity + "</td>";
        temp += "<td id='priceQuantity"+itemData.id+"'>" + itemData.totalPrice + "</td>";
        temp += "<td><button class='btn btn-danger' onClick='return deleteOrder("+itemData.id+")'>Delete</button></td></tr>";
      });
      document.getElementById('orderHistory').innerHTML = temp;
      document.getElementById('totalCost').innerHTML=TotalPrice;
    }else{
       document.getElementById('orderHistory').innerHTML = "";
       document.getElementById('totalCost').innerHTML=0;
    }
}
async function deleteOrder(id){
    fetch('/order/'+id, {
            method: "GET"
    })
    .then(data => {
        getProducts();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to delete order');
    });
}