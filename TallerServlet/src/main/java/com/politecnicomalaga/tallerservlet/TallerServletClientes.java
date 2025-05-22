package com.politecnicomalaga.tallerservlet;

import com.google.gson.Gson;
import com.politecnicomalaga.tallerservlet.data.BBDDController;
import com.politecnicomalaga.tallerservlet.data.RepositorioClientes;
import com.politecnicomalaga.tallerservlet.model.Cliente;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jetbrains.annotations.NotNull;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

//Versión Plus de nuestro Servlet original. Soporta fetch de objetos JSON directamente a partir de una petición POST
// Todavía no cumple convención REST (esta a "nuestra manera")

public class TallerServletClientes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        trabajoPrincipal(request, response, false);
    }

    @Override
    protected void doPost(HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        trabajoPrincipal(request, response, true);
    }

    private void trabajoPrincipal(HttpServletRequest request, @NotNull HttpServletResponse response, boolean isPost)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tipoConsulta = request.getParameter("peticion");

        if (tipoConsulta == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"Falta el parámetro 'peticion'\"}");
            return;
        }

        switch (tipoConsulta) {
            case "insertar":
                if (isPost && "application/json".equals(request.getContentType())) {
                    insertarClienteJSON(request, out, response);
                } else {
                    insertarClienteForm(request, out, response);
                }
                break;

            case "listartodos":
                listarClientes(out);
                break;

            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\":\"Parámetro 'peticion' desconocido\"}");
                break;
        }
    }

    private void insertarClienteForm(HttpServletRequest request, PrintWriter out, HttpServletResponse response) {
        Map<String, String> data;

        data = leerParametrosPeticion(request,out, response);


        Cliente c = new Gson().fromJson(new Gson().toJson(data), Cliente.class);

        //Esto se hace sólo a efectos académicos, para que se vea "qué ha pasado". En producción se quita
        out.println("{\"resultado\":\"" + RepositorioClientes.insertarCliente(c) + "\",");
        out.println("\"sql\":\"" + BBDDController.lastSQL + "\"}");
    }


    //Función para procesar los datos del formulario y convertir cada parámetro en una entrada de nuestro mapa de transferencia de
    //datos
    private Map<String,String> leerParametrosPeticion(HttpServletRequest request, PrintWriter out, HttpServletResponse response) {
        Map<String,String> data = new HashMap<>();
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        data.put(item.getFieldName(), item.getString());
                    }
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Error en el procesado de los parámetros de la Inserción"+ e.getMessage());
        }
        return data;
    }

    private void insertarClienteJSON(HttpServletRequest request, PrintWriter out, HttpServletResponse response) {
        try (BufferedReader reader = request.getReader()) {
            Gson gson = new Gson();
            Cliente c = gson.fromJson(reader, Cliente.class);

            String resultado = BBDDController.insertarMapeable(c, "Clientes");

            out.println("{\"resultado\":\"" + resultado + "\",");
            out.println("\"sql\":\"" + BBDDController.lastSQL + "\"}");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\":\"Error al procesar JSON: " + e.getMessage() + "\"}");
        }
    }

    private void listarClientes(PrintWriter out) {
        List<Cliente> listaClientes = RepositorioClientes.selectAllClientes();
        Gson gson = new Gson();
        String json = gson.toJson(listaClientes);
        out.println(json);
    }
}
