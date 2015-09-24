package scik.controlador.kardex;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import scik.modelo.Almacen;
import scik.modelo.Producto;
import scik.vista.UIKardexCabIns;

public class CKardexCabIns implements IKardexCabIns
{
    private UIKardexCabIns ventana;
    ArrayList<ArrayList<String>> productos;
    ArrayList<ArrayList<String>> almacenes;
    
    public CKardexCabIns()
    {
        productos = Producto.getActivos();
        almacenes = Almacen.getActivos();
        ventana = new UIKardexCabIns(this);
    }
    
    @Override
    public void cancelar()
    {
        CKardex kardex = new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        txtProCod.setText(productos.get(cbxProNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void verAlmacen(JTextField txtAlmCod, JComboBox cbxAlmNom)
    {
        txtAlmCod.setText(almacenes.get(cbxAlmNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void aceptar(JTextField txtProCod, JTextField txtAlmCod)
    {
        
    }
    
    @Override
    public void cargar(JComboBox cbxProNom, JComboBox cbxAlmNom)
    {
        for(int i = 0; i < productos.size(); i++)
            cbxProNom.insertItemAt(productos.get(i).get(1), i);
        for(int i = 0; i < almacenes.size(); i++)
            cbxAlmNom.insertItemAt(almacenes.get(i).get(1), i);
    }
}
