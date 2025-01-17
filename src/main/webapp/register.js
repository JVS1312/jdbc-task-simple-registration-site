/**
 * 
 */
// Input fields
let name = document.querySelector("input[name='uname']");
let email = document.querySelector("input[name='uemail']");
let phone = document.querySelector("input[name='uphone']");
let password = document.querySelector("input[name='upwd']");


// Error message spans
let ename = document.querySelectorAll(".errormsg")[0];
let eemail = document.querySelectorAll(".errormsg")[1];
let ephone = document.querySelectorAll(".errormsg")[2];
let epassword = document.querySelectorAll(".errormsg")[3];
let emsg=document.querySelector(".msg");

document.querySelector('input[type="number"]').addEventListener('keydown', function(event) {
    if (event.key === 'ArrowUp' || event.key === 'ArrowDown') {
        event.preventDefault();
    }
});


// Form submission
let form = document.querySelector("form");
form.addEventListener("submit", function (e) {
  let isValid = true;

  // Name validation (only characters allowed, 2-15 characters)
  let nameRegex = /^[a-zA-Z]{2,15}$/;
  if (name.value === "") {
    ename.textContent = "Name is required";
    isValid = false;
  } else if (!nameRegex.test(name.value)) {
    ename.textContent = "Only letters allowed, 2-15 characters";
    isValid = false;
  } else {
    ename.textContent = "";
  }

  // Email validation (basic email format)
  let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (email.value === "") {
    eemail.textContent = "Email is required";
    isValid = false;
  } else if (!emailRegex.test(email.value)) {
    eemail.textContent = "Invalid email format";
    isValid = false;
  } else {
    eemail.textContent = "";
  }

  // Phone validation (10 digits, starting with 6-9)
  let phoneRegex = /^[6-9][0-9]{9}$/;
  if (phone.value === "") {
    ephone.textContent = "Phone is required";
    isValid = false;
  } else if (!phoneRegex.test(phone.value)) {
    ephone.textContent = "Phone must start with 6-9 and be 10 digits";
    isValid = false;
  } else {
    ephone.textContent = "";
  }

  // Password validation (6-20 characters, letters and numbers)
  let passwordRegex = /^[a-zA-Z0-9]{5,20}$/;
  if (password.value === "") {
    epassword.textContent = "Password is required";
    isValid = false;
  } else if (!passwordRegex.test(password.value)) {
    epassword.textContent = "Password must be 5-20 characters, no special symbols";
    isValid = false;
  } else {
    epassword.textContent = "";
  }

  // Prevent form submission if validation fails
  if (!isValid) {
	  emsg.innerHTML="";
    e.preventDefault();
  }
});
