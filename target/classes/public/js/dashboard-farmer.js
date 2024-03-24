// Function to add a new product to the My Products section
async function addProduct(){
    // Fetch the farmer's email from the input field
    var email1 = document.getElementById('email').value.trim();
    var productName1 = document.getElementById('productName').value.trim();
    var imagePath1 = document.getElementById('imagePath').files[0].name;
    var Quantity1 = document.getElementById('Quantity').value.trim();
    var priceQuantity1 = document.getElementById('priceQuantity').value.trim();
    var productDescription1 = document.getElementById('productDescription').value.trim();

    // Check if any field is empty
    if(email==="" || productName==="" || imagePath==="" || priceQuantity==="" || productDescription===""){
        alert("Please fill all details");
        return false;
    }
    var formData = new FormData();
    formData.append("file", document.getElementById('imagePath').files[0]);
    formData.append('product', new Blob([JSON.stringify({
        "farmerEmail": email1,
        "productName": productName1,
        "imagePath": "path",
        "quantity": Quantity1,
        "priceQuantity": priceQuantity1,
        "productDescription": productDescription1
    })], {
        type: "application/json"
    }));
    fetch('/products', {
        method: "POST",
        body: formData
    })
    .then(data => {
        getProducts();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to register product');
    });


    return true; // Form is valid, proceed with submission
}
async function getProducts(){
    document.getElementById('email').value=window.sessionStorage.getItem("emailid");
    const response = await fetch("/products/"+window.sessionStorage.getItem("emailid"));
    const products = await response.json();
    if (products.length > 0) {
      var temp = "";
      products.forEach((itemData) => {
        let path = itemData.imagePath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");
        temp += "<tr>";
        temp += "<td>" + itemData.pid + "</td>";
        temp += "<td>" + itemData.productName + "</td>";
        temp += "<td><img src="+path+" width='60' height='60' alt='Sample Image'></td>";
        temp += "<td>" + itemData.quantity + "</td>";
        temp += "<td>" + itemData.priceQuantity + "</td>";
        temp += "<td>" + itemData.productDescription + "</td>";
        temp += "<td><button class='btn btn-danger' onClick='return deleteProduct("+itemData.pid+")'>Delete</button></td></tr>";
      });
      document.getElementById('productsList').innerHTML = temp;
    }else{
        document.getElementById('productsList').innerHTML = "";
    }
}
async function deleteProduct(pid){
    fetch('/product/'+pid, {
            method: "GET"
    })
    .then(data => {
        getProducts();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to get product');
    });
}

async function postBlogs(){
    var formData = new FormData();
    formData.append("image", document.getElementById('uploadImage').files[0]);
    formData.append("video", document.getElementById('uploadVideo').files[0]);
    formData.append('blog', new Blob([JSON.stringify({
            "description": document.getElementById('experienceContent').value.trim(),
            "email":window.sessionStorage.getItem("emailid"),
            "header":document.getElementById('blogheader').value.trim()
    })], {
        type: "application/json"
    }));
    fetch('/blogs', {
        method: "POST",
        body: formData
    })
    .then(data => {
        getBlogs();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to register blogs');
    });
}
async function getBlogs(){
    const response = await fetch("/blogs/"+window.sessionStorage.getItem("emailid"));
    const blogs = await response.json();
    if (blogs.length > 0) {
      var temp = "";
      blogs.forEach((itemData) => {
        let imagePath = itemData.imagePath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");
        let videoPath = itemData.videoPath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");

        temp += "<tr>";
        temp += "<td>" + itemData.id + "</td>";
        temp += "<td>" + itemData.header + "</td>";
        temp += "<td><img src="+imagePath+" width='60' height='60' alt='Sample Image'></td>";
        temp += "<td><video width='70' height='60' autoplay> <source src='"+videoPath+"'>Your browser does not support the video tag.</video></td>";
        temp += "<td style='text-overflow: ellipsis;word-wrap: nowrap;'>" + itemData.description + "</td>";
        temp += "<td><button class='btn btn-danger' onClick='return deleteblog("+itemData.id+")'>Delete</button></td></tr>";
      });
      document.getElementById('blogslist').innerHTML = temp;
    }else{
        document.getElementById('blogslist').innerHTML = "";
    }
}
async function deleteblog(id){
    fetch('/blog/'+id, {
            method: "GET"
    })
    .then(data => {
    getBlogs();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to delete blog');
    });
}
window.onload=function(){
    getProducts();
    getBlogs();
}