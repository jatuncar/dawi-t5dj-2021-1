package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmManteUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtFecha;
	private JTextField txtTipo;
	private JTextField txtEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteUsuario frame = new FrmManteUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mantenimiento de Usuarios");
		lblNewLabel.setBounds(21, 23, 230, 27);
		contentPane.add(lblNewLabel);

		JLabel lblCdigo = new JLabel("Código :");
		lblCdigo.setBounds(21, 71, 71, 27);
		contentPane.add(lblCdigo);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(21, 109, 71, 27);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setBounds(21, 147, 71, 27);
		contentPane.add(lblApellido);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(21, 185, 71, 27);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(21, 223, 71, 27);
		contentPane.add(lblClave);

		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setBounds(21, 263, 71, 27);
		contentPane.add(lblFecha);

		JLabel lblTipo = new JLabel("Tipo :");
		lblTipo.setBounds(21, 301, 71, 27);
		contentPane.add(lblTipo);

		JLabel lblFecha_1_1 = new JLabel("Estado :");
		lblFecha_1_1.setBounds(21, 339, 71, 27);
		contentPane.add(lblFecha_1_1);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(83, 74, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(83, 112, 133, 20);
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(83, 147, 133, 20);
		contentPane.add(txtApellido);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(83, 188, 86, 20);
		contentPane.add(txtUsuario);

		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(83, 226, 86, 20);
		contentPane.add(txtClave);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(83, 266, 86, 20);
		contentPane.add(txtFecha);

		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(83, 304, 86, 20);
		contentPane.add(txtTipo);

		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(83, 342, 86, 20);
		contentPane.add(txtEstado);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setBounds(322, 71, 89, 23);
		contentPane.add(btnRegistrar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(322, 105, 89, 23);
		contentPane.add(btnActualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(322, 139, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnConsultar.setBounds(322, 173, 89, 23);
		contentPane.add(btnConsultar);
	}

	void consultar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		// consulta
		Usuario u = em.find(Usuario.class, Integer.parseInt(txtCodigo.getText()));
		if (u != null) {
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
		} else {
			JOptionPane.showMessageDialog(this, "Usuario con código NO existe");
		}
		
		em.close();
		fabrica.close();
	}

	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
	EntityManager em = fabrica.createEntityManager();
	
	void registrar() {
		Usuario u = new Usuario();
		// u.setCodigo(6); EL ID/Código es AUTO_INCREMENT
		u.setNombre(txtNombre.getText());
		u.setApellido(txtApellido.getText());
		u.setUsuario(txtUsuario.getText());
		u.setClave(txtClave.getText());
		u.setFnacim(txtFecha.getText());
		u.setTipo(Integer.parseInt(txtTipo.getText())); // CONSTRAINT DEFAULT
		u.setEstado(Integer.parseInt(txtEstado.getText())); // CONSTRAINT DEFAULT

		// 3. empezar mi transacción
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();

		// -- cerrar
		em.close();
		fabrica.close();
	}
}
