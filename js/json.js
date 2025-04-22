function getJSON(callback = (xmlhttp, response) => {console.warn("getJSON: No callback; response: ", response);}, url, method="GET", async = true){
    getJSON.xmlhttp = new XMLHttpRequest();
    getJSON.url_prefix = getJSON.url_prefix ? getJSON.url_prefix : "";

    getJSON.xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4){
            if(this.status == 200){
                callback(this, this.response);
            }else{
                console.error("getJSON: wrong response status. status:", this.status);
            }
        }
    };

    getJSON.xmlhttp.open(method, getJSON.url_prefix + url, async);
    getJSON.xmlhttp.send();

}
getJSON.url_prefix = settings.URL_PREFIX;
