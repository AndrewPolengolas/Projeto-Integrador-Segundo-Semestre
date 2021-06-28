/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.VendedorDAO;
import Models.Vendedor;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class VendedorController {
    /**
     * Metodo que chama a classe {@link DAO.VendedorDAO#ConsultarVendedores()}
     * @return Retorna um ArrayList do tipo {@link Models.Vendedor}
     */
    public static ArrayList<Vendedor> ConsultarVendedores(){
        ArrayList<Vendedor> listaVendedores = VendedorDAO.ConsultarVendedores();
        return listaVendedores;
    }
}
