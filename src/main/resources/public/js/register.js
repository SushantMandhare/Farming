// register.js
async function validateForm() {
    var name1 = document.getElementById('name').value.trim();
    var contactinfo1 = document.getElementById('contactinfo').value.trim();
    var email1 = document.getElementById('email').value.trim();
    var password1 = document.getElementById('password').value.trim();
    var role1 = document.getElementById('role').value;

    // Prepare data to send to the backend
    var userData = {
        name: name1,
        email: email1,
        password: password1,
        role: role1,
        contactinfo: contactinfo1
    };

    // Submit the form data to the backend
     fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(data => {
		alert('User registered successfully');
        // Determine the redirect URL based on the user's role
        var redirectUrl;
        if (role1 === 'customer') {
            redirectUrl = 'dashboard-customer.html';
        }else{
            redirectUrl = 'dashboard-farmer.html';
        }
        // Redirect the user to the appropriate dashboard
        window.sessionStorage.setItem("emailid", email1);
        window.location.href = redirectUrl;
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to register user');
    });

    return true; // Form is valid, proceed with submission
}
