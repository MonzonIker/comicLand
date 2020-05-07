document.addEventListener("DOMContentLoaded", function (event) {
    createHTML();

  })
  
  function createHTML() {
   
    $.ajax({ 
      url: 'http://localhost:8080/Komikilandia_IM/ApiComics',
      dataType: 'json', 
  
      success: function (myJsonObject) {

        console.log(myJsonObject);

        var rowOpenHTML = "<div class='row justify-content-center'>"
        var colOpenHTML = "<div class='col-lg-3 m-1'>"
        var myHTMLcode = "";
  
        myHTMLcode=rowOpenHTML;
        
        for (let i = 0; i < myJsonObject.length; i++) {
  
            myHTMLcode += colOpenHTML;  
  
            myHTMLcode += "<div class='card h-100 text-center'> <div class='card-body'><h4 class='card-title'>" + myJsonObject[i].titulo + "</h4> <img class='card-img-bottom' src='" + myJsonObject[i].imagen + "'></div></div></div>";
          }
        myHTMLcode += "</div>";
        document.getElementById("main").innerHTML += myHTMLcode;
          

    },
      error: function (xhr) {
        alert("AJAX error : " + xhr.status + " " + xhr.statusText);
      }
    });
  
  
  }
  