package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author umg
 */
public class EmpleadosController implements ActionListener, MouseListener{
 frmPrincipal VistaPrincipal;
 frmEmpleados VistaEmpleados;
  frmConsulta VistaConsulta;
 
 EmpleadosModel ModeloEmpleado;
 
    

    public EmpleadosController( frmPrincipal VistaPrincipal, frmEmpleados VistaEmpleados,frmConsulta VistaConsulta,EmpleadosModel ModeloEmpleado) {
        this.VistaPrincipal = VistaPrincipal;
        this.VistaEmpleados = VistaEmpleados;
        this.VistaConsulta = VistaConsulta;
        this.ModeloEmpleado = ModeloEmpleado;
        
        /*LEVANTAR LAS VISTAS*/
      
      
      /*PONER A LA ESCUCHA LOS BOTONES*/
      this.VistaEmpleados.btn_Agregar.addActionListener(this);
      this.VistaEmpleados.btn_Editar.addActionListener(this);
      this.VistaEmpleados.btnEliminar.addActionListener(this);
      this.VistaPrincipal.opIngresar.addActionListener(this);
      this.VistaPrincipal.opcConsulta.addActionListener(this);
      this.VistaConsulta.btnEjecutar.addActionListener(this);
        
      /*REALIZAR LA CONEXION*/
            
            /*Limpiar la tabla Vista Empleados
                DefaultTableModel TablaModelo = new DefaultTableModel();
                TablaModelo.setRowCount(0);
                TablaModelo.setColumnCount(0);
               this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
            */        
            /*prepara el modelo de la tabla
                    TablaModelo.addColumn("ID");
                    TablaModelo.addColumn("APELLIDOS");
                    TablaModelo.addColumn("NOMBRES");
                    TablaModelo.addColumn("TELEFONO");
                    
      /* LEVANTAR EL MODELO Y LOGRAR RECORRER EL RESULTSET*/
         // Captar el resultado que viene del Modelo desde el método LISTARDATOS

                DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
                this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
                
                   
            //PASAR EL MODELO CREADO A LA TABLA DE LA VISTA EMPLEADOS        
                    this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
                    
            //PONER A LA ESCUCHA LA TABLA DE EMPLEADOS
            this.VistaEmpleados.jtbEmpleados.addMouseListener(this);
        
        /*LEVANTAR LA VISTA EMPLEADOR*/
      this.VistaPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
      this.VistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.VistaPrincipal.opIngresar)
        {
            this.VistaEmpleados.setLocationRelativeTo(null);
            this.VistaEmpleados.setVisible(true);
        }
        if(e.getSource() == this.VistaPrincipal.opcConsulta)
        {
            this.VistaConsulta.setLocationRelativeTo(null);
            this.VistaConsulta.setVisible(true);
        }
        if(e.getSource() == this.VistaConsulta.btnEjecutar)
        {
            DefaultTableModel TablaModelo2 = this.ModeloEmpleado.ListarDatosConsulta(this.VistaConsulta.txtConsulta.getText());
                this.VistaConsulta.tablaConsulta.setModel(TablaModelo2);
        }
        
        if(e.getSource() == this.VistaEmpleados.btn_Editar)
        {
            this.ModeloEmpleado.Actualizar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()),
                    this.VistaEmpleados.txtApellidos.getText(),
                    this.VistaEmpleados.txtNombre.getText(), this.VistaEmpleados.txtTelefono.getText());

            DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
            this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        }
        if (e.getSource() == this.VistaEmpleados.btn_Agregar) {
            this.ModeloEmpleado.Guardar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()),
                    this.VistaEmpleados.txtApellidos.getText(),
                    this.VistaEmpleados.txtNombre.getText(), this.VistaEmpleados.txtTelefono.getText());

            DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
            this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        }
        if (e.getSource() == this.VistaEmpleados.btnEliminar) {
            this.ModeloEmpleado.Eliminar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()),
                    this.VistaEmpleados.txtApellidos.getText(),
                    this.VistaEmpleados.txtNombre.getText(), this.VistaEmpleados.txtTelefono.getText());
            DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
            this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);

            this.VistaEmpleados.txtCodigo.setText("");
            this.VistaEmpleados.txtApellidos.setText("");
            this.VistaEmpleados.txtNombre.setText("");
            this.VistaEmpleados.txtTelefono.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int fila;
        if(arg0.getSource()==this.VistaEmpleados.jtbEmpleados)
        {
            fila = this.VistaEmpleados.jtbEmpleados.getSelectedRow();
            this.VistaEmpleados.txtApellidos.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 0).toString());
            
            fila = this.VistaEmpleados.jtbEmpleados.getSelectedRow();
            this.VistaEmpleados.txtNombre.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 1).toString());
            
            fila = this.VistaEmpleados.jtbEmpleados.getSelectedRow();
            this.VistaEmpleados.txtTelefono.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 2).toString());
            
            fila = this.VistaEmpleados.jtbEmpleados.getSelectedRow();
            this.VistaEmpleados.txtCodigo.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 3).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        
    }

}
