document.addEventListener("DOMContentLoaded", function (event) {
  /* Rellenar cards */
  createHTML();

  /* EventListener para los botones de mas info y like */
  document.getElementById("main").addEventListener('click', function (event) {
    infoOlike(event);
  });
  /* EventListener para los botones dentro de el modal de cada comic */
  document.getElementById("confiModal").addEventListener('click', function (event) {
    updateOdelete(event);
  });

})

function createHTML() {

  $.ajax({
    url: 'http://localhost:8081/Komikilandia_IM/ApiComics',
    dataType: 'json',

    success: function (myJsonObject) {

      /* para comprobar el contenido del objetoJsaon */
      console.log(myJsonObject);

     /* variables que contendran nuestro html a insertar */
      var rowOpenHTML = "<div class='row justify-content-center'>"
      var colOpenHTML = "<div class='col-lg-3 col-6 m-1'>"
      var myHTMLcode = "";

      myHTMLcode = rowOpenHTML;

      /* recorremos todo el objetoJson y rellenamos y añadimos cards con la infrmacion de cada objeto */
      for (let i = 0; i < myJsonObject.length; i++) {

        myHTMLcode += colOpenHTML;

        myHTMLcode += '<div class="card h-100" >\
            <img src="'+ myJsonObject[i].imagen + '" class="card-img-top" alt="..." height="439">\
            <div class="card-body text-center">\
                <h5 class="card-title">'+ myJsonObject[i].nombre + '</h5>\
                <p class="card-text">'+ myJsonObject[i].titulo + '</p>\
                <button type="button" class="btn btn-info" data-tipo="info" data-toggle="modal" data-target="#myModal" data-id="' + myJsonObject[i].id + '">More info</button>\
            </div>\
            <a href="#" class="btn btn-secondary" data-tipo="like" data-id="' + myJsonObject[i].id + '"><img src="img/dedito.png" class=" " width="25%"> : ' + myJsonObject[i].num_likes + '</a>\
        </div></div>';
      }

      myHTMLcode += "</div>";
      /* Lo insertamos en el container con el id main */
      document.getElementById("main").innerHTML += myHTMLcode;


    },
    error: function (xhr) {
      alert("AJAX error : " + xhr.status + " " + xhr.statusText);
    }
  });


}

function infoOlike(event) {
  /* para confirmar que el evento contiene la informacion correcta */
  console.log(event);

  var clickedButton = event.target;
  /* primero comprobamos que boton se ha pulsado y si contiene el dataset-tipo que queremos entonces entrara ha hacer su funcion */

  /* cuando es de tipo like */
  if (clickedButton.dataset.tipo == "like") {
    if (clickedButton.dataset.id != null) {   // confirmamos que tiene una id
      $.ajax({
        type: 'POST',
        data: { 'id': clickedButton.dataset.id },
        url: 'http://localhost:8081/Komikilandia_IM/ApiSumarLike',
        dataType: 'text',

        success: function (response, status, xhr) {
          /* si el status es 200 mandamos la alerta con la confirmacion */
          if (xhr.status == 200) {
            alert("Like sumado " + xhr.statusText);
            window.location.href = "index.html";
          }
        },
        error: function (xhr) {
          alert("An AJAX error occured: " + xhr.status + " " + xhr.statusText);
        }
      });

    }
  } else if (clickedButton.dataset.tipo == "info") { //cuando el tipo es info
    if (clickedButton.dataset.id != null) {   // confirmamos que tenga una id

      $.ajax({
        data: { 'id': clickedButton.dataset.id },
        url: 'http://localhost:8081/Komikilandia_IM/ApiComic',
        dataType: 'json',

        success: function (myJsonObject) {
          fillModal(myJsonObject);  // llamamos a una funcion que rellenara los modal con la info de los objetos
        },
        error: function (xhr) {
          alert("An AJAX error occured: " + xhr.status + " " + xhr.statusText);
        }
      });

    }
  }

}

function fillModal(myJsonObject) {

  /* variables con el contenido de html que vamos a insertar */
  var modalHeaderHTML = "<h4 class='modal-title'>" + myJsonObject.nombre + "</h4>";
  var confHeaderHTML = "<h3 class='modal-title'>Seguro que quieres eliminar:</h4>";
  var modalBodyHTML = "";
  var confBodyHTML = "<p class='text-center'>" + myJsonObject.nombre + " | " + myJsonObject.titulo + "</p>";
  var modalFooterHTML = "<button type='button' class='btn btn-info' data-tipo='info' data-toggle='modal' data-target='#confiModal' data-dismiss='modal'>Delete</button> <button type='button' class='btn btn-danger' data-dismiss='modal'>Close</button>";
  var confFooterHTML = "<button type='button' class='btn btn-danger' data-id='" + myJsonObject.id + "' data-tipo='delete'>Delete</button> <button type='button' class='btn btn-danger' data-dismiss='modal'>Close</button>";

  console.log(myJsonObject);  // para comprobar que obtenemos la informacion de el objeto

  // modal header
  modalHeaderHTML += "<p>| " + myJsonObject.titulo + " | " +
    myJsonObject.num + " | " +
    myJsonObject.fecha_publicacion + " | " +
    myJsonObject.genero.nombre + " | Likes: " +
    myJsonObject.num_likes +
    "</p>";

  // modal body               
  modalBodyHTML += "<p class='text-center'><img width='75%' src='" + myJsonObject.imagen + "'</p>";

  document.querySelector(".infH").innerHTML = modalHeaderHTML;  //insertamos el html en las clases correspondientes
  document.querySelector(".infB").innerHTML = modalBodyHTML;
  document.querySelector(".infF").innerHTML = modalFooterHTML;
  document.querySelector(".confH").innerHTML = confHeaderHTML;
  document.querySelector(".confB").innerHTML = confBodyHTML;
  document.querySelector(".confF").innerHTML = confFooterHTML;
}

function updateOdelete(event) {
  /* para comprobar de que tipo de boton dentro del modal se trata */
  console.log(event);

  var clickedButton = event.target;

  /* cuando el tipo es delete */
  if (clickedButton.dataset.tipo == "delete") {
    if (clickedButton.dataset.id != null) { //comprobamos que tenga una id

      $.ajax({
        type: 'POST',
        data: { 'id': clickedButton.dataset.id },
        url: 'http://localhost:8081/Komikilandia_IM/ApiDeleteComic',
        dataType: 'text', 

        success: function (response, status, xhr) {
          /* si el status es 200 mandamos la alerta con la confirmacion */
          if (xhr.status == 200) {
            alert("Comic borrado " + xhr.statusText);
            window.location.href = "index.html";
          }
        },
        error: function (xhr) {
          alert("An AJAX error occured: " + xhr.status + " " + xhr.statusText);
        }
      });

    }
  } else if (clickedButton.dataset.tipo == "update") { //cuando el tipo es update
    if (clickedButton.dataset.id != null) {
      //TODOO

    }
  }

}