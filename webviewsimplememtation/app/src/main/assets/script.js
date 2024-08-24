function time() {
    let clock = new Date();
    let hours = clock.getHours();
    let minutes = clock.getMinutes();
    let seconds = clock.getSeconds();

    if (hours > 12) {
        hours = hours - 12;
    }    

    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    let hourSpan = document.getElementById("hour");
    let minuteSpan = document.getElementById("minute");
    let secondSpan = document.getElementById("second");

    hourSpan.textContent = hours;
    minuteSpan.textContent = minutes;
    secondSpan.textContent = seconds;
}

setInterval(() => {
    time();
}, 1000);

time();
