package com.krakedev.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.Cliente;
import com.krakedev.utils.ConexionBDD;

import coom.krakedev.excepciones.KrakedevException;

public class ClientesBDD {
	public void insertar(Cliente cliente) throws KrakedevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement("INSERT INTO Clientes(cedula,apellido) values (?,?)");
			ps.setString(1, cliente.getCedula());
			ps.setString(2, cliente.getApellido());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new KrakedevException("Error al inserat cliente" + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void actualizar(Cliente cliente) throws KrakedevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement("UPDATE clientes SET apellido = ? WHERE cedula = ?");
			ps.setString(1, cliente.getApellido());
			ps.setString(2, cliente.getCedula());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new KrakedevException("Error al actualizar cliente" + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<Cliente> recuperarTodos() throws KrakedevException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			con = ConexionBDD.obtenerConexion();
			try {
				ps = con.prepareStatement("SELECT * FROM Clientes");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					String cedula = rs.getString("cedula");
					String apellido = rs.getString("apellido");
					Cliente cli = new Cliente(cedula,apellido);
					clientes.add(cli);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al listar todos los clientes" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return clientes;
	}
	
	public Cliente buscarPorPK(String cedulaBusqueda) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			con = ConexionBDD.obtenerConexion();
			try {
				ps = con.prepareStatement("SELECT * FROM Clientes WHERE cedula = ?");
				ps.setString(1, cedulaBusqueda);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					String cedula = rs.getString("cedula");
					String apellido = rs.getString("apellido");
					 cliente = new Cliente(cedula,apellido);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al listar todos los clientes" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return cliente;
	}
}
