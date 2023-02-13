package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Inventario;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JFormattedTextField;

public class FramePrestamo extends JFrame {

	private JPanel contentPane;
	private JTextField textCodBarra;
	private JTextField textBusquedaPrestamo;
	private JTable table;
	Inventario m=new Inventario();
	private JTable table_1;
	private JTextField textfecha;
	private JButton button;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrestamo frame = new FramePrestamo();
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
	public FramePrestamo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 624);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		panel.setBounds(35, 70, 366, 390);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Apellidos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(39, 43, 77, 20);
		panel.add(lblNewLabel);
		
		textCodBarra = new JTextField();
		textCodBarra.setBounds(170, 103, 166, 62);
		panel.add(textCodBarra);
		textCodBarra.setColumns(10);
		
		
		
		JComboBox comboBoxApellidos = new JComboBox();
		comboBoxApellidos.setBounds(170, 37, 166, 36);
		panel.add(comboBoxApellidos);
		
		
		
		ResultSet data=m.consulta("select * from usuario");
		try {
		while(data.next()) {
			comboBoxApellidos.addItem(data.getString(3));
		}
		}catch(SQLException e1) {
			
		}
		
		
		
		JButton btnLeerCodBarra = new JButton("Cod Barra");
		btnLeerCodBarra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLeerCodBarra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLeerCodBarra.setBounds(22, 120, 118, 27);
		panel.add(btnLeerCodBarra);
		
		textfecha = new JTextField();
		textfecha.setBounds(170, 188, 160, 42);
		panel.add(textfecha);
		textfecha.setColumns(10);
		
		String txtDate= new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
		textfecha.setText(txtDate);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(39, 202, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hora");
		lblNewLabel_2.setBounds(39, 268, 46, 14);
		panel.add(lblNewLabel_2);
		
		JFormattedTextField hora_inicio = new JFormattedTextField();
		hora_inicio.setBounds(180, 265, 145, 36);
		panel.add(hora_inicio);
		
		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH:mm:ss");
		hora_inicio.setText(dtf3.format(LocalDateTime.now()));
		
		JButton btnRegistrar = new JButton("Registrar Prestamo");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod_barra=Integer.parseInt(textCodBarra.getText());
				String fecha=textfecha.getText();  
				String  hora_i=hora_inicio.getText();
	
				String ape=String.valueOf(comboBoxApellidos.getSelectedItem());
				ResultSet data=m.consulta("select * from usuario where apellido='"+ ape +"'");
				ResultSet busqueda_cod=m.consulta("select * from herramienta where cod_herramienta='"+ cod_barra +"'");
				int cod_usuario = 0;
				int cod2=0;
				try {
					while(busqueda_cod.next()) {
						 cod2= busqueda_cod.getInt(1);
					}
					if(cod2 == cod_barra) {
						JOptionPane.showMessageDialog(null," Codigo Barra Valido");
					}else {
						JOptionPane.showMessageDialog(null, "Codigo no pertenece a ninguna herramienta");
					}	
				}catch(SQLException e1) {
				
				}
				
				try {
					while(data.next()) {
						 cod_usuario=data.getInt(1);
					}
					boolean w=m.registrar_prestamo(cod_usuario,cod_barra,fecha,hora_i,"");
					if(w==true) {
						JOptionPane.showMessageDialog(null," Prestamo  Correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error al reealizar prestamo");
					}	
					
				}catch(SQLException e1) {
					}	
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistrar.setBounds(104, 337, 160, 42);
		panel.add(btnRegistrar);
		
		
		JButton btnMenuPrincipal = new JButton("Menu");
		btnMenuPrincipal.setBounds(39, 11, 89, 37);
		contentPane.add(btnMenuPrincipal);
		
		JButton btnBusquedaPrestamo = new JButton("Buscar");
		btnBusquedaPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBusquedaPrestamo.setBounds(720, 29, 89, 33);
		contentPane.add(btnBusquedaPrestamo);
		
		textBusquedaPrestamo = new JTextField();
		textBusquedaPrestamo.setBounds(819, 30, 138, 30);
		contentPane.add(textBusquedaPrestamo);
		textBusquedaPrestamo.setColumns(10);
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHistorial.setBounds(428, 423, 166, 37);
		contentPane.add(btnHistorial);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(419, 68, 538, 344);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		
		JButton boton = new JButton("Finalizar");
		boton.setSize(100,45);
		boton.setVisible(true);
		ActionListener listener = new ActionListener(){ 
		   public void actionPerformed(ActionEvent e) { 
		    
		   }         
		};
		boton.addActionListener(listener);
		
		
		table_1.setBackground(SystemColor.menu);
		
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		model=new DefaultTableModel();
		table_1.setModel(model);
		model.addColumn("COD PRESTAMO");
		model.addColumn("USUARIO");
		model.addColumn("HERRAMIENTA");
		model.addColumn("FECHA");
		model.addColumn("HORA INICIO");
		model.addColumn("FINALIZAR");
		
		ResultSet dat3=m.consulta("select * from prestamo");
		try {
		while(dat3.next()) {
			Object[] fila=new Object[5];
			fila[0]=dat3.getInt(1);
			
			fila[1]=dat3.getInt(2);
			fila[2]=dat3.getInt(3);
			fila[3]=dat3.getString(4);
			fila[4]=dat3.getString(5);
			
			model.addRow(fila);
		}
		}catch(SQLException e) {
	    }
		
		
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.LIGHT_GRAY, null, null));
		scrollPane.setViewportView(table_1);
		
		JButton btnListaPrestamos = new JButton("Lista de Prestamos");
		btnListaPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListaPrestamos.setBounds(428, 34, 193, 23);
		contentPane.add(btnListaPrestamos);
	}
}
