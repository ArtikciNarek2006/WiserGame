class Timer {
    static obj = new Timer();
    constructor() {
        if (Timer.obj)
            return Timer.obj;
        else {
            return this;
        }
    }

    /**
     * creates the timer dom elements and appends as child of document.body
     */
    #init_DOM() {
        if (!this.init_DOM_done) {
            this.div = document.createElement("div");
            this.div.id = "timer";
            this.div.classList.add("inActive");

            this.timer_text_p = document.createElement("p");
            this.timer_text_p.id = "text";
            this.timer_text_p.innerText = "Time Left:";
            this.div.appendChild(this.timer_text_p);

            this.timer_time_p = document.createElement("p");
            this.timer_time_p.id = "time";
            this.timer_time_p.innerText = "oo:oo";
            this.div.appendChild(this.timer_time_p);

            document.body.appendChild(this.div);
            this.init_DOM_done = true;
        }
    }

    #show_DOM() {
        if (this.init_DOM_done && this.div.classList.contains("inActive")) {
            this.div.classList.remove("inActive");
        }
    }

    #hide_DOM() {
        if (this.init_DOM_done && !this.div.classList.contains("inActive")) {
            this.div.classList.add("inActive");
        }
    }

    /**
     * DONT CALL this method
     */
    update_interval() {
        this.update_DOM();
        this.real_duration = this.get_real_duration();
        if (this.real_duration >= this.duration) {
            this.stop();
        }
    }

    #start_interval() {
        this.interval_id = window.setInterval(this.update_interval.bind(this), 250);
    }

    #stop_interval() {
        window.clearInterval(this.interval_id);
    }

    /**
     * updates timer text in dom
     */
    update_DOM() {
        if (this.init_done) {
            var sec = Math.floor(this.get_time_left() / 1000);
            var min = Math.trunc(sec / 60);
            sec = sec % 60;
            this.timer_time_p.innerHTML = "";
            this.timer_time_p.innerText = (min > 9) ? min.toString() : ("0" + min);
            this.timer_time_p.innerText += ":";
            this.timer_time_p.innerText += (sec > 9) ? sec.toString() : ("0" + sec);
        }
    }

    /**
     * call on window onload 1 times
     */
    init() {
        this.#init_DOM();
        this.interval_id = 0;
        this.start_time = -1;
        this.duration = -1;
        this.real_duration = -1;
        this.started = false;

        this.init_done = true;
    }

    /**
     * 
     * @returns time elapsed from timer start in ms or {-1} if timer isn't started
     */
    get_real_duration() {
        if (this.init_done) {
            if (this.started) {
                return Date.now() - this.start_time;
            } else {
                return this.real_duration;
            }
        } else {
            return -1;
        }
    }

    /**
     * 
     * @returns time left to timer end in ms or {0} if timer isn't started
     */
    get_time_left() {
        if (this.init_done && this.started) {
            return this.duration - this.get_real_duration() + 1000;
        } else {
            return 0;
        }
    }

    /**
     * 
     * @param {Number} duration timer duration in seconds
     */
    start(duration = 0) {
        if ((!this.started) && (duration > 0) && (this.init_done)) {
            this.start_time = Date.now();
            this.duration = duration * 1000;
            this.real_duration = 0;
            this.#show_DOM();
            this.#start_interval();
            this.update_DOM();
            console.log(`Timer: started with duration: ${this.duration}ms`);
            this.started = true;
        }
    }

    /**
     * stops timer
     */
    stop() {
        if (this.started) {
            this.#stop_interval();
            this.update_DOM();
            this.#hide_DOM();
            console.log(`Timer: stoped with real duration: ${this.real_duration}ms`);
            this.started = false;
        }
    }
}