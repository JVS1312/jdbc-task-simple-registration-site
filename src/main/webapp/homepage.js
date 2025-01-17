document.addEventListener("DOMContentLoaded", function() {
    let deleteBtn = document.querySelector("#accd");

    deleteBtn.addEventListener("dblclick", () => {
        const userConfirmation = window.confirm("Are you sure you want to delete your account?");
        if (userConfirmation) {
            
            const form = document.createElement("form");
            form.method = "POST";
            form.action = "/Registerapp/accdelete"; 
            document.body.appendChild(form); 
            form.submit();  
        }
    });
});
