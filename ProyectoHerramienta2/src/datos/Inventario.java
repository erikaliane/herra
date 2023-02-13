package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Inventario {
	public Connection con;
	public PreparedStatement sen;
	public Statement sen2;
	public ResultSet datos;
	public String driver="com.mysql.jdbc.Driver";
	public String cadena="jdbc:mysql://localhost/p_herramienta";
	public String usuario="root";
	public String clave="";
	public Connection conectarBD() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(cadena,usuario,clave);
		}catch(ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Error en el driver");
		}catch(SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error en la conexion");
		}return con;
	}
	
	public  boolean  registrar_herra(int cod_barra , String nom, String marca,String serie ) {
		try {
			String codigo="insert into herramienta (cod_herramienta,nombre,marca,serie) values(?,?,?,?)";
			sen=conectarBD().prepareStatement(codigo);
			sen.setInt(1,cod_barra);
			sen.setString(2, nom);
			sen.setString(3, marca);
			sen.setString(4, serie);
			sen.executeUpdate();		
		}catch(SQLException e3) {
			
		}
		return true;
		
	}
	
	public  boolean  registrar_usuario(int cod, String nom, String apell ) {
		try {
			String codigo="insert into usuario values(?,?,?)";
			sen=conectarBD().prepareStatement(codigo);
			sen.setInt(1, cod);
			sen.setString(2, nom);
			sen.setString(3, apell);
			sen.executeUpdate();		
		}catch(SQLException e3) {
			
		}
		return true;
	}
	
	public  boolean  registrar_prestamo(int cod_usuario, int cod_herramienta, String fecha, String hora_inicio , String hora_fin) {
		try {
			String codigo="insert into prestamo (cod_usuario,cod_herramienta,fecha,hora_inicio,hora_fin) values(?,?,?,?,?)";
			sen=conectarBD().prepareStatement(codigo);
			sen.setInt(1, cod_usuario);
			sen.setInt(2, cod_herramienta);
			sen.setString(3, fecha);
			sen.setString(4, hora_inicio);
			sen.setString(5, hora_fin);
			sen.executeUpdate();		
		}catch(SQLException e3) {
			JOptionPane.showMessageDialog(null, "Error en el prestamo");
		}
		return true;
	}
	
	public ResultSet consulta(String codigosql) {
		try {
			String codigo=codigosql;
			sen2=conectarBD().createStatement();
			datos=sen2.executeQuery(codigo);
		}catch(SQLException e3) {
			JOptionPane.showMessageDialog(null, "Error en la consulta ");
			
		}
		return datos;
	}
	public void reporte1(String reporte ,String consulta) {
		try {
			con=conectarBD();
			String sql=consulta;
			String ruta="src/reportes/"+reporte+".jrxml";
			JasperDesign jd=JRXmlLoader.load(ruta);
			JRDesignQuery jrdq=new JRDesignQuery();
			jrdq.setText(sql);
			jd.setQuery(jrdq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
			JasperViewer.viewReport(jp,false);
        }catch(Exception e3) {
        	JOptionPane.showMessageDialog(null,"Error al generar reporte");
        }
	}
	public void reporte2(String reporte ,String consulta,int cod) {
		try {
			con=conectarBD();
			String sql=consulta;
			String ruta="src/reportes/"+reporte+".jrxml";
			JasperDesign jd=JRXmlLoader.load(ruta);
			Map<String,Object> map=new HashMap<String,Object>();
			map.clear();
			map.put("cod",cod);
			JRDesignQuery jrdq=new JRDesignQuery();
			jrdq.setText(sql);
			jd.setQuery(jrdq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr,map,con);
			JasperViewer.viewReport(jp,false);
        }catch(Exception e3) {
        	JOptionPane.showMessageDialog(null,"Error al generar reporte");
        }
	}
} 