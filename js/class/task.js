class Task {
    constructor(parentNode = document.body) {
        this.categories = [];
        this.questions = [];
        this.answer = {};
        this.question_i = -1;
        this.timer = new Timer();
        this.questions_showed_indexes = [];
        this.is_started = false;
        this.question_inited = false;

        this.question_duration = 10; // seconds;
        this.timeout_id;

        this.default_question_text = "No Question Data";
        this.default_answer_text = "No Answer Data";
        this.default_category_text = "No Category Name";

        this.dom = {};
        this.dom.div = document.createElement("div");
        this.dom.div.id = "task";
        this.dom.div.classList.add("inActive");
        parentNode.appendChild(this.dom.div);
        this.prepareDOM();

        this.current_media = {};
        this.init_media("null", "", 100, this.dom.question.media);
    }

    prepareDOM() {
        this.dom.div.innerHTML = "";

        // buttons
        this.dom.next_btn = document.createElement("button");
        this.dom.next_btn.classList.add("button");
        this.dom.next_btn.id = "next_btn";
        this.dom.next_btn.onclick = (() => {this.next(); this.start();}).bind(this);
        this.dom.next_btn.innerHTML = "next";
        this.dom.div.appendChild(this.dom.next_btn);

        this.dom.show_ans_btn = document.createElement("button");
        this.dom.show_ans_btn.classList.add("button");
        this.dom.show_ans_btn.id = "show_ans_btn";
        this.dom.show_ans_btn.onclick = this.show_answer.bind(this);
        this.dom.show_ans_btn.innerHTML = "show answer";
        this.dom.div.appendChild(this.dom.show_ans_btn);


        // category info
        this.dom.category = {};
        this.dom.category.div = document.createElement("div");
        this.dom.category.div.id = "category";
        this.dom.div.appendChild(this.dom.category.div);

        this.dom.category.p = document.createElement("p");
        this.dom.category.p.innerHTML = this.default_category_text;
        this.dom.category.div.appendChild(this.dom.category.p);


        // start question dom
        this.dom.question = {};
        this.dom.question.div = document.createElement("div");
        this.dom.question.div.id = "question";
        this.dom.div.appendChild(this.dom.question.div);

        this.dom.question.p = document.createElement("p");
        this.dom.question.p.classList.add("text");
        this.dom.question.p.innerHTML = this.default_question_text;
        this.dom.question.div.appendChild(this.dom.question.p);

        this.dom.question.media = {};
        this.dom.question.media.div = document.createElement("div");
        this.dom.question.media.div.classList.add("media");
        this.dom.question.div.appendChild(this.dom.question.media.div);

        this.dom.question.media.audio = document.createElement("audio");
        this.dom.question.media.video = document.createElement("video");
        this.dom.question.media.img = document.createElement("div");
        this.dom.question.media.audio.classList.add("inActive");
        this.dom.question.media.video.classList.add("inActive");
        this.dom.question.media.img.classList.add("inActive");
        this.dom.question.media.img.classList.add("img");
        this.dom.question.media.video.controls = true;
        this.dom.question.media.audio.controls = true;
        this.dom.question.media.div.appendChild(this.dom.question.media.audio);
        this.dom.question.media.div.appendChild(this.dom.question.media.video);
        this.dom.question.media.div.appendChild(this.dom.question.media.img);
        // end question dom

        // start answer dom
        this.dom.answer = {};
        this.dom.answer.div = document.createElement("div");
        this.dom.answer.div.id = "answer";
        this.dom.answer.div.classList.add("inActive");
        this.dom.div.appendChild(this.dom.answer.div);

        this.dom.answer.p = document.createElement("p");
        this.dom.answer.p.classList.add("text");
        this.dom.answer.p.innerHTML = this.default_answer_text;
        this.dom.answer.div.appendChild(this.dom.answer.p);

        this.dom.answer.media = {};
        this.dom.answer.media.div = document.createElement("div");
        this.dom.answer.media.div.classList.add("media");
        this.dom.answer.div.appendChild(this.dom.answer.media.div);

        this.dom.answer.media.audio = document.createElement("audio");
        this.dom.answer.media.video = document.createElement("video");
        this.dom.answer.media.img = document.createElement("div");
        this.dom.answer.media.audio.classList.add("inActive");
        this.dom.answer.media.video.classList.add("inActive");
        this.dom.answer.media.img.classList.add("inActive");
        this.dom.answer.media.img.classList.add("img");
        this.dom.answer.media.video.controls = true;
        this.dom.answer.media.audio.controls = true;
        this.dom.answer.media.div.appendChild(this.dom.answer.media.audio);
        this.dom.answer.media.div.appendChild(this.dom.answer.media.video);
        this.dom.answer.media.div.appendChild(this.dom.answer.media.img);
        //end answer dom
    }

    show_category(id) {
        var cat = {};
        for (var i = 0, max_i = this.categories.length; i < max_i; i++) {
            if (this.categories[i].id == id) {
                cat = this.categories[i];
                break;
            }
        }

        if (!cat) {
            console.error(`Task: show_category: unable to find catedory [id=${id}]`);
            return;
        }

        // this.dom.category.p.innerHTML = `<i>Team name:</i> ${(cat.name) ? (cat.name) : this.default_category_text}`;
		this.dom.category.p.innerHTML = `<i>Question number: </i> ${this.question_i + 1}`;
    }

    show_root_dom() {
        if (this.dom.div.classList.contains("inActive")) this.dom.div.classList.remove("inActive");
    }

    hide_root_dom() {
        if (!this.dom.div.classList.contains("inActive")) this.dom.div.classList.add("inActive");
    }

    init_media(media_type, media_file, media_vol, media_dom) {
        this.current_media = { type: media_type, volume: media_vol, url: media_file, dom: media_dom, audio: media_dom.audio, video: media_dom.video, img: media_dom.img };
        if (!this.current_media.audio.classList.contains("inActive")) this.current_media.audio.classList.add("inActive");
        if (!this.current_media.video.classList.contains("inActive")) this.current_media.video.classList.add("inActive");
        if (!this.current_media.img.classList.contains("inActive")) this.current_media.img.classList.add("inActive");
        if (this.current_media.type == "AUDIO") {
            this.current_media.audio.src = Util.getMediaURL(this.current_media.url);
            this.current_media.audio.volume = this.current_media.volume / 100;
            this.current_media.audio.classList.remove("inActive");
        } else if (this.current_media.type == "VIDEO") {
            this.current_media.video.src = Util.getMediaURL(this.current_media.url);
            this.current_media.video.volume = this.current_media.volume / 100;
            this.current_media.video.classList.remove("inActive");

        } else if (this.current_media.type == "IMG") {
            this.current_media.img.style.backgroundImage = `url(${Util.getMediaURL(this.current_media.url)})`;
            this.current_media.img.classList.remove("inActive");
        }
    }

    play_media() {
        if ((this.current_media.type == "null") || (this.current_media.type == null) || (this.current_media.type == "NULL")) return;

        if (this.current_media.type == "AUDIO") {
            this.current_media.audio.play();
        } else if (this.current_media.type == "VIDEO") {
            this.current_media.video.play();

        } else if (this.current_media.type == "IMG") {
            this.current_media.img.classList.remove("inActive");
        }
    }

    stop_media() {
        if ((this.current_media.type == "null") || (this.current_media.type == null) || (this.current_media.type == "NULL")) return;
        if (!this.current_media.audio.classList.contains("inActive")) this.current_media.audio.classList.add("inActive");
        if (!this.current_media.video.classList.contains("inActive")) this.current_media.video.classList.add("inActive");
        if (!this.current_media.img.classList.contains("inActive")) this.current_media.img.classList.add("inActive");
        if (this.current_media.type == "AUDIO") {
            this.current_media.audio.src = "";
            this.current_media.audio.volume = 0;
            this.current_media.audio.pause();
            this.current_media.audio.currentTime = 0;
        } else if (this.current_media.type == "VIDEO") {
            this.current_media.video.src = "";
            this.current_media.video.volume = 0;
            this.current_media.video.pause();
            this.current_media.video.currentTime = 0;
        } else if (this.current_media.type == "IMG") {
            this.current_media.img.style.backgroundImage  = "";
        }
    }

    show_answer() {
        this.stop();
        this.show_root_dom();

        if (!this.dom.question.div.classList.contains("inActive")) this.dom.question.div.className = ("inActive");
        if (this.dom.answer.div.classList.contains("inActive")) this.dom.answer.div.classList.remove("inActive");

        if (Object.keys(this.answer).length) {
            this.dom.answer.p.innerHTML = (this.answer.text) ? this.answer.text : this.default_answer_text;
            this.init_media(this.answer.media_type, this.answer.media_file, this.answer.audio_volume, this.dom.answer.media);
            this.play_media();
        } else {
            console.log("Task: show_answer: no answer obj");
            this.dom.answer.p.innerHTML = (this.answer.text) ? this.answer.text : this.default_answer_text;
            this.init_media("null", "", 100, this.dom.question.media);
        }
    }

    update_dom_data() {
        this.prepareDOM();

        this.show_category(this.questions[this.question_i].category_id);
		var regex_br = new RegExp("<br>+");
        var question_text = (this.questions[this.question_i].text) ? (this.questions[this.question_i].text) : this.default_question_text;
		this.dom.question.p.innerHTML = question_text.replace(regex_br, "<br><br>");
        this.init_media(this.questions[this.question_i].media_type, this.questions[this.question_i].media_file, this.questions[this.question_i].media_volume, this.dom.question.media);
    }

    /**
     * public: sets question index
     * @param {*} i question index;
     */
    setQuestions_i(i = 0) {
        this.stop();
        if (i >= this.questions.length || i < 0) {
            if (this.questions.length) {
                // this.question_i = 0;
            } else {
                this.question_i = -1;
            }
        } else {
            this.question_i = i;
        }
        this.load_current_answer();
        this.update_dom_data();
    }

    setCategories(arr) {
        this.categories = arr;
    }

    setQuestions(arr) {
        this.questions = arr;
        this.questions_showed_indexes = [];
        this.setQuestions_i(0);
    }

    setAnswer(arr) {
        if (arr.length) {
            this.answer = arr[0];
        } else {
            console.error(`getJSON: \"/getfromdb?tablename=answer&answer_id=${this.questions[this.question_i].answer_id}\"`, "Inavlid answer: array is empty");
            this.answer = {};
        }
    }

    load_current_answer() {
        // check answer_id exists;
        this.answer = {};
        if ((this.questions[this.question_i].answer_id != undefined)  && !isNaN(this.questions[this.question_i].answer_id)) {
            var callback = ((xmlhttp, resp) => {
                this.setAnswer(JSON.parse(resp));
            }).bind(this);
            getJSON(callback, `/getfromdb?tablename=answer&answer_id=${this.questions[this.question_i].answer_id}`, "GET", true);
        }
    }

    /**
     *public: run window onload
     */
    init_task_data() {
        var callback = ((xmlhttp, resp) => {
            this.setCategories(JSON.parse(resp));
        }).bind(this);
        getJSON(callback, "/getfromdb?tablename=category", "GET", true);

        var callback = ((xmlhttp, resp) => {
            this.setQuestions(JSON.parse(resp));
        }).bind(this);
        getJSON(callback, "/getfromdb?tablename=question", "GET", true);
    }


    /**
     * public: starts task;
     */
    start() {
        if (!this.is_started) {
            this.show_root_dom();
            this.update_dom_data();
            this.timer.start(this.question_duration);
            this.timeout_id = setTimeout(this.show_answer.bind(this), this.question_duration * 1000);
            this.play_media();
            this.questions_showed_indexes.push(this.question_i);
            this.is_started = true;
        }
    }

    /**
     * public: stops task;
     */
    stop() {
        this.is_started = false;
        this.timer.stop();
        clearTimeout(this.timeout_id);
        this.stop_media();

        // this.hide_root_dom();
    }

    /**
     * public: set question_i to next:
     */
    next() {
        // this.setQuestions_i(this.get_unshowed_random_i());
        this.setQuestions_i(this.question_i + 1);
    }
	
	/**
     * public: set question_i to previous:
     */
    previous() {
        // this.setQuestions_i(this.get_unshowed_random_i());
        this.setQuestions_i(this.question_i - 1);
    }

    /**
     * public: returns unwatched question index;
     */
    get_unshowed_random_i() {
        var indexes = [];
        for (var i = 0, max_i = this.questions.length; i < max_i; i++) {
            var showed = false;
            for (var j = 0, max_j = this.questions_showed_indexes.length; j < max_j; j++) {
                if (i == this.questions_showed_indexes[j]) {
                    showed = false;
                    break;
                }
            }
            if (!showed) {
                indexes.push(i);
            }
        }

        return indexes[Math.trunc(Math.random() * (indexes.length - 1))];

    }
}