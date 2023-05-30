document.getElementById("btnTime").addEventListener("click", Time_Response);
document.getElementById("btnAlert").addEventListener("click", alert_box);

function Time_Response(){
    const Target = document.getElementById("time");
    if (Target.innerText == "") {
        let today = new Date();
        Target.innerText = today.toString();
    }
    else Target.innerText = "";
}

function alert_box(){
    alert("Hello!!");
}