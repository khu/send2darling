$(document).bind("pageinit", function() {
    var success = function(message) {
        //var jsonObj = {morning:["aaa","aaa","aaa"],noon:["bbbb","bbbb","bbbb","bbbb"], evening:["ccccccc", "ccc"]};
        var jsonObj = eval(message);
        $( "#sms-container").append("<li data-role='list-divider' role='heading' class='ui-li ui-li-divider ui-btn ui-bar-b'>Morning</li>")
        $( "#sms-template").tmpl(jsonObj.morning).appendTo("#sms-container");
        $( "#sms-container").append("<li data-role='list-divider' role='heading' class='ui-li ui-li-divider ui-btn ui-bar-b'>Noon</li>")
        $( "#sms-template").tmpl(jsonObj.noon).appendTo("#sms-container");
        $( "#sms-container").append("<li data-role='list-divider' role='heading' class='ui-li ui-li-divider ui-btn ui-bar-b'>Evening</li>")
        $( "#sms-template").tmpl(jsonObj.evening).appendTo("#sms-container");
    }
    var failed = function(e) {
        alert('Message Failed:' + e);
    }
    window.plugins.sms.fetch(success, failed)
})

$(document).bind("pageinit", function() {
    $(".sms").click(function(){
        window.plugins.sms.send("13717709061", "message", function(message) {});
    }, function(e) {
        alert('Message Failed:' + e);
    })
})

