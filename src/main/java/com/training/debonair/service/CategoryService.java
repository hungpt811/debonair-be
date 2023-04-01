package com.training.debonair.service;

import com.github.slugify.Slugify;
import com.training.debonair.dto.CategorySearchModel;
import com.training.debonair.entity.Categories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class CategoryService extends BaseService<Categories> {

    @Override
    protected Class<Categories> clazz() {
        return Categories.class;
    }

    public PagerData<Categories> search(CategorySearchModel searchModel) {

        // khởi tạo câu lệnh
        String sql = "SELECT * FROM tbl_category p WHERE 1=1";

        if (searchModel != null) {
            //tìm theo seo
            if (!StringUtils.isEmpty(searchModel.seo)) {
                sql += " and p.seo = '" + searchModel.seo + "'";
            }


            // tìm kiếm sản phẩm hot
            if (searchModel.isHot != null) {
//				sql += " and p.is_hot = '" + searchModel.seo + "'";
            }

            // tim kiem san pham theo seachText
            if (!StringUtils.isEmpty(searchModel.keyword)) {
                sql += " and (p.name like '%" + searchModel.keyword + "%'"
                        + " or p.seo like '%" + searchModel.keyword + "%')";
            }
        }

        // chi lay san pham chua xoa
//				sql += " and p.status=1 ";
        return executeByNativeSQL(sql, searchModel == null ? 0 : searchModel.getPage());

    }

    /**
     * Dùng để thêm mới sản phẩm
     *
     * @param p
     * @throws IOException
     * @throws IllegalStateException
     */
    @Transactional
    public Categories add(Categories p)
            throws IllegalStateException, IOException {

        //tạo seo
        p.setSeo(new Slugify().slugify(p.getName()));

        // lưu vào database
        return super.saveOrUpdate(p);
    }

    /**
     * Cập nhật sản phẩm
     *
     * @param p
     * @throws IOException
     * @throws IllegalStateException
     */
    @Transactional
    public Categories update(Categories p)
            throws IllegalStateException, IOException {

        // lấy thông tin cũ của product theo id
        Categories categoryInDb = super.getById(p.getId());


        p.setParent(categoryInDb.getParent());

        //tạo seo
        p.setSeo(new Slugify().slugify(p.getName()));

        // lưu vào database
        return super.saveOrUpdate(p);
    }
}
