function start_bg_rotation() {
    start_bg_rotation.BG_GRADIENT = 315;
    setInterval(() => {
        start_bg_rotation.BG_GRADIENT = (start_bg_rotation.BG_GRADIENT + 0.1) % 360;
        document.body.style.background = `linear-gradient(${start_bg_rotation.BG_GRADIENT}deg,  rgb(80, 205, 186) 3%, rgba(60,132,206,1) 38%, rgb(48, 238, 203) 68%, rgb(25, 240, 255) 98%)`;
        document.body.style.backgroundSize = "200% 200%";
        document.body.style.backgroundAttachment = "fixed";
    }, 30);
}


// Program init;
const TIMER = new Timer(), SCOREBOARD = new Scoreboard(), TASK = new Task();


window.onload = () => {
    TIMER.init();
    if(settings.bg_rotation) start_bg_rotation();
    SCOREBOARD.init_scoreboard_data();
    TASK.init_task_data();
}

window.addEventListener("keyup", (e) => {
    // console.log(e);
    if (e.code == "NumpadAdd") {
        if(SCOREBOARD.is_shown())
            SCOREBOARD.hide();
        else
            SCOREBOARD.show();
    }

    if(e.code == "Space"){
        TASK.start();
    }
    if(e.code == "KeyP" && e.shiftKey){
        TASK.stop();
    }
    if(e.code == "KeyS" && e.shiftKey){
        if(TASK.dom.div.classList.contains("inActive"))
            TASK.show_root_dom();
        else
            TASK.hide_root_dom();
    }
    if(e.code == "KeyN" && e.shiftKey){
        TASK.next();
    }
	
	if(e.code == "KeyB" && e.shiftKey){
        TASK.previous();
    }
	
	if(e.code == "Enter"){
        TASK.show_answer();
    }
});