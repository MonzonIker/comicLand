document.addEventListener("DOMContentLoaded", function (event) {
  createHTML();
  document.getElementById("main").addEventListener('click',function(event){
    infoOlike(event);
  });

})

function createHTML() {

  $.ajax({
    url: 'http://localhost:8081/Komikilandia_IM/ApiComics',
    dataType: 'json',

    success: function (myJsonObject) {

      console.log(myJsonObject);

      var rowOpenHTML = "<div class='row justify-content-center'>"
      var colOpenHTML = "<div class='col-lg-3 m-1'>"
      var myHTMLcode = "";

      myHTMLcode = rowOpenHTML;

      for (let i = 0; i < myJsonObject.length; i++) {

        myHTMLcode += colOpenHTML;

        myHTMLcode += '<div class="card" style=" width: 18rem; ">\
            <img src="'+ myJsonObject[i].imagen + '" class="card-img-top" alt="..." height="439">\
            <div class="card-body text-center">\
                <h5 class="card-title">'+ myJsonObject[i].nombre + '</h5>\
                <p class="card-text">'+ myJsonObject[i].titulo + '</p>\
                <a href="#" class="btn btn-info" data-toggle="modal" data-tipo="info" data-taget="myModal" data-id="'+ myJsonObject[i].id + '">Más info</a>\
            </div>\
            <a href="#" class="btn btn-secondary" data-tipo="like" data-id="' + myJsonObject[i].id + '"><img src="img/dedito.png" class=" " width="25%"> : '+ myJsonObject[i].num_likes + '</a>\
        </div></div>';
      }

      myHTMLcode += "</div>";
      document.getElementById("main").innerHTML += myHTMLcode;


    },
    error: function (xhr) {
      alert("AJAX error : " + xhr.status + " " + xhr.statusText);
    }
  });


}

function infoOlike(event){

  console.log(event);

  var clickedButton = event.target;  

  if (clickedButton.dataset.tipo == "like") {
    if (clickedButton.dataset.id != null) {   // to confirm that it's a button
    // alert(clickedButton.dataset.id);
   
    $.ajax({
      type:'POST',  
      data : {'id': clickedButton.dataset.id},
      url: 'http://localhost:8081/Komikilandia_IM/ApiSumarLike',
      //url: 'http://www.omdbapi.com/?i=' + clickedButton.dataset.id + '&   apikey=d969f880',
      dataType: 'text', //specifying here the response type, there's no need to parse the response.
      
      success: function (response, status, xhr) {
        if(xhr.status==200){
          alert("Like sumado "+xhr.statusText);
          window.location.href="index.html";
        }
      },
      error: function (xhr) {
        alert("An AJAX error occured: " + xhr.status + " " + xhr.statusText);
      }
    });

  }
  }else if(clickedButton.dataset.tipo == "info"){

  }
    
}