$(document).bind("pageinit", function() {
    var success = function(message) {
        var jsonObj = eval(message);
        $("#sms-container").empty();
        $( "#sms-container").append("<li data-role='list-divider' role='heading' class='ui-li ui-li-divider ui-btn ui-bar-b'>Morning</li>")
        $( "#sms-template").tmpl(jsonObj.morning).appendTo("#sms-container");
        $( "#sms-container").append("<li data-role='list-divider' role='heading' class='ui-li ui-li-divider ui-btn ui-bar-b'>Noon</li>")
        $( "#sms-template").tmpl(jsonObj.noon).appendTo("#sms-container");
        $( "#sms-container").append("<li data-role='list-divider' role='heading' class='ui-li ui-li-divider ui-btn ui-bar-b'>Evening</li>")
        $( "#sms-template").tmpl(jsonObj.evening).appendTo("#sms-container");
        $(".sms").bind("tap", function(){
            $("#sms-content").html($.trim($(this).text()))
            $("#sms-content").focus()
        })
    }
    var failed = function(e) {
        //alert('Message Failed:' + e);
    }
    window.plugins.sms.fetch(success, failed)
})

$(document).bind("pageinit", function() {
    $("#send-sms").unbind();
    $("#send-sms").bind("tap", function(){
        var content = $("#sms-content").val()
        window.plugins.sms.send("XXXXX",
	    	content,
		    function () { $.mobile.changePage ($("#list-sms"))}, function (e) {alert('Message Failed:' + e);}
	    );
    })
})
