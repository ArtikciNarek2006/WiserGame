class Scoreboard {
    constructor(parentNode = document.body) {
        this.rated_categories = [];

        this.div = document.createElement("div");
        this.div.id = "scoreboard";
        this.div.classList.add("inActive");
        parentNode.appendChild(this.div);

        this.sort_css_dom = document.createElement("style");
        this.sort_css_dom.id = "scoreboard_css";
        document.getElementsByTagName("head")[0].appendChild(this.sort_css_dom);
    }

    getMinMaxScore() {
        if (this.rated_categories) {
            var max = this.rated_categories[0].score, min = this.rated_categories[0].score;
            for (let i = 1, max_i = this.rated_categories.length; i < max_i; i++) {
                if (this.rated_categories[i].score > max)
                    max = this.rated_categories[i].score;
                if (this.rated_categories[i].score < min)
                    min = this.rated_categories[i].score;
            }
            return [min, max];
        } else {
            return [0, 0];
        }
    }

    create_sort_css() {
        const h_max = 120;
        var [min_score, max_score] = this.getMinMaxScore(), dif = max_score - min_score;
        var h_step = h_max / dif;
        // (num - min_score) / dif * h_max;


        this.sort_css_dom.innerHTML = "";
        var css = "";
        css += `#scoreboard { `
        css += `    --rated-category-height: calc(var(--scoreboard-height) / ${this.rated_categories.length + 1});`
        css += `    --rated-category-margin: calc(calc(var(--scoreboard-height) - calc(var(--rated-category-height) * ${this.rated_categories.length})) / ${this.rated_categories.length * 2});`
        css += `}\n`;

        for (let i = 0, max_i = this.rated_categories.length; i < max_i; i++) {
            css += `#scoreboard .ratedCategory.order_${i}{\n`
            css += `    top: calc(calc(var(--rated-category-height) + var(--rated-category-margin) * 2) * ${i});\n`;
            css += `    background: hsl(${(dif) ? ((this.rated_categories[i].score - min_score) * h_step) : h_max}, 65%, 56%) var(--bg-gradient);\n`;
            css += `}\n\n`;
        }
        this.sort_css_dom.innerHTML = css;
    }

    createCategoriesDOM() {
        this.div.innerHTML = "";

        for (let i = 0, max_i = this.rated_categories.length; i < max_i; i++) {
            var cat_div = document.createElement("div");
            cat_div.classList.add("ratedCategory");
            cat_div.classList.add(`order_${i}`);
            cat_div.id = `ratedCategory_${this.rated_categories[i].id}`;
            this.div.appendChild(cat_div);

            var name_p = document.createElement("p");
            name_p.classList.add("name");
            name_p.innerHTML = this.rated_categories[i].name;
            cat_div.appendChild(name_p);

            var score_p = document.createElement("p");
            score_p.classList.add("score");
            score_p.innerHTML = this.rated_categories[i].score;
            cat_div.appendChild(score_p);
        }
    }

    updateCategoriesDOM() {
        for (let i = 0, max_i = this.rated_categories.length; i < max_i; i++) {
            var cat_div = document.getElementById(`ratedCategory_${this.rated_categories[i].id}`);
            var name_p = cat_div.getElementsByClassName("name")[0];
            var score_p = cat_div.getElementsByClassName("score")[0];

            name_p.innerHTML = this.rated_categories[i].name;
            score_p.innerHTML = this.rated_categories[i].score;
            var regexp = /(order_\d+)/g;
            cat_div.className = cat_div.classList.toString().replace(regexp, "");
            cat_div.classList.add(`order_${i}`)
        }
    }

    sort_categories_arr() {
        this.rated_categories.sort((a, b) => { return b.score - a.score; });
    }

    setCategories(arr) {
        this.rated_categories = arr;
        this.sort_categories_arr();
        this.create_sort_css();
        this.createCategoriesDOM();

    }

    updateCategories(arr) {
        this.rated_categories = arr;
        this.sort_categories_arr();
        this.create_sort_css();
        this.updateCategoriesDOM();
    }

    /**
     * public: returns boolean: true if show, false if hidden;
     */
    is_shown(){
        return !this.div.classList.contains("inActive");
    }

    /**
     *public: run window onload
     */
    init_scoreboard_data() {
        var callback = ((xmlhttp, resp) => {
            this.setCategories(JSON.parse(resp));
        }).bind(this);
        getJSON(callback, "/scoreboard", "GET", true);
    }

    /**
     * public: show and update dom;
     */
    show() {
        var callback = ((xmlhttp, resp) => {
            this.updateCategories(JSON.parse(resp));
            console.log(this, resp);
        }).bind(this);
        getJSON(callback, "/scoreboard", "GET", true);

        if (this.div.classList.contains("inActive")) {
            this.div.classList.remove("inActive");
        }
    }

    /**
     * public: hide dom;
     */
    hide() {
        if (!this.div.classList.contains("inActive")) {
            this.div.classList.add("inActive");
        }
    }
}