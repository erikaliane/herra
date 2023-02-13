package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import datos.Inventario;
import java.awt.ScrollPane;
import java.awt.List;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class FrameUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField textCodUsuario;
	private JTextField textNomUsuario;
	private JTextField textApeUsuario;
	private JTable table;
	Inventario m=new Inventario();
	private JTextField txtBusApellido;
	private JTable table_1;
	private JTable table_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUsuarios frame = new FrameUsuarios();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameUsuarios() {
		setForeground(SystemColor.textHighlight);
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1281, 777);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea textListaUsuario = new JTextArea();
		textListaUsuario.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		textListaUsuario.setBounds(549, 87, 503, 361);
		contentPane.add(textListaUsuario);
		
		
		JButton btnListaUsuario = new JButton("Lista de Usuarios");
		btnListaUsuario.setBackground(new Color(100, 149, 237));
		btnListaUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnListaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textListaUsuario.setText("Codigo \tNombre\tApellido");
			
				ResultSet data=m.consulta("select * from usuario");
				try {
				while(data.next()) {
					textListaUsuario.append("\n"+"\n"+data.getInt(1)+"\t"+data.getString(2)+
							"\t"+data.getString(3));
				}
				}catch(SQLException e1) {
				}
			}
		});
		btnListaUsuario.setBounds(545, 42, 165, 37);
		contentPane.add(btnListaUsuario);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(180, 180, 180), new Color(180, 180, 180), SystemColor.activeCaptionBorder, SystemColor.activeCaptionBorder));
		panel.setBounds(23, 87, 439, 382);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(104, 69, 215, 30);
		panel.add(lblNewLabel);
		
		JLabel lblnombre = new JLabel("NOMBRE");
		lblnombre.setBounds(85, 185, 75, 17);
		panel.add(lblnombre);
		lblnombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textCodUsuario = new JTextField();
		textCodUsuario.setBackground(SystemColor.control);
		textCodUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCodUsuario.setBounds(221, 110, 146, 37);
		panel.add(textCodUsuario);
		textCodUsuario.setColumns(10);
		
		JLabel lblapellido = new JLabel("APELLIDOS");
		lblapellido.setBounds(85, 240, 106, 14);
		panel.add(lblapellido);
		lblapellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textApeUsuario = new JTextField();
		textApeUsuario.setBackground(SystemColor.control);
		textApeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textApeUsuario.setBounds(221, 240, 146, 37);
		panel.add(textApeUsuario);
		textApeUsuario.setColumns(10);
		
		JLabel lblcodigo = new JLabel("CODIGO");
		lblcodigo.setBounds(85, 121, 75, 14);
		panel.add(lblcodigo);
		lblcodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textNomUsuario = new JTextField();
		textNomUsuario.setBackground(SystemColor.control);
		textNomUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNomUsuario.setBounds(221, 175, 146, 37);
		panel.add(textNomUsuario);
		textNomUsuario.setColumns(10);
		
		JButton btnRegistroUsuario = new JButton("Registrar");
		btnRegistroUsuario.setForeground(new Color(255, 255, 255));
		btnRegistroUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod=Integer.parseInt(textCodUsuario.getText());
				String nom=textNomUsuario.getText();
				String ape=textApeUsuario.getText();
				boolean w=m.registrar_usuario(cod,nom,ape);
				if(w==true) {
					
					JOptionPane.showMessageDialog(null," Usuario Registrado Correctamente");
					textCodUsuario.setText("");
					textNomUsuario.setText("");
					textApeUsuario.setText("");
					
				}else {
					JOptionPane.showMessageDialog(null, "Error al registrar Usuario");
				}	
				
				
			}
		});
		
		
		btnRegistroUsuario.setBackground(new Color(102, 153, 255));
		btnRegistroUsuario.setBounds(144, 301, 142, 37);
		panel.add(btnRegistroUsuario);
		btnRegistroUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnImportarUsuarios = new JButton("Importar Usuarios");
		btnImportarUsuarios.setForeground(Color.WHITE);
		btnImportarUsuarios.setBackground(new Color(102, 153, 255));
		btnImportarUsuarios.setBounds(23, 11, 173, 37);
		panel.add(btnImportarUsuarios);
		btnImportarUsuarios.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtBusApellido = new JTextField();
		txtBusApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBusApellido.setBounds(879, 45, 176, 31);
		contentPane.add(txtBusApellido);
		txtBusApellido.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textListaUsuario.setText("Codigo \tNombre\tApellido");
				String ape =txtBusApellido.getText();
				ResultSet data=m.consulta("select * from usuario where apellido='"+ ape +"'");
				try {
				while(data.next()) {
					textListaUsuario.append("\n"+data.getInt(1)+"\t"+data.getString(2)+
							"\t"+data.getString(3));
				}
				}catch(SQLException e1) {
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(751, 45, 118, 31);
		contentPane.add(btnNewButton);
		
		
	}
}
