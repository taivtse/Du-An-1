
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import poly.app.core.daoimpl.DoAnChiTietDaoImpl;
import poly.app.core.daoimpl.LoaiPhimDaoImpl;
import poly.app.core.daoimpl.PhimDaoImpl;
import poly.app.core.daoimpl.PhongChieuDaoImpl;
import poly.app.core.daoimpl.SuatChieuDaoImpl;
import poly.app.core.daoimpl.VeBanDaoImpl;
import poly.app.core.entities.DoAnChiTiet;
import poly.app.core.entities.LoaiPhim;
import poly.app.core.entities.Phim;
import poly.app.core.entities.SuatChieu;
import poly.app.core.entities.VeBan;
import poly.app.core.utils.JsonFactoryUtil;

public class TestJackson {

    public static void main(String[] args) throws IOException {
        DoAnChiTiet doAnChiTiet = new DoAnChiTietDaoImpl().getById(1);
        System.out.println(JsonFactoryUtil.toJson(doAnChiTiet, true));
//        String json = "[{\"id\":27,\"ten\":\"Phim hành động\",\"phims\":null},{\"id\":28,\"ten\":\"Phim hài hước\",\"phims\":null},{\"id\":29,\"ten\":\"Phim tâm lý\",\"phims\":null},{\"id\":30,\"ten\":\"Phim cổ trang\",\"phims\":null},{\"id\":31,\"ten\":\"Phim kinh dị\",\"phims\":null},{\"id\":32,\"ten\":\"Phim hoạt hình\",\"phims\":null},{\"id\":33,\"ten\":\"Phim chiến tranh\",\"phims\":null}]";
//        List<LoaiPhim> a = JsonFactoryUtil.toObject(new TypeReference<List<LoaiPhim>>() {}, json);
//        for (LoaiPhim loaiPhim : a) {
//            System.out.println(loaiPhim.getTen());
//        }
    }
}
