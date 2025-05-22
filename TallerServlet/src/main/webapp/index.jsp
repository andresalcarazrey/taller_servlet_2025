<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prueba TallerServlet</title>
    <script>
        // Función para listar clientes desde el servlet y mostrarlos en la tabla
        async function listarClientes() {
            const response = await fetch('clientes?peticion=listartodos');
            if (!response.ok) {
                alert('Error al obtener clientes');
                return;
            }
            const clientes = await response.json();

            //console.log(clientes);

            const tbody = document.getElementById('clientes-body');
            tbody.innerHTML = '';

            clientes.forEach(c => {
                //console.log("Renderizando cliente:", c);
                const tr = document.createElement('tr');
                Object.values(c).forEach(valor => {
                      const celda = document.createElement('td');
                      celda.textContent = valor;
                      tr.appendChild(celda);
                    });
                tbody.appendChild(tr);
            });
        }

        // Función para enviar nuevo cliente con POST (formulario)
        async function añadirCliente(event) {
            event.preventDefault();

            const form = event.target;
            const formData = new FormData(form);

            //const params = new URLSearchParams();
            //params.append('peticion', 'insertar');

            console.log("Datos form",formData);
            // Enviar con application/x-www-form-urlencoded
            const response = await fetch('clientes?peticion=insertar', {
                method: 'POST',
                body: formData
            });

            const text = await response.text();
            alert('Respuesta del servidor:\n' + text);

            form.reset();
            listarClientes();
        }

        // Cargar lista clientes al cargar la página
        window.onload = function () {
            listarClientes();
            document.getElementById('formCliente').addEventListener('submit', añadirCliente);
        };
    </script>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }
        table {
            margin-top: 15px;
            width: 100%;
        }
    </style>
</head>
<body>

<h1>Gestión Taller - Clientes</h1>

<h2>Añadir cliente</h2>
<form id="formCliente">
    <label>DNI: <input type="text" name="dni" required maxlength="9"></label><br>
    <label>Nombre: <input type="text" name="nombre" required></label><br>
    <label>Apellido1: <input type="text" name="apellido1" required></label><br>
    <label>Apellido2: <input type="text" name="apellido2" required></label><br>
    <label>Teléfono: <input type="text" name="telefono" required></label><br>
    <label>Email: <input type="email" name="email" required></label><br>
    <button type="submit">Añadir cliente</button>
</form>

<h2>Lista de clientes</h2>
<table>
    <thead>
    <tr>
        <th>DNI</th><th>Nombre</th><th>Apellido1</th><th>Apellido2</th><th>Email</th><th>Teléfono</th>
    </tr>
    </thead>
    <tbody id="clientes-body">
    <!-- Clientes cargados aquí -->
    </tbody>
</table>

</body>
</html>
