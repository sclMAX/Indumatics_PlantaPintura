package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColorDP {
    
    private final Connection con = AccessConector.getInstance();
    
    public Color getOne(int id){
        Color res = null;
        try {
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Colores WHERE Colores.id = " + Integer.toString(id));
            if(rs.next()){
                res = new Color(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getDouble(4)
                ,rs.getString(5),rs.getDate(6),rs.getBoolean(7),rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
}
