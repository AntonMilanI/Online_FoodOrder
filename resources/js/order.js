window.onload = (function(){
var orderButton = document.getElementById("order");
var inc =    document.getElementById("inc");
var dec =    document.getElementById("dec");
var element = document.getElementById("count");
var pay = document.getElementById("pay");
var x=element.innerText;	



inc.addEventListener("click",  function (){
        element.innerText = ++x;
    });
dec.addEventListener("click",  function (){
	if(x>0)
       element.innerText = --x;
    });
orderButton.addEventListener("click",  function (){
		element.innerText = 0;
		pay.style.display="block";
        document.getElementById("resLbl").style.display="block";
		document.getElementById("res").style.display="block";
		document.getElementById("res").innerHTML =""
		document.getElementById("its").innerHTML =""
		document.getElementById("itemLbl").style.display="block";
		document.getElementById("its").style.display="block";
		inc.style.display="block";
		dec.style.display="block";
		element.style.display="block";
		
		 $.ajax({dataType : 'json',
                    url : "getRestaurants",
                    data : null,
                    type : "GET",
                    processData: false, 
                    contentType:false,
                    success : function(data) {
					var $res = $('#res');
					$.each(data, function(index,value) {
					$res.append(new Option(value, value));
					});
                    },
                    error : function(result){
                        //...;
                    }
                });
    });

	 $("#res").change(function () {
		element.innerText = 0;
		document.getElementById("its").innerHTML =""
	var selItem = $("#res").val();
	 $.ajax({dataType : 'json',
                    url : `getItems/${selItem}`,
                    data : null,
                    type : "GET",
                    processData: false, 
                    contentType:false,
                    success : function(data) {
					var $res = $('#its');
					$.each(data, function(index,value) {
					$res.append(new Option(value, value));
					});
                    },
                    error : function(result){
                        //...;
                    }
                });
});
	$("#its").change(function () {
		element.innerText = 0;
		});
	
	pay.addEventListener("click",  function (){
		var quantity = x;
		if(x==0){
			alert("Please select an Item to place the order.");
		}else{
		var resValue = $("#res").val();
		var itemValue = $("#its").val();
		 $.ajax({dataType : 'text',
                    url : `getBill/${resValue}/${itemValue}/${quantity}`,
                    data : null,
                    type : "GET",
                    processData: false, 
                    contentType:false,
                    success : function(data) {
						if(data ==="fail"){
							alert("Sorry! Item not available.");
						}
						if(data ==="success"){
							alert("Yay! Order has been placed.");
						}
                    },
                    error : function(result){
                        //...;
                    }
                });
		element.innerText = 0;
		}
    });
});