/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;
import dao.ProductosDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import productos.Productos;
/**
 *
 * @author LN710Q
 */
public class Consultas extends JFrame{
    public JLabel lblCodigo, lblNombre, lblTipo, lblCantidad, lblPrecio, lblDisponibilidad;
    public JTextField codigo, descripcion, Nombre;
    public JComboBox Tipo;
    ButtonGroup disponibilidad = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    private static final int ANCHOC = 130, ALTOC = 30;
    DefaultTableModel tm;
    public Consultas(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container=getContentPane();
        container.add(lblCodigo);
        container.add(lblNombre);
        container.add(lblTipo);
        container.add(lblCantidad);
        container.add(lblPrecio);
        container.add(lblDisponibilidad);
        container.add(codigo);
        container.add(Nombre);
        container.add(Tipo);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
        
    }

    private void agregarLabels() {
        lblCodigo=new JLabel("Codigo");
        lblNombre= new JLabel ("Nombre");
        lblTipo= new JLabel ("Tipo");
        lblCantidad= new JLabel ("Cantidad");
        lblPrecio= new JLabel ("Precio");
        lblDisponibilidad=new JLabel("Productos en tienda");
        lblCodigo.setBounds(10, 10, ANCHOC,ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC,ALTOC);
        lblTipo.setBounds(10, 100, ANCHOC,ALTOC);
        lblDisponibilidad.setBounds(10, 140, ANCHOC,ALTOC);
    }

    private void formulario() {
        codigo = new JTextField();
        Nombre = new JComboBox();
        Tipo = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        Nombre.addItem("FRAM");
        Nombre.addItem("WIX");
        Nombre.addItem("Luber Finer");
        Nombre.addItem("OSK");
        disponibilidad = new ButtonGroup();
        disponibilidad.add(si);
        disponibilidad.add(no);
        
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        Nombre.setBounds(140, 60, ANCHOC, ALTOC);
        Tipo.setBounds(140, 100, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50,  ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
        }

   public final void agregarLabels(){
        lblCodigo=new JLabel("Codigo");
        lblMarca= new JLabel ("Marca");
        lblExistencia=new JLabel("Stock en tienda");
        lblCodigo.setBounds(10, 10, ANCHOC,ALTOC);
        lblMarca.setBounds(10, 60, ANCHOC,ALTOC);
        lblStock.setBounds(10, 100, ANCHOC,ALTOC);
        lblExistencia.setBounds(10, 140, ANCHOC,ALTOC);
    }
    public void llenarTabla(){
        tm= new DefaultTableModel(){
            public Class <?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
    tm.addColumn("Codigo");
    tm.addColumn("Marca");
    tm.addColumn("Stock");
    tm.addColumn("Stock en Sucursal");
    
    FiltroDao fd=new FiltroDao();
    ArrayList<Filtro> filtros =fd.readAll();
    
    filtros.forEach((fi) -> {
        tm.addRow(new Object []{fi.getCodigo(),fi. getMarca(),fi.getStock(), fi. getExistencia()});
        });
    resultados.setModel(tm);
    }
    public void eventos (){
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd=new FiltroDao();
               Filtro f=new Filtro (codigo.getText)(),marca.getSelectedItem().toString(),
                    Integer.parseInt(stock.getText()), true);
               if (no.isSelected()){
                   f.setExistencia(false);
               } 
               if (fd.create(f)){
               JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
               limpiarCampos();
               llenarTabla();
               }else {
                   JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
               }
            }   
    });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                ProductosDao fd=new ProductosDao();
                Productos f= fd.read(codigo.getText());
                if (f==null){
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");
                    
                }else{
                    codigo.setText(f.getCodigo());
                    Nombre.setSelectedItem(f.getNombre());
                    Tipo.setText(Integer.toString(f.getTipo()));
                    if(f.getDisponibilidad())}{
                si.setSelected(true);
                }else{
                        no.setSelected(true);
                        
                        };
                
            }
        });
        
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                limpiarCampos();
                
            }
            });
        }
    public void limpiarCampos(){
        codigo.setText(" ");
        Nombre.setSelectedItem("FRAM");
        Tipo.setText(" ");
        
    }
    public static void main (String[] args ){
        java.awt.EventQueue.invokeLater(new Runnable(){
        @Override 
        public void run(){
            new Consultas().setVisible(true);
        }
    });
    }
    
    
}
