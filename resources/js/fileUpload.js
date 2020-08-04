var files = [];
$(document)
        .on(
                "change",
                "#fileLoader",
                function(event) {
                 files=event.target.files;
                });

$(document)
        .on(
                "click",
                "#fileSubmit",
                function() {
			var dateCreated=document.getElementById("created").valueAsDate;
			var filee=document.getElementById("fileLoader").value;
			if(dateCreated === null){
			alert("Please Select a Date To Upload");
       		return false;
			}
			if(filee == ''){
			alert("Please Select a file To Upload");
       		return false;
			}
				document.getElementById("myImg1").style.display="block";
				document.getElementById("myImg").style.display="none";
				document.getElementById("fileLoader").disabled=true
				document.getElementById("created").disabled=true
				document.getElementById("fileSubmit").disabled=true
                processUpload();
                });

function processUpload()
          {
			var dateCreated=document.getElementById("created").valueAsDate;
              var oMyForm = new FormData();
              oMyForm.append("file", files[0]);
             $
                .ajax({dataType : 'json',
                    url : `fileUpload/${dateCreated}`,
                    data : oMyForm,
                    type : "POST",
                    enctype: 'multipart/form-data',
                    processData: false, 
                    contentType:false,
                    success : function(data) {
					document.getElementById("myTable").style.display="block";
					document.getElementById("order").style.display="block";
						data.forEach(display);
                    },
                    error : function(result){
                        //...;
                    }
                });
          }

var ViewModel = function () {
    var self = this;

    self.Items = ko.observableArray([]);

    self.addItem = function (item) {
        self.Items.push(item);      
    }
};

function display(value, index, array) {
	 var dishName =value.dishName;
    var quantity =value.quantity;
	var price =value.price;
	var weight =value.weight;
	var restaurant =value.restaurant;
	var tableRef = document.getElementById('myTable').getElementsByTagName('tbody')[0];
	 
	var myHtmlContent = `<tr><td>${dishName}</td><td>${quantity}</td><td>${price}</td><td>${weight}</td><td>${restaurant}</td></tr>`;
	var newRow = tableRef.insertRow(tableRef.rows.length);
	newRow.innerHTML = myHtmlContent;

}

