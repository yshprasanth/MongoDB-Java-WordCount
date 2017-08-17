function reduce(key, counts) {
    // javascript reduce function to be registered in mongo db
    var cnt = 0;
    for (var i = 0; i < counts.length; i++) {
        cnt = cnt + counts[i].count;
    }
    return { count:cnt };
}