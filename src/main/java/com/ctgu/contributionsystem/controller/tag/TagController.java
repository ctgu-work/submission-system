package com.ctgu.contributionsystem.controller.tag;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: contribution-system *
 * @classname: TagController *
 * @author: lnback *
 * @create: 2019-12-21 18:15
 **/
@Controller
@RequestMapping("/admin/tag")
public class TagController {


    //标签添加
    @GetMapping("/add")
    @ResponseBody
    public ReturnResposeBody addTag(@RequestBody Tag tag){
        tag.setTagDetail(tag.getTagDetail());
        ReturnResposeBody responseBody = new ReturnResposeBody();
        return responseBody;
    }

    //标签查询
    @GetMapping("/get")
    @ResponseBody
    public ReturnResposeBody getTag(HttpServletRequest request){
        String token = request.getHeader("token");
        ReturnResposeBody responseBody = new ReturnResposeBody();
        return responseBody;
    }

    //标签删除
    @GetMapping("delete")
    @ResponseBody
    public ReturnResposeBody deleteTag(HttpServletRequest request, @Param("tagId") Integer tagId){
        Tag tag  = new Tag();
        tag.setTagId(tagId);
        ReturnResposeBody responseBody = new ReturnResposeBody();
        return responseBody;
    }

}
