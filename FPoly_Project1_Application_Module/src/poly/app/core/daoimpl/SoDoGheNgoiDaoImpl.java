package poly.app.core.daoimpl;

import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.SoDoGheNgoi;

public class SoDoGheNgoiDaoImpl extends AbstractDao<Integer, SoDoGheNgoi>{
    public static void main(String[] args) {
        for (SoDoGheNgoi arg : new SoDoGheNgoiDaoImpl().getAll()) {
            System.out.println(arg.getDayBatDau());
        }
    }
}