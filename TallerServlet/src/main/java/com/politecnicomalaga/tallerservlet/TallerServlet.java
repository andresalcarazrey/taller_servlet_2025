package com.politecnicomalaga.tallerservlet;

import com.google.gson.Gson;
import com.politecnicomalaga.tallerservlet.data.BBDDController;
import com.politecnicomalaga.tallerservlet.data.RepositorioClientes;
import com.politecnicomalaga.tallerservlet.model.Cliente;
import org.jetbrains.annotations.NotNull;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TallerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        trabajoPrincipal(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        trabajoPrincipal(req, resp);
    }


    private void trabajoPrincipal(HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        {
            response.setContentType("text/json");
            PrintWriter out = response.getWriter();
            String tipoConsulta = request.getParameter("peticion");


            Map<String,String> data = new HashMap<>();
            data.put("nombre",request.getParameter("nombre"));
            data.put("apellido1",request.getParameter("apellido1"));
            data.put("apellido2",request.getParameter("apellido2"));
            data.put("dni",request.getParameter("dni"));
            data.put("email",request.getParameter("email"));
            data.put("telefono",request.getParameter("telefono"));


            switch (tipoConsulta) {
                case "insertar": insertarCliente(data,out);
                break;
                case "listartodos": listarClientes(out);
                break;
            }


        }
    }

    private void insertarCliente(Map<String,String> data, PrintWriter out) {
        Cliente c;
        Gson gson = new Gson();
        String json = gson.toJson(data);
        c = gson.fromJson(json,Cliente.class);
        out.println(BBDDController.insertarMapeable(c,"Clientes"));
        out.println(BBDDController.lastSQL);
    }

    private void listarClientes(PrintWriter out) {
        List<Cliente> listaClientes = RepositorioClientes.selectAllClientes();

        Gson gson = new Gson();
        String json = gson.toJson(listaClientes);
        out.println(json);

        //out.println(BBDDController.lastSQL);
    }
}