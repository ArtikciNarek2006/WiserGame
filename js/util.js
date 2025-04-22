class Util{
    static map = (num, in_min, in_max, out_min, out_max) => {
        return (num - in_min) / (in_max - in_min) * (out_max - out_min) + out_min;
    }
    static getMediaURL = (media_file) => {
        return "/WiserWeb/res/" + media_file;
    }
}