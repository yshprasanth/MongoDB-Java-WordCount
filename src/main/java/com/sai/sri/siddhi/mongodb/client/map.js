function map() {
    // javascript map function to be registered in mongo db
    var cnt = this.content;
    var words = cnt.match(/\w+/g);
    if (words == null) {
        return;
    }
    for (var i = 0; i < words.length; i++) {
        emit({ word:words[i] }, { count:1 });
    }
}