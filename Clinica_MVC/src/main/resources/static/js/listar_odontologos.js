const tableBody = document.querySelector("#odontologosTable tbody");
const apiURL = "http://localhost:8080";
function fetchOdontologos() {
// listando los odontologos

fetch(`${apiURL}/odontologo`)
    .then((response) => response.json())
    .then((data) => {
    // Limpiar el contenido actual de la tabla
    tableBody.innerHTML = "";

    // Insertar los datos en la tabla
    data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.matricula}</td>
                <td>
                <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.matricula}')">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
            `;

        tableBody.appendChild(row);
    });
    })
    .catch((error) => {
    console.error("Error fetching data:", error);
    });

// modificar un odontologo

// eliminar un odontologo
}

var editModal = new bootstrap.Modal(document.getElementById('editModal'));
function editOdontologo(id, nombre,apellido,matricula) {

    //Creamos un modal e ingresamos los datos registrados para la modificacion.
    document.getElementById("editId").value = id;
    document.getElementById("editnombre").value = nombre;
    document.getElementById("editapellido").value = apellido;
    document.getElementById("editmatricula").value = matricula;

    // Mostrar el modal
    editModal.show();

}

const editform=document.getElementById("editForm")


editform.addEventListener("submit",function (e) {
    e.preventDefault()
    
    const id = document.getElementById("editId").value
    const nombre = document.getElementById("editnombre").value
    const apellido = document.getElementById("editapellido").value
    const matricula = document.getElementById("editmatricula").value

    const odontologoNuevo=({id,nombre,apellido,matricula})

    // llamada al endpoint

    fetch(`${apiURL}/odontologo`, {
        method: "PUT",
        headers: {
        "Content-Type": "application/json",
        },
        body: JSON.stringify(odontologoNuevo),
    }).then((response)=> response.json())
    .then(data =>{
        console.log(data)
        fetchOdontologos();
        editModal.hide()
    })

})



function deleteOdontologo(id) {

    const settings={
        method:"DELETE"
    }
    Swal.fire({
        title: "Eliminar Odontologo",
        text: "¿Estás seguro de que deseas eliminar al odontologo?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "¡Sí, Confirmo!"
    }).then((result) => {
        if (result.isConfirmed) {
                fetch(`${apiURL}/odontologo/${id}`,settings)
                .then(response => {
                    console.log("Borrando Odontologo...");
                //vuelvo a consultar las tareas actualizadas y pintarlas nuevamente en pantalla
                    fetchOdontologos()
                    Swal.fire(
                        "¡Odontologo Eliminado!"
                    )
                })

            }
        }
    );
}



fetchOdontologos();
