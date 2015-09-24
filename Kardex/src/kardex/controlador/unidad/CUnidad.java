package kardex.controlador.unidad;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import kardex.controlador.CKardex;
import kardex.controlador.documento.CDocumentoMod;
import kardex.modelo.Documento;
import kardex.modelo.Unidad;
import kardex.vista.UIUnidad;

public class CUnidad implements IUnidad
{
    private UIUnidad ventana;
    private ArrayList<Unidad> unidades;
    
    public CUnidad()
    {
        unidades = Unidad.getLista();
        ventana = new UIUnidad(this);
    }
    
    @Override
    public void cargar(JTable tblRegistros)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < unidades.size(); i++)
        {
            model.addRow(new Object[]{  unidades.get(i).getUniCod(),
                                        unidades.get(i).getUniDes(),
                                        unidades.get(i).getUniEstReg().equals("1")?"A":(unidades.get(i).getUniEstReg().equals("2")?"I":"*")});
        }
    }
    
    @Override
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            chActivar.setEnabled(true);
            Unidad u = unidades.get(i);
            if(!u.getUniEstReg().equals("3"))
            {
                if(u.getUniEstReg().equals("1"))
                    chActivar.setSelected(true);
                else
                    chActivar.setSelected(false);
            }
            else
                chActivar.setEnabled(false);
        }
        else
            chActivar.setEnabled(false);
    }
    
    @Override
    public void menu()
    {
        CKardex menu = new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void insertar()
    {
        CUnidadIns insertar = new CUnidadIns();
        ventana.dispose();
    }
    
    @Override
    public void modificar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Unidad u = unidades.get(i);
            CUnidadMod modificar;
            if(u.getUniEstReg().equals("1"))
            {
                modificar = new CUnidadMod(u.getUniCod());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void eliminar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Unidad u = unidades.get(i);
            if(!u.getUniEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    u.eliminar();
                    model.setValueAt("*", i, 2);
                    chActivar.setEnabled(false);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void activar_desactivar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        Unidad u = unidades.get(i);
        if(chActivar.isSelected())
        {
            u.activar();
            model.setValueAt("A", i, 2);
        }
        else
        {
            u.desactivar();
            model.setValueAt("I", i, 2);
        }
    }
}