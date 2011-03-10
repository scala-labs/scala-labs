document.write = function(str){
    var moz = !window.opera && !/Apple/.test(navigator.vendor);

    // Watch for writing out closing tags, we just
    // ignore these (as we auto-generate our own)
    if ( str.match(/^<\//) ) return;

    // Make sure & are formatted properly, but Opera
    // messes this up and just ignores it
    if ( !window.opera )
        str = str.replace(/&(?![#a-z0-9]+;)/g, "&amp;");

    // Watch for when no closing tag is provided
    // (Only does one element, quite weak)
    str = str.replace(/<([a-z]+)(.*[^\/])>$/, "<$1$2></$1>");

    // Mozilla assumes that everything in XHTML innerHTML
    // is actually XHTML - Opera and Safari assume that it's XML
    if ( !moz )
        str = str.replace(/(<[a-z]+)/g, "$1 xmlns='http://www.w3.org/1999/xhtml'");

    // The HTML needs to be within a XHTML element
    var div = document.createElementNS("http://www.w3.org/1999/xhtml","div");
    div.innerHTML = str;

    // Find the last element in the document
    var pos;

    // Opera and Safari treat getElementsByTagName("*") accurately
    // always including the last element on the page
    if ( !moz ) {
        pos = document.getElementsByTagName("*");
        pos = pos[pos.length - 1];

        // Mozilla does not, we have to traverse manually
    } else {
        pos = document;
        while ( pos.lastChild && pos.lastChild.nodeType == 1 )
            pos = pos.lastChild;
    }

    // Add all the nodes in that position
    var nodes = div.childNodes;
    while ( nodes.length )
        pos.parentNode.appendChild( nodes[0] );
};