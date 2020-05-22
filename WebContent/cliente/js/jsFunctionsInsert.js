document.addEventListener("DOMContentLoaded", function (event) {

    rellenarGeneros();

    document.getElementById('confirmar').addEventListener('click', insertar());

})

function rellenarGeneros() {

    $.ajax({
        url: 'http://localhost:8081/Komikilandia_IM/ApiGeneros',
        dataType: 'json',

        success: function (myJsonObject) {
            console.log(myJsonObject);
            var myHTMLcode = "";
            for (let i = 0; i < myJsonObject.length; i++) {

                myHTMLcode += '<option value="' + myJsonObject[i].id + '">' + myJsonObject[i].nombre + '</option>';

            }

            document.getElementById("genero_id").innerHTML += myHTMLcode;

        },
        error: function (xhr) {
            alert("AJAX error : " + xhr.status + " " + xhr.statusText);
        }
    });


}

function insertar() {

    var nombre = document.getElementById("nombre").value;
    var titulo = document.getElementById("titulo").value;
    var num = document.getElementById("num").value;
    var fecha_publicacion = document.getElementById("fecha_publicacion").value;
    var imagen = document.getElementById("imagen").value;
    var num_likes = document.getElementById("num_likes").value;
    var genero_id = document.getElementById("genero_id").value;

    var comic = { "nombre": nombre, "titulo": titulo, "num": num, "fecha_publicacion": fecha_publicacion, "imagen": imagen, "num_likes": num_likes, "genero_id": genero_id };

    $.ajax({
        type: 'POST',
        data: { 'comic': JSON.stringify(comic) },
        url: 'http://localhost:8081/Komikilandia_IM/ApiInsertComic',
        dataType: 'text',
        success: function (response, status, xhr) {
            if (xhr.status == 200) {
                alert("Comic insertado " + xhr.statusText);
                window.location.href = "index.html";
            }
        },
        error: function (xhr) {
            alert("An AJAX error occured: " + xhr.status + " " + xhr.statusText);
        }
    });
}