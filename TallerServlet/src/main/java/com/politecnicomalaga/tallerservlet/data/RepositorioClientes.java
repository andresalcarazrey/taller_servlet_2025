package com.politecnicomalaga.tallerservlet.data;

import com.politecnicomalaga.tallerservlet.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioClientes {

    private static final String TABLA_CLIENTES = "Clientes";


    public static String insertarCliente(Cliente c) {
        return BBDDController.insertarMapeable(c, TABLA_CLIENTES);
    }

    public static List<Cliente> selectAllClientes() {
        List<Cliente> listClientes = new ArrayList<>();
        //Data viene en formato CSV con ; al final
        String data = BBDDController.selectAll("Clientes", "dni,nombre,apellido1,apellido2,email,telefono",6);
        String clientes[] = data.split("\n");
        for(String clienteString: clientes) {
            //Cada línea, un cliente
            String campos[] = clienteString.split(";");
            if (campos==null || campos.length<6) return listClientes;
            Cliente c = new Cliente(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5]);
            listClientes.add(c);
        }
        return listClientes;

    }

}
