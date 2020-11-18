$.fn.extend({
    treed: function (o) {

        var openedClass = 'glyphicon-minus-sign';
        var closedClass = 'glyphicon-plus-sign';

        if (typeof o != 'undefined') {
            if (typeof o.openedClass != 'undefined') {
                openedClass = o.openedClass;
            }
            if (typeof o.closedClass != 'undefined') {
                closedClass = o.closedClass;
            }
        }
        ;

        //initialize each of the top levels
        var tree = $(this);
        tree.addClass("tree");
        tree.find('li').has("ul").each(function () {
            var branch = $(this); //li with children ul
            branch.prepend("<i class='indicator glyphicon " + closedClass + "'></i>");
            branch.addClass('branch');
            branch.on('click', function (e) {
                if (this == e.target) {
                    var icon = $(this).children('i:first');
                    icon.toggleClass(openedClass + " " + closedClass);
                    $(this).children().children().toggle();
                }
            })
            branch.children().children().toggle();
        });
        //fire event from the dynamically added icon
        tree.find('.branch .indicator').each(function () {
            $(this).on('click', function () {
                $(this).closest('li').click();
            });
        });
        //fire event to open branch if the li contains an anchor instead of text
        tree.find('.branch>a').each(function () {
            $(this).on('click', function (e) {
                $(this).closest('li').click();
                e.preventDefault();
            });
        });
        //fire event to open branch if the li contains a button instead of text
        tree.find('.branch>button').each(function () {
            $(this).on('click', function (e) {
                $(this).closest('li').click();
                e.preventDefault();
            });
        });
    }
});



function formatMethodDesc(desc) {
    // split desc, to return type string + method params
    let index = desc.indexOf(")");
    return [desc.substr(index+1), desc.substr(0, index+1)];
}

function formatMethodName(name) {
    // if name contains characters like '<' or '>', we need to skip it or html will display incorrect.
    return name.replace('<', '').replace('>', '');
}

function printMethodTree(nodeTree) {
    let methodHtml = "<li>";
    let method = nodeTree['method'];
    let methodStr = "";
    if (method['methodName'] == null) {
        methodStr = JSON.stringify(method);
    } else {
        let descArr = formatMethodDesc(method['desc']);
        methodStr = descArr[0] + "::" + method['owner'] + "::" + formatMethodName(method['methodName']) + descArr[1];
    }
    methodHtml += methodStr;

    let callers = nodeTree['callers'];
    if (callers.length !== 0) {
        methodHtml += "<ul>";
        for (let i = 0; i < callers.length; i++) {
            methodHtml += printMethodTree(callers[i]);
        }
        methodHtml += "</ul>";
    }
    methodHtml += "</li>";
    return methodHtml;
}

function searchQuery(queryStr) {
    // Get query data
    let queryUrl = "/query/" + escape(queryStr);
    console.log("Query str: " + queryStr)
    $.getJSON(queryUrl, function (data) {
        // populate search condition to result
        let fakeRoot = {}
        let fakeMethod = {}
        fakeMethod['desc'] = '([Ljava/lang/String;)V';
        fakeMethod['methodName'] = 'fakeRootMethod';
        fakeMethod['owner'] = 'fakeOwnerClass';
        fakeRoot['method'] = fakeMethod;
        fakeRoot['callers'] = data;
        let htmlStr = printMethodTree(fakeRoot);

        // delete child node first
        $('#tree1').empty();
        $('#tree1').append(htmlStr)
        //Initialization of treeviews
        $('#tree1').treed();
    });
}

$( "#search" ).click(function() {
    searchQuery($("#searchCondition").val());
});

// search box enter key down event
$('#searchCondition').keypress(function (e) {
    let key = e.which;
    if(key === 13)  // the enter key code
    {
        searchQuery($("#searchCondition").val());
        return false;
    }
});