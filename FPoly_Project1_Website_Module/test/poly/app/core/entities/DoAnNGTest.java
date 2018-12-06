/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.entities;

import javax.persistence.GenerationType;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import poly.app.core.daoimpl.DoAnDaoImpl;
import poly.app.core.daoimpl.LoaiDoAnDaoImpl;

/**
 *
 * @author vothanhtai
 */
public class DoAnNGTest {
    
    public DoAnNGTest() {
    }

    @Test
    public void insert() throws Exception {
        LoaiDoAn loaiDoAn = new LoaiDoAnDaoImpl().getById("DA");
        DoAn doAn = new DoAn("", loaiDoAn, "Haha", true);
        new DoAnDaoImpl().insert(doAn);
        System.out.println(doAn.getId());
    }
    
}
