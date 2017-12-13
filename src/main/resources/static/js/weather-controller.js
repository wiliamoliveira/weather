$( document ).ready(function() {
	
	// SUBMIT
    $("#weatherForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE DATA
    	var formData = {
    		cityName : $("#cityName").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location + "api/weather/current/city",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "DONE"){
					$("#postLocation").html(result.data.cityName + ", " + result.data.country);
					$("#postTemperatura").html("<img src='img/" + result.data.icon + ".png'/>" + result.data.temp + "º C - " + result.data.description);
					$("#postCondition").html("Humidity: " + result.data.humidity + "% - Wind: " + result.data.windSpeed +" m/s");
				}else{
					$("#postLocation").html(result.data);
					$("#postTemperatura").html("");
					$("#postCondition").html("");
				}
				console.log(result);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();
 
    }
    
    function resetData(){
    	$("#cityName").val("");
    }
})