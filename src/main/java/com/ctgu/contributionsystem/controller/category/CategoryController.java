package com.ctgu.contributionsystem.controller.category;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.PaperCategory;
import com.ctgu.contributionsystem.model.SpecialistCategory;
import com.ctgu.contributionsystem.model.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author : kun
 * @date ： 2019/12/24
 * @description ：this is a code
 **/

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    //分类添加
    @GetMapping("/add")
    @ResponseBody
    public ReturnResposeBody addCategory(@RequestBody PaperCategory paperCategory){
        paperCategory.setCategoryDetail(paperCategory.getCategoryDetail());
        ReturnResposeBody responseBody = new ReturnResposeBody();
        return responseBody;
    }

    //分类查询
    @GetMapping("/get")
    @ResponseBody
    public ReturnResposeBody getCategory (HttpServletRequest request){
        String token = request.getHeader("token");
        ReturnResposeBody responseBody1 = new ReturnResposeBody();
        return responseBody1;
    }

    //分类删除
    @GetMapping("/delete")
    @ResponseBody
    public ReturnResposeBody deleteCategory(HttpServletRequest request,
                                       @Param("paperCategoryId") Integer paperCategoryId){
        PaperCategory paper = new PaperCategory();
        paper.setCategoryId(paperCategoryId);
        ReturnResposeBody responseBody = new ReturnResposeBody();
        return responseBody;
    }
}
